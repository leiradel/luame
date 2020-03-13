%.o: %.c
	$(CC) $(INCLUDES) $(CFLAGS) -c $< -o $@

%.lua.h : %.lua
	xxd -i $< \
		| sed "s/src_//" \
		| sed "s/unsigned char/static const char/" \
		| sed "s/unsigned int/static const size_t/" \
		> $@

CC ?= gcc
CFLAGS = -std=c99 -Wall
INCLUDES = -Isrc -Isrc/zlib/contrib/minizip
LIBS = -llua -lm -ldl

ZLIB_OBJ_FILES = \
	src/zlib/contrib/minizip/unzip.o \
	src/zlib/contrib/minizip/ioapi.o \
	src/zlib/crc32.o \
	src/zlib/adler32.o \
	src/zlib/inflate.o \
	src/zlib/inftrees.o \
	src/zlib/inffast.o \
	src/zlib/zutil.o

OBJ_FILES = \
	src/djb2.o \
	src/version.o \
	src/buffer.o \
	src/proxyud.o \
	src/zip.o \
	src/modules.o \
	src/main.o \
	$(ZLIB_OBJ_FILES)

LUA_FILES = \
	src/loader.lua.h \
	src/luafy.lua.h \
	src/utils.lua.h

ifeq ($(DEBUG), 1)
	CFLAGS += -O0 -g -DLUAME_DEBUG
else
	CFLAGS += -O2 -DLUAME_RELEASE -DNDEBUG
endif

all: test

test: $(OBJ_FILES)
	$(CC) -o $@ $+ $(LIBS)

src/version.c: FORCE
	cat etc/version.c.templ \
		| sed s/HASH/`git rev-parse HEAD | tr -d "\n"`/g \
		| sed s/VERSION/`git tag | sort -r -V | head -n1 | tr -d "\n"`/g \
		> $@

src/modules.o: $(LUA_FILES)

clean: FORCE
	rm -f test $(OBJ_FILES) $(LUA_FILES)

.PHONY: FORCE
