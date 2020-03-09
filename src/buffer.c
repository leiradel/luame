#include <lua.h>
#include <lauxlib.h>

#include <stdint.h>
#include <stddef.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "djb2.h"

typedef struct {
    void const* data;
    size_t size;
    size_t position;
    int parentRef;
}
buffer_t;

typedef union {
    int8_t b;
    uint16_t c;
    double d;
    float f;
    int32_t i;
    int64_t j;
    uint8_t z;

    uint8_t u1;
    uint16_t u2;
    uint32_t u4;

    uint8_t m[8];
}
conv_t;

#define BUFFER_MT "buffer_t"

int buffer_push(lua_State* L, void const* data, size_t size, int parentIndex);

static buffer_t* check(lua_State* const L, int const index) {
    return (buffer_t*)luaL_checkudata(L, index, BUFFER_MT);
}

static int read(lua_State* const L) {
    buffer_t* const self = check(L, 1);

    size_t position = self->position;
    size_t const size = self->size;
    uint8_t const* const data = ((uint8_t const*)self->data) + position;

    if (lua_type(L, 2) == LUA_TNUMBER) {
        lua_Integer const length = lua_tointeger(L, 2);

        if (position + length > size) {
invalid_position:
                return luaL_error(L, "invalid position: %d", (lua_Integer)position);
        }

        lua_pushlstring(L, (char const*)data, length);
        return 1;
    }

    char const* const mode = luaL_checkstring(L, 2);
    conv_t conv;

    switch (*mode) {
        case 'B': // byte
        case 'Z': // boolean
        case '1': // u1
            if (position + 1 > size) {
                goto invalid_position;
            }

            conv.m[0] = data[0];
            position++;
            break;

        case 'C': // char
        case '2': // u2
            if (position + 2 > size) {
                goto invalid_position;
            }

            conv.m[0] = data[1];
            conv.m[1] = data[0];
            position += 2;
            break;

        case 'I': // int
        case 'F': // float
        case '4': // u4
            if (position + 4 > size) {
                goto invalid_position;
            }

            conv.m[0] = data[3];
            conv.m[1] = data[2];
            conv.m[2] = data[1];
            conv.m[3] = data[0];
            position += 4;
            break;

        case 'J': // long
        case 'D': // double
            if (position + 8 > size) {
                goto invalid_position;
            }

            conv.m[0] = data[7];
            conv.m[1] = data[6];
            conv.m[2] = data[5];
            conv.m[3] = data[4];
            conv.m[4] = data[3];
            conv.m[5] = data[2];
            conv.m[6] = data[1];
            conv.m[7] = data[0];
            position += 8;
            break;

        case 'l': {
            // line
            if (position == size) {
                lua_pushnil(L);
                return 1;
            }

            luaL_Buffer buffer;
            luaL_buffinit(L, &buffer);

            uint8_t const* const data = (uint8_t const*)self->data;

            while (position < size) {
                uint8_t k = data[position++];

                if (k == '\r') {
                    if (position == size || data[position++] != '\n') {
                        // Stray \r in buffer.
                        return luaL_error(L, "invalid end-of-line: \\r");
                    }

                    // Push line terminated by \r\n.
                    break;
                }
                else if (k == '\n') {
                    // Push line terminated by \n.
                    break;
                }
                else {
                    luaL_addchar(&buffer, k);
                }
            }

            // Push last line without an end-of-line.
            self->position = position;
            luaL_pushresult(&buffer);
            return 1;
        }
    }

    self->position = position;

    switch (*mode) {
        case 'B': lua_pushinteger(L, conv.b);  return 1;
        case 'Z': lua_pushboolean(L, conv.z);  return 1;
        case '1': lua_pushinteger(L, conv.u1); return 1;
        case 'C': lua_pushinteger(L, conv.c);  return 1;
        case '2': lua_pushinteger(L, conv.u2); return 1;
        case 'I': lua_pushinteger(L, conv.i);  return 1;
        case 'F': lua_pushnumber(L, conv.f);   return 1;
        case '4': lua_pushinteger(L, conv.u4); return 1;
        case 'J': lua_pushinteger(L, conv.j);  return 1;
        case 'D': lua_pushnumber(L, conv.d);   return 1;
    }

    return luaL_error(L, "invalid mode: %c", isprint(*mode) ? *mode : '?');
}

