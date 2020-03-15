#include <lua.h>
#include <lauxlib.h>

#include <zconf.h>

unsigned long crc32(unsigned long crc, const unsigned char FAR *buf, uInt len);

static int crc32l(lua_State* const L) {
    size_t length;
    char const* const string = luaL_checklstring(L, 1, &length);
    lua_pushinteger(L, crc32(0, (const unsigned char FAR*)string, length));
    return 1;
}

LUAMOD_API int luaopen_crc32(lua_State* const L) {
    lua_pushcfunction(L, crc32l);
    return 1;
}
