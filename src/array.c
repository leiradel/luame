#include <lua.h>
#include <lauxlib.h>

#include <stdint.h>
#include <stddef.h>
#include <string.h>

#include "types.h"

typedef enum {
    BOOLEAN = 4,
    CHAR = 5,
    FLOAT = 6,
    DOUBLE = 7,
    BYTE = 8,
    SHORT = 9,
    INT = 10,
    LONG = 11
}
arraytype_t;

typedef struct {
    lua_Integer length;
    arraytype_t type;
    uint8_t data[0];
}
array_t;

#define ARRAY_MT "array_t"

static array_t* check(lua_State* const L, int const index) {
    return (array_t*)luaL_checkudata(L, index, ARRAY_MT);
}

static int index(lua_State* const L) {
    array_t const* const self = check(L, 1);

    if (lua_isinteger(L, 2)) {
        lua_Integer const index = lua_tointeger(L, 2);

        if (index < 0 || index >= self->length) {
            return luaL_error(L, "index %d out of bounds: [0, %d)", index, self->length);
        }

        switch (self->type) {
            case BOOLEAN: {
                uint8_t const* const data = (uint8_t*)self->data;
                lua_pushinteger(L, box_int((data[index / 8] & (1 << (index % 8))) != 0));
                return 1;
            }

            case CHAR: {
                jchar_t const* const data = (jchar_t*)self->data;
                lua_pushinteger(L, box_int(data[index]));
                return 1;
            }

            case FLOAT: {
                jfloat_t const* const data = (jfloat_t*)self->data;
                lua_pushinteger(L, box_float(data[index]));
                return 1;
            }

            case DOUBLE: {
                jdouble_t const* const data = (jdouble_t*)self->data;
                lua_pushinteger(L, box_double(data[index]));
                return 1;
            }

            case BYTE: {
                jbyte_t const* const data = (jbyte_t*)self->data;
                lua_pushinteger(L, box_int(data[index]));
                return 1;
            }

            case SHORT: {
                jshort_t const* const data = (jshort_t*)self->data;
                lua_pushinteger(L, box_int(data[index]));
                return 1;
            }

            case INT: {
                jint_t const* const data = (jint_t*)self->data;
                lua_pushinteger(L, box_int(data[index]));
                return 1;
            }

            case LONG: {
                jlong_t const* const data = (jlong_t*)self->data;
                lua_pushinteger(L, box_long(data[index]));
                return 1;
            }
        }
    }
    else
    {
        char const* const key = luaL_checkstring(L, 2);

        if (key[0] == 'n' && key[1] == 0) {
            lua_pushinteger(L, self->length);
            return 1;
        }
    }

    return luaL_error(L, "invalid index to array");
}

static int newindex(lua_State* const L) {
    array_t* const self = check(L, 1);
    lua_Integer const index = luaL_checkinteger(L, 2);
    lua_Integer const value = luaL_checkinteger(L, 3);

    if (index < 0 || index >= self->length) {
        return luaL_error(L, "index %d out of bounds: [0, %d)", index, self->length);
    }

    switch (self->type) {
        case BOOLEAN: {
            uint8_t* const data = (uint8_t*)self->data;

            if (unbox_int(value) != 0) {
                data[index / 8] |= 1 << (index % 8);
            }
            else {
                data[index / 8] &= ~(1 << (index % 8));
            }

            return 0;
        }

        case CHAR: {
            jchar_t* const data = (jchar_t*)self->data;
            data[index] = (jchar_t)unbox_int(value);
            return 0;
        }

        case FLOAT: {
            jfloat_t* const data = (jfloat_t*)self->data;
            data[index] = unbox_float(value);
            return 0;
        }

        case DOUBLE: {
            jdouble_t* const data = (jdouble_t*)self->data;
            data[index] = unbox_double(value);
            return 0;
        }

        case BYTE: {
            jbyte_t* const data = (jbyte_t*)self->data;
            data[index] = (jbyte_t)unbox_int(value);
            return 0;
        }

        case SHORT: {
            jshort_t* const data = (jshort_t*)self->data;
            data[index] = (jshort_t)unbox_int(value);
            return 0;
        }

        case INT: {
            jint_t* const data = (jint_t*)self->data;
            data[index] = unbox_int(value);
            return 0;
        }

        case LONG: {
            jlong_t* const data = (jlong_t*)self->data;
            data[index] = unbox_long(value);
            return 0;
        }
    }

    return 0;
}

static int tostring(lua_State* const L) {
    array_t const* const self = (array_t*)lua_touserdata(L, 1);
    lua_pushlstring(L, (char const*)self->data, self->length);
    return 1;
}

int array_push(lua_State* const L, size_t const length, arraytype_t const type) {
    size_t size;

    switch (type) {
        case BOOLEAN: size = (length + 7) / 8; break;
        case CHAR:    size = length * sizeof(jchar_t); break;
        case FLOAT:   size = length * sizeof(jfloat_t); break;
        case DOUBLE:  size = length * sizeof(jdouble_t); break;
        case BYTE:    size = length * sizeof(jbyte_t); break;
        case SHORT:   size = length * sizeof(jshort_t); break;
        case INT:     size = length * sizeof(jint_t); break;
        case LONG:    size = length * sizeof(jlong_t); break;
    }

    size += sizeof(array_t);
    array_t* const self = (array_t*)lua_newuserdata(L, size);
    memset(self, 0, size);

    self->length = length;
    self->type = type;

    if (luaL_newmetatable(L, ARRAY_MT)) {
        lua_pushcfunction(L, index);
        lua_setfield(L, -2, "__index");

        lua_pushcfunction(L, newindex);
        lua_setfield(L, -2, "__newindex");

        lua_pushcfunction(L, tostring);
        lua_setfield(L, -2, "__tostring");
    }

    lua_setmetatable(L, -2);
    return 1;
}

static int new_(lua_State* const L) {
    lua_Integer const length = luaL_checkinteger(L, 1);
    arraytype_t const type = (arraytype_t)luaL_checkinteger(L, 2);

    switch (type) {
        case BOOLEAN:
        case CHAR:
        case FLOAT:
        case DOUBLE:
        case BYTE:
        case SHORT:
        case INT:
        case LONG: break;

        default: return luaL_error(L, "invalid array type %d", type);
    }

    return array_push(L, length, type);
}

LUAMOD_API int luaopen_array(lua_State* const L) {
    static const luaL_Reg functions[] = {
        {"new", new_},
        {NULL,  NULL}
    };

    luaL_newlib(L, functions);
    return 1;
}