static int seek(lua_State* const L) {
    buffer_t* const self = check(L, 1);
    lua_Integer const offset = luaL_checkinteger(L, 2);
    char const* const whence = luaL_optstring(L, 3, "set");

    uint32_t const hash = djb2(whence);
    lua_Integer position = 0;

    switch (hash) {
        case UINT32_C(0x0b88a991): /* set */ position = offset; break;
        case UINT32_C(0x0b88678f): /* cur */ position = self->position + offset; break;
        case UINT32_C(0x0b886f1c): /* end */ position = self->size - offset; break;
        default: return luaL_error(L, "invalid seek mode: %s", whence);
    }

    if (position < 0 || position > self->size) {
        return luaL_error(L, "invalid seek position: %d", position);
    }

    self->position = (size_t)position;
    return 0;
}

static int tell(lua_State* const L) {
    buffer_t const* const self = check(L, 1);
    lua_pushinteger(L, self->position);
    return 1;
}

static int size(lua_State* const L) {
    buffer_t const* const self = check(L, 1);
    lua_pushinteger(L, self->size);
    return 1;
}

static int sub(lua_State* const L) {
    buffer_t const* const self = check(L, 1);
    lua_Integer const begin = luaL_checkinteger(L, 2);
    lua_Integer const end = luaL_checkinteger(L, 3);

    if (begin < 0 || end >= self->size || begin > end) {
        return luaL_error(L, "invalid limits: %d and %d", begin, end);
    }

    return buffer_push(L, ((uint8_t const*)self->data) + begin, end - begin, 1);
}

static int tostring(lua_State* const L) {
    buffer_t const* const self = (buffer_t*)lua_touserdata(L, 1);
    lua_pushlstring(L, (char const*)self->data, self->size);
    return 1;
}

static int gc(lua_State* const L) {
    buffer_t const* const self = (buffer_t*)lua_touserdata(L, 1);

    if (self->parentRef == LUA_NOREF) {
        free((void*)self->data);
    }
    else {
        luaL_unref(L, LUA_REGISTRYINDEX, self->parentRef);
    }

    return 0;
}

int buffer_push(lua_State* const L, void const* const data, size_t const length, int const parentIndex) {
    buffer_t* const self = (buffer_t*)lua_newuserdata(L, sizeof(*self));

    self->data = data;
    self->size = length;
    self->position = 0;

    if (parentIndex != LUA_NOREF) {
        lua_pushvalue(L, parentIndex);
        self->parentRef = luaL_ref(L, LUA_REGISTRYINDEX);
    }
    else {
        self->parentRef = LUA_NOREF;
    }

    if (luaL_newmetatable(L, BUFFER_MT)) {
        static const luaL_Reg methods[] = {
            {"read", read},
            {"seek", seek},
            {"tell", tell},
            {"size", size},
            {"sub",  sub},
            {NULL,   NULL}
        };

        luaL_newlib(L, methods);
        lua_setfield(L, -2, "__index");

        lua_pushcfunction(L, tostring);
        lua_setfield(L, -2, "__tostring");

        lua_pushcfunction(L, gc);
        lua_setfield(L, -2, "__gc");
    }

    lua_setmetatable(L, -2);
    return 1;
}

static int new_(lua_State* const L) {
    size_t length;
    char const* const string = luaL_checklstring(L, 1, &length);

    void* const data = malloc(length);

    if (data == NULL) {
        return luaL_error(L, "out of memory");
    }

    memcpy(data, string, length);
    return buffer_push(L, data, length, LUA_NOREF);
}

int luaopen_buffer(lua_State* const L) {
    static const luaL_Reg functions[] = {
        {"new", new_},
        {NULL,  NULL}
    };

    luaL_newlib(L, functions);
    return 1;
}
