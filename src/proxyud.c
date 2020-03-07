#include <lua.h>
#include <lauxlib.h>

static int l_new(lua_State* L) {
    lua_newuserdata(L, 0);

    if (lua_type(L, 1) == LUA_TTABLE) {
        lua_pushvalue(L, 1);
        lua_setmetatable(L, -2);
    }

    return 1;
}

LUAMOD_API int luaopen_proxyud(lua_State* L) {
    static const luaL_Reg functions[] = {
        {"new", l_new},
        {NULL, NULL}
    };

    luaL_newlib(L, functions);
    return 1;
}
