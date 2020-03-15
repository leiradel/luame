#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <string.h>
#include <errno.h>

#include <lua.h>
#include <lauxlib.h>
#include <lualib.h>

static int traceback(lua_State* const L)
{
    luaL_traceback(L, L, lua_tostring(L, -1), 1);
    return 1;
}

static int lua_main(lua_State* const L)
{
    luaL_openlibs(L);

    void register_modules(lua_State* L);
    register_modules(L);

#ifdef LUAME_DEBUG
    lua_pushboolean(L, 1);
#else
    lua_pushboolean(L, 0);
#endif

    lua_setglobal(L, "_LUAME_DEBUG");

    extern char const* const luame_githash;
    lua_pushstring(L, luame_githash);
    lua_setglobal(L, "_LUAME_GITHASH");

    extern char const* const luame_version;
    lua_pushstring(L, luame_version);
    lua_setglobal(L, "_LUAME_VERSION");

    const int argc = lua_tointeger(L, 1);
    const char* const* const argv = (const char* const*)lua_touserdata(L, 2);

    if (argc < 2)
    {
        return luaL_error(L, "missing script path");
    }

    struct stat stbuf;

    if (stat(argv[1], &stbuf) != 0)
    {
        return luaL_error(L, "could not stat \"%s\": %s", argv[1], strerror(errno));
    }

    char* source = (char*)malloc(stbuf.st_size);

    if (source == NULL)
    {
        return luaL_error(L, "out of memory");
    }

    FILE* const file = fopen(argv[1], "rb");

    if (file == NULL)
    {
        return luaL_error(L, "could not open \"%s\": %s", argv[1], strerror(errno));
    }

    if (fread(source, 1, stbuf.st_size, file) != stbuf.st_size)
    {
        return luaL_error(L, "could not read from \"%s\": %s", argv[1], strerror(errno));
    }

    fclose(file);

    if (luaL_loadbufferx(L, source, stbuf.st_size, argv[1], "t") != LUA_OK)
    {
        return lua_error(L);
    }

    lua_call(L, 0, 1);
    lua_createtable(L, argc - 1, 0);

    for (int i = 1; i < argc; i++)
    {
        lua_pushstring(L, argv[i]);
        lua_rawseti(L, -2, i);
    }

    lua_call(L, 1, 1);
    return 1;
}

int main(const int argc, const char* const* const argv)
{
    lua_State* L = luaL_newstate();

    if (L == NULL)
    {
        fprintf(stderr, "Error creating the Lua state\n");
        return EXIT_FAILURE;
    }

    lua_pushcfunction(L, traceback);
    lua_pushcfunction(L, lua_main);
    lua_pushinteger(L, argc);
    lua_pushlightuserdata(L, (void*)argv);

    if (lua_pcall(L, 2, 1, -4) != LUA_OK)
    {
        fprintf(stderr, "%s\n", luaL_tolstring(L, -1, NULL));
        lua_close(L);
        return EXIT_FAILURE;
    }

    const int ok = lua_toboolean(L, -1);
    lua_close(L);
    return ok ? EXIT_SUCCESS : EXIT_FAILURE;
}
