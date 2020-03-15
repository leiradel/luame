#include <stddef.h>
#include <string.h>

#include <lua.h>
#include <lauxlib.h>

#include "lua/bytecode.lua.h"
#include "lua/loader.lua.h"
#include "lua/log.lua.h"
#include "lua/luafy.lua.h"
#include "lua/utils.lua.h"
#include "lua/vm.lua.h"

int luaopen_buffer(lua_State*);
int luaopen_classes(lua_State*);
int luaopen_crc32(lua_State*);
int luaopen_proxyud(lua_State*);
int luaopen_zip(lua_State*);

typedef struct
{
    // The module's name.
    const char* name;

    union
    {
        // Either a pointer to the Lua source code...
        const char* source;
        // ... or a pointer to the C function that opens the module.
        lua_CFunction openf;
    };

    // The size of the Lua source code, or 0 if a C function.
    size_t length;
}
module_t;

// Defines a Lua module.
#define MODL(name, array) {name, {array}, sizeof(array)}
// Defines a C module.
#define MODC(name, openf) {name, {(char*)openf}, 0}

// All modules that our searcher will know.
static const module_t modules[] =
{
    MODL("luame.bytecode", bytecode_lua),
    MODL("luame.loader",   loader_lua),
    MODL("luame.log",      log_lua),
    MODL("luame.luafy",    luafy_lua),
    MODL("luame.utils",    utils_lua),
    MODL("luame.vm",       vm_lua),
    MODC("luame.buffer",   luaopen_buffer),
    MODC("luame.classes",  luaopen_classes),
    MODC("luame.crc32",    luaopen_crc32),
    MODC("luame.proxyud",  luaopen_proxyud),
    MODC("luame.zip",      luaopen_zip)
};

#undef MODL
#undef MODC

// The searcher function that knows our embedded modules.
static int searcher(lua_State* const L)
{
    // Get the module name.
    const char* const name = lua_tostring(L, 1);

    // Iterate over the modules looking for the one given to us.
    for (size_t i = 0; i < sizeof(modules) / sizeof(modules[0]); i++)
    {
        if (strcmp(name, modules[i].name) == 0)
        {
            // Found the module, check if it's a Lua or a C module.
            if (modules[i].length != 0)
            {
                // Lua module, compile the chunk.
                const int res = luaL_loadbufferx(L, modules[i].source, modules[i].length, name, "t");

                if (res != LUA_OK)
                {
                    // Syntax error, throw it.
                    return lua_error(L);
                }

                // The compiled chunk is left as a function on the stack.
            }
            else
            {
                // Push the function that opens the module with the AntedbApi pointer as an upvalue.
                lua_pushcfunction(L, modules[i].openf);
            }

            // Return the function that creates the module.
            return 1;
        }
    }

    // We don't know this module...
    lua_pushfstring(L, "unknown module \"%s\"", name);
    return 1;
}

// Register our searcher.
void register_modules(lua_State* const L)
{
    // Find the "package.searchers" global and get its length.
    lua_getglobal(L, "package");
    lua_getfield(L, -1, "searchers");
    const size_t length = lua_rawlen(L, -1);

    // Add our searcher with the API as an upvalue to the end of the searchers list.
    lua_pushcfunction(L, searcher);
    lua_rawseti(L, -2, length + 1);

    // Clean up the stack.
    lua_pop(L, 2);
}
