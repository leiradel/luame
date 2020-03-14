#include <lua.h>
#include <lauxlib.h>

#include <unzip.h>

#include <stdlib.h>

#define UNZIP_MT "unzFile"

int buffer_push(lua_State* L, void const* data, size_t size, int parentIndex);

static unzFile* check(lua_State* const L, int const index) {
    return (unzFile*)luaL_checkudata(L, index, UNZIP_MT);
}

static int exists(lua_State* const L) {
    unzFile* const self = check(L, 1);
    char const* const path = luaL_checkstring(L, 2);
    lua_pushboolean(L, unzLocateFile(*self, path, 1) == UNZ_OK);
    return 1;
}

static int readzip(lua_State* const L) {
    unzFile* const self = check(L, 1);
    char const* const path = luaL_checkstring(L, 2);

    if (unzLocateFile(*self, path, 1) != UNZ_OK) {
	    return luaL_error(L, "could not find file \"%s\" in archive", path);
    }

    unz_file_info info;

    if (unzGetCurrentFileInfo(*self, &info, NULL, 0, NULL, 0, NULL, 0) != UNZ_OK) {
	    return luaL_error(L, "error getting information for file \"%s\" in archive", path);
    }

    if (unzOpenCurrentFile(*self) != UNZ_OK) {
    	return luaL_error(L, "error opening file \"%s\"", path);
    }

    void* const buffer = malloc(info.uncompressed_size);

    if (buffer == NULL) {
        unzCloseCurrentFile(*self);
        return luaL_error(L, "out-of-memory allocating buffer to read from file \"%s\" in archive", path);
    }

    if (unzReadCurrentFile(*self, buffer, info.uncompressed_size) != info.uncompressed_size) {
        unzCloseCurrentFile(*self);
        return luaL_error(L, "error reading from file \"%s\" in archive", path);
    }

    unzCloseCurrentFile(*self);
    return buffer_push(L, buffer, (size_t)info.uncompressed_size, 0);
}

static int gc(lua_State* const L) {
    unzFile* const self = (unzFile*)lua_touserdata(L, 1);
    unzClose(*self);
    return 0;
}

int zip_push(lua_State* const L, unzFile file) {
    unzFile* const self = (unzFile*)lua_newuserdata(L, sizeof(unzFile));
    *self = file;

    if (luaL_newmetatable(L, UNZIP_MT)) {
        static const luaL_Reg methods[] = {
            {"exists", exists},
            {"read",   readzip},
            {NULL,     NULL}
        };

        luaL_newlib(L, methods);
        lua_setfield(L, -2, "__index");

        lua_pushcfunction(L, gc);
        lua_setfield(L, -2, "__gc");
    }

    lua_setmetatable(L, -2);
    return 1;
}

static int open(lua_State* const L) {
    char const* const path = luaL_checkstring(L, 1);
    unzFile file = unzOpen(path);

    if (file == NULL) {
        return luaL_error(L, "error opening archive \"%s\"", path);
    }

    return zip_push(L, file);
}

LUAMOD_API int luaopen_zip(lua_State* const L) {
    static const luaL_Reg functions[] = {
        {"open", open},
        {NULL,   NULL}
    };

    luaL_newlib(L, functions);
    return 1;
}
