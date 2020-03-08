#include <lua.h>
#include <lauxlib.h>

#include <zip.h>

#define ZIP_MT "zip_t"

static zip_t* check(lua_State* const L, int const index) {
    return *(zip_t**)luaL_checkudata(L, index, ZIP_MT);
}

static int read(lua_State* const L) {
    zip_t* const self = check(L, 1);

}

static int gc(lua_State* const L) {
    zip_t* const self = *(zip_t**)lua_touserdata(L, 1);
    zip_close(self);
    return 0;
}

static int open(lua_State* const L) {
    char const* const path = luaL_checkstring(L, 1);
    int err;

    zip_t* const self = zip_open(path, ZIP_CHECKCONS | ZIP_RDONLY, &err);

    if (self == NULL) {
        zip_error_t error;
        zip_error_init_with_code((&error, err);

        lua_pushfstring(L, "error opening archive \"%s\": %s", path, zip_error_strerror(&error));

        zip_error_fini(&error);
        return lua_error(L);
    }

    *(zip_t**)lua_newuserdata(L, sizeof(zip_t*)) = self;

    if (luaL_newmetatable(L, ZIP_MT)) {
        static const luaL_Reg methods[] = {
            {"read", read},
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

int luazip_buffer(lua_State* const L) {
    static const luaL_Reg functions[] = {
        {"open", open},
        {NULL,   NULL}
    };

    luaL_newlib(L, functions);
    return 1;
}
