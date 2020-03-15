%.o: %.c
	$(CC) $(INCLUDES) $(CFLAGS) -c $< -o $@

%.lua.h : %.lua
	xxd -i $< \
		| sed "s/src_//" \
		| sed "s/unsigned char/static const char/" \
		| sed "s/unsigned int/static const size_t/" \
		> $@

%.class: %.java
	-javac -g:none --release 6 $<
	lua etc/patch.lua $@

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
	src/classes.o \
	src/djb2.o \
	src/buffer.o \
	src/proxyud.o \
	src/version.o \
	src/zip.o \
	src/modules.o \
	src/main.o \
	$(ZLIB_OBJ_FILES)

LUA_FILES = \
	src/bytecode.lua.h \
	src/loader.lua.h \
	src/log.lua.h \
	src/luafy.lua.h \
	src/utils.lua.h \
	src/vm.lua.h

CLASS_FILES = \
	src/classes/java/lang/Thread.class \
	src/classes/javax/microedition/midlet/MIDlet.class

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

src/classes.c: src/classes.zip.h

src/classes.zip.h: src/classes/classes.zip
	xxd -i $< \
		| sed "s/src_classes_//" \
		| sed "s/unsigned char/static const uint8_t/" \
		| sed "s/unsigned int/static const size_t/" \
		> $@

src/classes/classes.zip: $(CLASS_FILES)
	(cd src/classes ; rm -f classes.zip ; zip -9r classes.zip . -i *.class)

clean: FORCE
	rm -f test $(OBJ_FILES) $(LUA_FILES) $(CLASS_FILES) src/classes.zip.h src/classes/classes.zip

.PHONY: FORCE
