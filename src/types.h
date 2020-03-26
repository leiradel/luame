#ifndef TYPES_H__
#define TYPES_H__

#include <lua.h>

#include <stdint.h>

typedef int8_t   jbyte_t;
typedef int16_t  jshort_t;
typedef uint16_t jchar_t;
typedef int32_t  jint_t;
typedef int64_t  jlong_t;
typedef float    jfloat_t;
typedef double   jdouble_t;

typedef char luainteger_can_hold_jlong[sizeof(lua_Integer) >= sizeof(jlong_t) ? 1 : -1];

typedef union {
    lua_Integer luai;

    jint_t    jint;
    jlong_t   jlong;
    jfloat_t  jfloat;
    jdouble_t jdouble;
}
type_t;

// Boxing
static lua_Integer box_int   (jint_t    const x) { type_t t; t.jint    = x; return t.luai; }
static lua_Integer box_long  (jlong_t   const x) { type_t t; t.jlong   = x; return t.luai; }
static lua_Integer box_float (jfloat_t  const x) { type_t t; t.jfloat  = x; return t.luai; }
static lua_Integer box_double(jdouble_t const x) { type_t t; t.jdouble = x; return t.luai; }

// Unboxing
static jint_t    unbox_int   (lua_Integer const x) { type_t t; t.luai = x; return t.jint;    }
static jlong_t   unbox_long  (lua_Integer const x) { type_t t; t.luai = x; return t.jlong;   }
static jfloat_t  unbox_float (lua_Integer const x) { type_t t; t.luai = x; return t.jfloat;  }
static jdouble_t unbox_double(lua_Integer const x) { type_t t; t.luai = x; return t.jdouble; }

#endif // TYPES_H__
