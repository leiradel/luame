#include <lua.h>
#include <lauxlib.h>

#include "types.h"

static int l2i(lua_State* const L) {
    lua_pushinteger(L, box_int(unbox_long(luaL_checkinteger(L, 1))));
    return 1;
}

LUAMOD_API int luaopen_opcodes(lua_State* const L) {
    static const luaL_Reg functions[] = {
        {"l2i", l2i},
        {NULL,  NULL}
    };

    luaL_newlib(L, functions);
    return 1;
}
