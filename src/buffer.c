#include <lua.h>
#include <lauxlib.h>

#include <stdint.h>
#include <stddef.h>

#include <stdlib.h>
#include <string.h>

typedef struct {
    void const* data;
    size_t offset;
    size_t size;
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

static int push(lua_State* const L, void const* const data, size_t const size, int const parentIndex);

static buffer_t* check(lua_State* const L, int const index) {
    return (buffer_t*)luaL_checkudata(L, index, BUFFER_MT);
}

static int peek(lua_State* const L) {
    buffer_t const* const self = check(L, 1);
    lua_Integer const offset = luaL_checkinteger(L, 2);
    char const* const type = luaL_checkstring(L, 3);

    if (type[0] != 0 && type[1] != 0) {
        goto error;
    }

    uint8_t const* const data = ((uint8_t const*)self->data) + offset;
    conv_t conv;

    switch (*type) {
        case 'B':
            if (offset < 0 || offset + sizeof(conv.b) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[0];
            lua_pushinteger(L, conv.b);
            return 1;

        case 'C':
            if (offset < 0 || offset + sizeof(conv.c) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[1];
            conv.m[1] = data[0];
            lua_pushinteger(L, conv.c);
            return 1;

        case 'D':
            if (offset < 0 || offset + sizeof(conv.d) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[7];
            conv.m[1] = data[6];
            conv.m[2] = data[5];
            conv.m[3] = data[4];
            conv.m[4] = data[3];
            conv.m[5] = data[2];
            conv.m[6] = data[1];
            conv.m[7] = data[0];
            lua_pushnumber(L, conv.d);
            return 1;

        case 'F':
            if (offset < 0 || offset + sizeof(conv.f) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[3];
            conv.m[1] = data[2];
            conv.m[2] = data[1];
            conv.m[3] = data[0];
            lua_pushnumber(L, conv.f);
            return 1;

        case 'I':
            if (offset < 0 || offset + sizeof(conv.i) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[3];
            conv.m[1] = data[2];
            conv.m[2] = data[1];
            conv.m[3] = data[0];
            lua_pushinteger(L, conv.i);
            return 1;

        case 'J':
            if (offset < 0 || offset + sizeof(conv.j) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[7];
            conv.m[1] = data[6];
            conv.m[2] = data[5];
            conv.m[3] = data[4];
            conv.m[4] = data[3];
            conv.m[5] = data[2];
            conv.m[6] = data[1];
            conv.m[7] = data[0];
            lua_pushinteger(L, conv.j);
            return 1;

        case 'Z':
            if (offset < 0 || offset + sizeof(conv.z) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[0];
            lua_pushboolean(L, conv.z != 0);
            return 1;

        case '1':
            if (offset < 0 || offset + sizeof(conv.u1) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[0];
            lua_pushinteger(L, conv.u1);
            return 1;

        case '2':
            if (offset < 0 || offset + sizeof(conv.u2) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[1];
            conv.m[1] = data[0];
            lua_pushinteger(L, conv.u2);
            return 1;

        case '4':
            if (offset < 0 || offset + sizeof(conv.u4) > self->size) {
                return luaL_error(L, "out of bounds: %d", offset);
            }

            conv.m[0] = data[3];
            conv.m[1] = data[2];
            conv.m[2] = data[1];
            conv.m[3] = data[0];
            lua_pushinteger(L, conv.u1);
            return 1;
    }

error:
    return luaL_error(L, "invalid base type specifier: \"%s\"", type);
}

static int sub(lua_State* const L) {
    buffer_t const* const self = check(L, 1);
    lua_Integer const begin = luaL_checkinteger(L, 2);
    lua_Integer const end = luaL_checkinteger(L, 3);

    if (begin < 0 || end >= self->size || begin >= end) {
        return luaL_error(L, "invalid limits: %d and %d", begin, end);
    }

    return push(L, ((uint8_t const*)self->data) + begin, end - begin, 1);
}

static int size(lua_State* const L) {
    buffer_t const* const self = check(L, 1);
    lua_pushinteger(L, self->size);
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

static int push(lua_State* const L, void const* const data, size_t const size, int const parentIndex) {
    buffer_t* const self = (buffer_t*)lua_newuserdata(L, sizeof(*self));

    self->data = data;
    self->size = size;

    if (parentIndex != LUA_NOREF) {
        lua_pushvalue(L, parentIndex);
        self->parentRef = luaL_ref(L, LUA_REGISTRYINDEX);
    }
    else {
        self->parentRef = LUA_NOREF;
    }

    if (luaL_newmetatable(L, BUFFER_MT)) {
        static const luaL_Reg methods[] = {
            {"peek", peek},
            {"sub",  sub},
            {"size", size},
            {NULL,   NULL}
        };

        luaL_newlib(L, methods);
        lua_setfield(L, -2, "__index");

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
    return push(L, data, length, LUA_NOREF);
}

int luaopen_buffer(lua_State* const L) {
    static const luaL_Reg functions[] = {
        {"new", new_},
        {NULL,  NULL}
    };

    luaL_newlib(L, functions);
    return 1;
}
