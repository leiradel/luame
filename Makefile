%.o: %.c
	$(CC) $(INCLUDES) $(CFLAGS) -c $< -o $@

%.lua.h : %.lua
	xxd -i $< \
		| sed "s/src_lua_//" \
		| sed "s/unsigned char/static const char/" \
		| sed "s/unsigned int/static const size_t/" \
		> $@

%.class: %.java
	-javac -g:none --release 6 -cp src/classes $<

CC ?= gcc
CFLAGS = -std=c99 -Wall -DNOBYFOUR
INCLUDES = -Isrc -Isrc/zip -Isrc/zlib/contrib/minizip
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
	src/array.o \
	src/buffer.o \
	src/classes.o \
	src/crc32.o \
	src/djb2.o \
	src/main.o \
	src/modules.o \
	src/opcodes.o \
	src/proxyud.o \
	src/version.o \
	src/zip.o \
	$(ZLIB_OBJ_FILES)

LUA_FILES = \
	src/lua/bytecode.lua.h \
	src/lua/loader.lua.h \
	src/lua/log.lua.h \
	src/lua/luafy.lua.h \
	src/lua/native.lua.h \
	src/lua/utils.lua.h \
	src/lua/vm.lua.h

CLASS_FILES = \
	src/classes/java/io/ByteArrayInputStream.class \
	src/classes/java/io/ByteArrayOutputStream.class \
	src/classes/java/io/DataInputStream.class \
	src/classes/java/io/DataOutputStream.class \
	src/classes/java/io/EOFException.class \
	src/classes/java/io/InputStream.class \
	src/classes/java/io/InputStreamReader.class \
	src/classes/java/io/InterruptedIOException.class \
	src/classes/java/io/IOException.class \
	src/classes/java/io/OutputStream.class \
	src/classes/java/io/OutputStreamWriter.class \
	src/classes/java/io/PrintStream.class \
	src/classes/java/io/Reader.class \
	src/classes/java/io/UnsupportedEncodingException.class \
	src/classes/java/io/UTFDataFormatException.class \
	src/classes/java/io/Writer.class \
	src/classes/java/lang/ArithmeticException.class \
	src/classes/java/lang/ArrayIndexOutOfBoundsException.class \
	src/classes/java/lang/ArrayStoreException.class \
	src/classes/java/lang/Boolean.class \
	src/classes/java/lang/Byte.class \
	src/classes/java/lang/Character.class \
	src/classes/java/lang/ClassCastException.class \
	src/classes/java/lang/Class.class \
	src/classes/java/lang/ClassNotFoundException.class \
	src/classes/java/lang/Double.class \
	src/classes/java/lang/Error.class \
	src/classes/java/lang/Exception.class \
	src/classes/java/lang/Float.class \
	src/classes/java/lang/IllegalAccessException.class \
	src/classes/java/lang/IllegalArgumentException.class \
	src/classes/java/lang/IllegalMonitorStateException.class \
	src/classes/java/lang/IllegalStateException.class \
	src/classes/java/lang/IllegalThreadStateException.class \
	src/classes/java/lang/IndexOutOfBoundsException.class \
	src/classes/java/lang/InstantiationException.class \
	src/classes/java/lang/Integer.class \
	src/classes/java/lang/InterruptedException.class \
	src/classes/java/lang/Long.class \
	src/classes/java/lang/Math.class \
	src/classes/java/lang/NegativeArraySizeException.class \
	src/classes/java/lang/NoClassDefFoundError.class \
	src/classes/java/lang/NullPointerException.class \
	src/classes/java/lang/NumberFormatException.class \
	src/classes/java/lang/Object.class \
	src/classes/java/lang/OutOfMemoryError.class \
	src/classes/java/lang/Runtime.class \
	src/classes/java/lang/RuntimeException.class \
	src/classes/java/lang/SecurityException.class \
	src/classes/java/lang/Short.class \
	src/classes/java/lang/StringBuffer.class \
	src/classes/java/lang/String.class \
	src/classes/java/lang/StringIndexOutOfBoundsException.class \
	src/classes/java/lang/System.class \
	src/classes/java/lang/Thread.class \
	src/classes/java/lang/Throwable.class \
	src/classes/java/lang/VirtualMachineError.class \
	src/classes/java/util/Calendar.class \
	src/classes/java/util/Date.class \
	src/classes/java/util/EmptyStackException.class \
	src/classes/java/util/Hashtable.class \
	src/classes/java/util/NoSuchElementException.class \
	src/classes/java/util/Random.class \
	src/classes/java/util/Stack.class \
	src/classes/java/util/Timer.class \
	src/classes/java/util/TimerTask.class \
	src/classes/java/util/TimeZone.class \
	src/classes/java/util/Vector.class \
	src/classes/javax/microedition/io/ConnectionNotFoundException.class \
	src/classes/javax/microedition/io/Connector.class \
	src/classes/javax/microedition/io/PushRegistry.class \
	src/classes/javax/microedition/lcdui/Alert.class \
	src/classes/javax/microedition/lcdui/AlertType.class \
	src/classes/javax/microedition/lcdui/Canvas.class \
	src/classes/javax/microedition/lcdui/ChoiceGroup.class \
	src/classes/javax/microedition/lcdui/Command.class \
	src/classes/javax/microedition/lcdui/CustomItem.class \
	src/classes/javax/microedition/lcdui/DateField.class \
	src/classes/javax/microedition/lcdui/Displayable.class \
	src/classes/javax/microedition/lcdui/Display.class \
	src/classes/javax/microedition/lcdui/Font.class \
	src/classes/javax/microedition/lcdui/Form.class \
	src/classes/javax/microedition/lcdui/game/GameCanvas.class \
	src/classes/javax/microedition/lcdui/game/Layer.class \
	src/classes/javax/microedition/lcdui/game/LayerManager.class \
	src/classes/javax/microedition/lcdui/game/Sprite.class \
	src/classes/javax/microedition/lcdui/game/TiledLayer.class \
	src/classes/javax/microedition/lcdui/Gauge.class \
	src/classes/javax/microedition/lcdui/Graphics.class \
	src/classes/javax/microedition/lcdui/Image.class \
	src/classes/javax/microedition/lcdui/ImageItem.class \
	src/classes/javax/microedition/lcdui/Item.class \
	src/classes/javax/microedition/lcdui/List.class \
	src/classes/javax/microedition/lcdui/Screen.class \
	src/classes/javax/microedition/lcdui/Spacer.class \
	src/classes/javax/microedition/lcdui/StringItem.class \
	src/classes/javax/microedition/lcdui/TextBox.class \
	src/classes/javax/microedition/lcdui/TextField.class \
	src/classes/javax/microedition/lcdui/Ticker.class \
	src/classes/javax/microedition/media/Manager.class \
	src/classes/javax/microedition/media/MediaException.class \
	src/classes/javax/microedition/midlet/MIDlet.class \
	src/classes/javax/microedition/midlet/MIDletStateChangeException.class \
	src/classes/javax/microedition/pki/CertificateException.class \
	src/classes/javax/microedition/rms/InvalidRecordIDException.class \
	src/classes/javax/microedition/rms/RecordStore.class \
	src/classes/javax/microedition/rms/RecordStoreException.class \
	src/classes/javax/microedition/rms/RecordStoreFullException.class \
	src/classes/javax/microedition/rms/RecordStoreNotFoundException.class \
	src/classes/javax/microedition/rms/RecordStoreNotOpenException.class

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

src/classes.o: src/classes.zip.h

src/classes.zip.h: src/classes/classes.zip
	xxd -i $< \
		| sed "s/src_classes_//" \
		| sed "s/unsigned char/static const uint8_t/" \
		| sed "s/unsigned int/static const size_t/" \
		> $@

src/classes/classes.zip: $(CLASS_FILES)
	find src/classes -name '*.class' -exec lua etc/patch.lua {} \;
	(cd src/classes ; rm -f classes.zip ; zip -9r classes.zip . -i *.class)

clean: FORCE
	rm -f test $(OBJ_FILES) $(LUA_FILES) $(CLASS_FILES) src/classes.zip.h src/classes/classes.zip

.PHONY: FORCE
