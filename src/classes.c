#include <lua.h>
#include <lauxlib.h>

#include <unzip.h>

#include <string.h>
#include <stdint.h>
#include <stddef.h>

#include "classes.zip.h"


static voidpf open_file(voidpf opaque, const char* filename, int mode) {
    static size_t pos;

    (void)opaque;
    (void)filename;
    (void)mode;

    pos = 0;
    return (voidpf)&pos;
}

static uLong read_file(voidpf opaque, voidpf stream, void* buf, uLong size) {
    size_t* const pos = (size_t*)stream;
    memcpy(buf, classes_zip + *pos, size);
    *pos += size;
    return (uLong)size;
}

static uLong write_file(voidpf opaque, voidpf stream, const void* buf, uLong size) {
    (void)opaque;
    (void)stream;
    (void)buf;
    (void)size;

    return 0;
}

static long tell_file(voidpf opaque, voidpf stream) {
    size_t const* const pos = (size_t*)stream;
    return (long)*pos;
}

static long seek_file(voidpf opaque, voidpf stream, uLong offset, int origin) {
    size_t* const pos = (size_t*)stream;
    
    switch (origin) {
        case ZLIB_FILEFUNC_SEEK_CUR: *pos += offset; return 0;
        case ZLIB_FILEFUNC_SEEK_END: *pos = classes_zip_len + offset; return 0;
        case ZLIB_FILEFUNC_SEEK_SET: *pos = offset; return 0;
    }

    return -1;
}

static int close_file(voidpf opaque, voidpf stream) {
    (void)opaque;
    (void)stream;

    return 0;
}

static int testerror_file(voidpf opaque, voidpf stream) {
    (void)opaque;
    (void)stream;

    return 0;
}

static zlib_filefunc_def filefuncs = {
    open_file,
    read_file,
    write_file,
    tell_file,
    seek_file,
    close_file,
    testerror_file,
    NULL
};

int zip_push(lua_State* const L, unzFile file);

LUAMOD_API int luaopen_classes(lua_State* const L) {
    unzFile file = unzOpen2("classes.zip", &filefuncs);

    if (file == NULL) {
        return luaL_error(L, "error opening archive \"classes.zip\"");
    }

    return zip_push(L, file);
}
