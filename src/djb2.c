#include "djb2.h"

uint32_t djb2(char const* str) {
    uint32_t hash = 5381;

    if (*str != 0) {
        do {
            hash = hash * 33 + (uint8_t)*str++;
        }
        while (*str != 0);
    }

    return hash;
}
