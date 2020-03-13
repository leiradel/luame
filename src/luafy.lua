local utils = require 'luame.utils'

local function get(index, class)
    return class.constantPool[index]
end

local function countParams(descriptor)
    local i = 2
    local count = 0

    while true do
        local k = descriptor:byte(i)

        if k == 41 then -- ')'
            return count
        end

        count = count + 1

        if k == 74 or k == 68 then -- 'J' or 'D'
            count = count + 1
            i = i + 1
        else
            if k == 91 then -- '['
                repeat
                    i = i + 1
                    k = descriptor:sub(i, i)
                until k ~= 91
            end

            if k == 76 then -- 'L'
                repeat
                    i = i + 1
                    k = descriptor:sub(i, i)
                until k == ';'
            end

            i = i + 1
        end
    end
end

local generators = {
    [0x01] = function(lua, class, sp, b)
        lua:println('s', sp, ' = nil -- aconst_null')
        return sp + 1
    end,
    [0x02] = function(lua, class, sp, b)
        lua:println('s', sp, ' = -1 -- iconst_m1')
        return sp + 1
    end,
    [0x03] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 0 -- iconst_0')
        return sp + 1
    end,
    [0x04] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 1 -- iconst_1')
        return sp + 1
    end,
    [0x05] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 2 -- iconst_2')
        return sp + 1
    end,
    [0x06] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 3 -- iconst_3')
        return sp + 1
    end,
    [0x07] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 4 -- iconst_4')
        return sp + 1
    end,
    [0x08] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 5 -- iconst_5')
        return sp + 1
    end,
    [0x0b] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 0 -- fconst_0')
        return sp + 1
    end,
    [0x0c] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 1 -- fconst_1')
        return sp + 1
    end,
    [0x0d] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 2 -- fconst_2')
        return sp + 1
    end,
    [0x0e] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 0 -- dconst_0')
        return sp + 1
    end,
    [0x0f] = function(lua, class, sp, b)
        lua:println('s', sp, ' = 1 -- dconst_1')
        return sp + 1
    end,
    [0x10] = function(lua, class, sp, b)
        lua:println('s', sp, ' = byteToInt(', b:read '1', ') -- bipush')
        return sp + 1
    end,
    [0x15] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1', ' -- iload')
        return sp + 1
    end,
    [0x17] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1', ' -- fload')
        return sp + 1
    end,
    [0x18] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1', ' -- dload')
        return sp + 1
    end,
    [0x19] = function(lua, class, sp, b)
        local index = b:read '1'
        lua:println('s', sp, ' = l', b:read '1', ' -- aload')
        return sp + 1
    end,
    [0x1a] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l0 -- iload_0')
        return sp + 1
    end,
    [0x1b] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l1 -- iload_1')
        return sp + 1
    end,
    [0x1c] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l2 -- iload_2')
        return sp + 1
    end,
    [0x1d] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l3 -- iload_3')
        return sp + 1
    end,
    [0x22] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l0 -- fload_0')
        return sp + 1
    end,
    [0x23] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l1 -- fload_1')
        return sp + 1
    end,
    [0x24] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l2 -- fload_2')
        return sp + 1
    end,
    [0x25] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l3 -- fload_3')
        return sp + 1
    end,
    [0x26] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l0 -- dload_0')
        return sp + 1
    end,
    [0x27] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l1 -- dload_1')
        return sp + 1
    end,
    [0x28] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l2 -- dload_2')
        return sp + 1
    end,
    [0x29] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l3 -- dload_3')
        return sp + 1
    end,
    [0x2a] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l0 -- aload_0')
        return sp + 1
    end,
    [0x2b] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l1 -- aload_1')
        return sp + 1
    end,
    [0x2c] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l2 -- aload_2')
        return sp + 1
    end,
    [0x2d] = function(lua, class, sp, b)
        lua:println('s', sp, ' = l3 -- aload_3')
        return sp + 1
    end,
    [0x2e] = function(lua, class, sp, b)
        lua:println('-- iaload')
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
        return sp - 1
    end,
    [0x30] = function(lua, class, sp, b)
        lua:println('-- faload')
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
        return sp - 1
    end,
    [0x31] = function(lua, class, sp, b)
        lua:println('-- daload')
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
        return sp - 1
    end,
    [0x32] = function(lua, class, sp, b)
        lua:println('-- aaload')
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
        return sp - 1
    end,
    [0x33] = function(lua, class, sp, b)
        lua:println '-- baload'
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = byteToInt(s', sp - 2, '[s', sp - 1, '])')
        return sp - 1
    end,
    [0x34] = function(lua, class, sp, b)
        lua:println '-- caload'
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = charToInt(s', sp - 2, '[s', sp - 1, '])')
        return sp - 1
    end,
    [0x38] = function(lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1, ' -- fstore')
        return sp - 1
    end,
    [0x39] = function(lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1, ' -- dstore')
        return sp - 1
    end,
    [0x3a] = function(lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1, ' -- astore')
        return sp - 1
    end,
    [0x43] = function(lua, class, sp, b)
        lua:println('l0 = s', sp - 1, ' -- fstore_0')
        return sp - 1
    end,
    [0x44] = function(lua, class, sp, b)
        lua:println('l1 = s', sp - 1, ' -- fstore_1')
        return sp - 1
    end,
    [0x45] = function(lua, class, sp, b)
        lua:println('l2 = s', sp - 1, ' -- fstore_2')
        return sp - 1
    end,
    [0x46] = function(lua, class, sp, b)
        lua:println('l3 = s', sp - 1, ' -- fstore_3')
        return sp - 1
    end,
    [0x47] = function(lua, class, sp, b)
        lua:println('l0 = s', sp - 1, ' -- dstore_0')
        return sp - 1
    end,
    [0x48] = function(lua, class, sp, b)
        lua:println('l1 = s', sp - 1, ' -- dstore_1')
        return sp - 1
    end,
    [0x49] = function(lua, class, sp, b)
        lua:println('l2 = s', sp - 1, ' -- dstore_2')
        return sp - 1
    end,
    [0x4a] = function(lua, class, sp, b)
        lua:println('l3 = s', sp - 1, ' -- dstore_3')
        return sp - 1
    end,
    [0x4b] = function(lua, class, sp, b)
        lua:println('l0 = s', sp - 1, ' -- astore_0')
        return sp - 1
    end,
    [0x4c] = function(lua, class, sp, b)
        lua:println('l1 = s', sp - 1, ' -- astore_1')
        return sp - 1
    end,
    [0x4d] = function(lua, class, sp, b)
        lua:println('l2 = s', sp - 1, ' -- astore_2')
        return sp - 1
    end,
    [0x4e] = function(lua, class, sp, b)
        lua:println('l3 = s', sp - 1, ' -- astore_3')
        return sp - 1
    end,
    [0x4f] = function(lua, class, sp, b)
        lua:println '-- iastore'
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
        return sp - 3
    end,
    [0x51] = function(lua, class, sp, b)
        lua:println '-- fastore'
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
        return sp - 3
    end,
    [0x52] = function(lua, class, sp, b)
        lua:println '-- dastore'
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
        return sp - 3
    end,
    [0x53] = function(lua, class, sp, b)
        lua:println '-- aastore'
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
        return sp - 3
    end,
    [0x54] = function(lua, class, sp, b)
        lua:println '-- bastore'
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = intToByte(s', sp - 1, ')')
        return sp - 3
    end,
    [0x55] = function(lua, class, sp, b)
        lua:println '-- castore'
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = intToChar(s', sp - 1, ')')
        return sp - 3
    end,
    [0x59] = function(lua, class, sp, b)
        lua:println('s', sp, ' = s', sp - 1, ' -- dup')
        return sp + 1
    end,
    [0x5a] = function(lua, class, sp, b)
        lua:println '-- dup_x1'
        lua:println('s', sp, ' = s', sp - 1)
        lua:println('s', sp - 1, ' = s', sp - 2)
        lua:println('s', sp - 2, ' = s', sp)
        return sp + 1
    end,
    [0x5b] = function(lua, class, sp, b)
        lua:println '-- dup_x2'
        lua:println('s', sp, ' = s', sp - 1)
        lua:println('s', sp - 1, ' = s', sp - 2)
        lua:println('s', sp - 2, ' = s', sp - 3)
        lua:println('s', sp - 3, ' = s', sp)
        return sp + 1
    end,
    [0x5c] = function(lua, class, sp, b)
        lua:println '-- dup2'
        lua:println('s', sp + 1, ' = s', sp - 1)
        lua:println('s', sp, ' = s', sp - 2)
        return sp + 2
    end,
    [0x5d] = function(lua, class, sp, b)
        lua:println '-- dup2_x1'
        lua:println('s', sp + 1, ' = s', sp - 1)
        lua:println('s', sp + 0, ' = s', sp - 2)
        lua:println('s', sp - 1, ' = s', sp - 3)
        lua:println('s', sp - 2, ' = s', sp + 1)
        lua:println('s', sp - 3, ' = s', sp + 0)
        return sp + 2
    end,
    [0x5e] = function(lua, class, sp, b)
        lua:println '-- dup2_x2'
        lua:println('s', sp + 1, ' = s', sp - 1)
        lua:println('s', sp + 0, ' = s', sp - 2)
        lua:println('s', sp - 1, ' = s', sp - 3)
        lua:println('s', sp - 2, ' = s', sp - 4)
        lua:println('s', sp - 3, ' = s', sp + 1)
        lua:println('s', sp - 4, ' = s', sp + 0)
        return sp + 2
    end,
    [0x60] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' + s', sp - 1, ' -- iadd')
        return sp - 1
    end,
    [0x62] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' + s', sp - 1, ' -- fadd')
        return sp - 1
    end,
    [0x63] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' + s', sp - 1, ' -- dadd')
        return sp - 1
    end,
    [0x66] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' - s', sp - 1, ' -- fsub')
        return sp - 1
    end,
    [0x67] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' - s', sp - 1, ' -- dsub')
        return sp - 1
    end,
    [0x68] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' * s', sp - 1, ' -- imul')
        return sp - 1
    end,
    [0x6a] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' * s', sp - 1, ' -- fmul')
        return sp - 1
    end,
    [0x6b] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' * s', sp - 1, ' -- dmul')
        return sp - 1
    end,
    [0x6c] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' // s', sp - 1, ' -- idiv')
        return sp - 1
    end,
    [0x6e] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' / s', sp - 1, ' -- fdiv')
        return sp - 1
    end,
    [0x6f] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' / s', sp - 1, ' -- ddiv')
        return sp - 1
    end,
    [0x72] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' % s', sp - 1, ' -- frem')
        return sp - 1
    end,
    [0x73] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' % s', sp - 1, ' -- drem')
        return sp - 1
    end,
    [0x74] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = -s', sp - 1, ' -- ineg')
        return sp - 1
    end,
    [0x76] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = -s', sp - 1, ' -- fneg')
        return sp - 1
    end,
    [0x77] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = -s', sp - 1, ' -- dneg')
        return sp - 1
    end,
    [0x7e] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' & s', sp - 1, ' -- iand')
        return sp - 1
    end,
    [0x84] = function(lua, class, sp, b)
        local index = b:read('1')
        local const = b:read('B')
        lua:println('l', index, ' = l', index, ' + ', const, ' -- iinc')
    end,
    [0x85] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToLong(s', sp - 1, ') -- i2l')
    end,
    [0x86] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToFloar(s', sp - 1, ') -- i2f')
    end,
    [0x87] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToDouble(s', sp - 1, ') -- i2d')
    end,
    [0x8b] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = floatToInt(s', sp - 1, ') -- f2i')
    end,
    [0x8c] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = floatToLong(s', sp - 1, ') -- f2l')
    end,
    [0x8d] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = floatToDouble(s', sp - 1, ') -- f2d')
    end,
    [0x8e] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = doubleToInt(s', sp - 1, ') -- d2i')
    end,
    [0x8f] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = doubleToLong(s', sp - 1, ') -- d2l')
    end,
    [0x90] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = doubleToFloat(s', sp - 1, ') -- d2f')
    end,
    [0x91] = function(lua, class, sp, b)
        lua_println('s', sp - 1, ' = intToByte(s', sp - 1, ') -- i2b')
    end,
    [0x92] = function(lua, class, sp, b)
        lua_println('s', sp - 1, ' = intToChar(s', sp - 1, ') -- i2c')
    end,
    [0x93] = function(lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToShort(s', sp - 1, ') -- i2d')
    end,
    [0x95] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = floatCompareLess(s', sp - 2, ', s', sp - 1, ') -- fcmpl')
        return sp - 1
    end,
    [0x96] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = floatCompareGreater(s', sp - 2, ', s', sp - 1, ') -- fcmpg')
        return sp - 1
    end,
    [0x97] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = doubleCompareLess(s', sp - 2, ', s', sp - 1, ') -- dcmpl')
        return sp - 1
    end,
    [0x98] = function(lua, class, sp, b)
        lua:println('s', sp - 2, ' = doubleCompareGreater(s', sp - 2, ', s', sp - 1, ') -- dcmpg')
        return sp - 1
    end,
    [0x99] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' == 0 then goto ', label, ' -- ifeq')
        return sp - 1
    end,
    [0x9a] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' ~= 0 then goto ', label, ' -- ifne')
        return sp - 1
    end,
    [0x9b] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' < 0 then goto ', label, ' -- iflt')
        return sp - 1
    end,
    [0x9c] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' >= 0 then goto ', label, ' -- ifge')
        return sp - 1
    end,
    [0x9d] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' > 0 then goto ', label, ' -- ifgt')
        return sp - 1
    end,
    [0x9e] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' <= 0 then goto ', label, ' -- ifle')
        return sp - 1
    end,
    [0x9f] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' == s', sp - 1, ' then goto ', label, ' -- if_icmpeq')
        return sp - 2
    end,
    [0xa0] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' ~= s', sp - 1, ' then goto ', label, ' -- if_icmpne')
        return sp - 2
    end,
    [0xa1] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' < s', sp - 1, ' then goto ', label, ' -- if_icmplt')
        return sp - 2
    end,
    [0xa2] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' >= s', sp - 1, ' then goto ', label, ' -- if_icmpge')
        return sp - 2
    end,
    [0xa3] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' > s', sp - 1, ' then goto ', label, ' -- if_icmpgt')
        return sp - 2
    end,
    [0xa4] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' <= s', sp - 1, ' then goto ', label, ' -- if_icmple')
        return sp - 2
    end,
    [0xa5] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' == s', sp - 1, ' then goto ', label, ' -- if_acmpeq')
        return sp - 2
    end,
    [0xa6] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 2, ' ~= s', sp - 1, ' then goto ', label, ' -- if_acmpne')
        return sp - 2
    end,
    [0xa7] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('goto ', label, ' -- goto')
    end,
    [0xae] = function(lua, class, sp, b)
        lua:println('return s', sp - 1, ' -- freturn')
    end,
    [0xaf] = function(lua, class, sp, b)
        lua:println('return s', sp - 1, ' -- dreturn')
    end,
    [0xb0] = function(lua, class, sp, b)
        lua:println('return s', sp - 1, ' -- areturn')
    end,
    [0xb2] = function(lua, class, sp, b)
        local fieldref = class.cpool[b:read '2']
        local className = class.cpool[class.cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = class.cpool[fieldref.nameAndTypeIndex]
        local fieldName = class.cpool[nameAndType.nameIndex].bytes

        lua:println('s', sp, ' = _G[ [[', className, ']] ] [ [[', fieldname, ']] ] -- getstatic')
        return sp + 1
    end,
    [0xb4] = function(lua, class, sp, b)
        local fieldref = class.cpool[b:read '2']
        local nameAndType = class.cpool[fieldref.nameAndTypeIndex]
        local fieldName = class.cpool[nameAndType.nameIndex].bytes

        lua:println '-- getfield'
        lua:println('maybeThrowNullPointerException(s', sp, ')')
        lua:println('s', sp, ' = s', sp, '[ [[', fieldName, ']] ]')
    end,
    [0xb9] = function(lua, class, sp, b)
        local methodref = class.cpool[b:read '2']
    end,
    [0xbd] = function(lua, class, sp, b)
        lua:println '-- anewarray'
        lua:println 'do'
        lua:println('    local n = s', sp - 1)
        lua:println('    maybeThrowNegativeArraySizeException(n)')
        lua:println('    s', sp - 1, ' = {n = n, t = \'L\'}')
        lua:println '    for i = 1, n do'
        lua:println('        s', sp - 1, '[i] = nil')
        lua:println '    end'
        lua:println 'end'
    end,
    [0xbe] = function(lua, class, sp, b)
        lua:println '-- arraylength'
        lua:println('maybeThrowNullPointerException(s', sp - 1, ')')
        lua:println('s', sp - 1, ' = s', sp - 1, '.n')
    end,
    [0xbf] = function(lua, class, sp, b)
        lua:println '-- athrow'
        lua:println('maybeThrowNullPointerException(s', sp - 1, ')')
        lua:println('error(s', sp - 1, ')')
    end,
    [0xc0] = function(lua, class, sp, b)
        lua:println('-- checkcast')
    end,
    [0xc1] = function(lua, class, sp, b)
        local className = class.cpool[class.cpool[b:read '2'].nameIndex].bytes
        lua:println('s', sp - 1, ' = instanceOf(s', sp - 1, '[ [[#class]] ], _G[ [[', className, ']] ]) -- instanceof')
    end,
    [0xc6] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' == nil then goto ', label, ' -- ifnull')
        return sp - 1
    end,
    [0xc7] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('o')
        local label = string.format('_%08x', pc + offset)
        lua:println('if s', sp - 1, ' ~= nil then goto ', label, ' -- ifnonnull')
        return sp - 1
    end,
    [0xc8] = function(lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('O')
        local label = string.format('_%08x', pc + offset)
        lua:println('goto ', label)
    end,
}

local function generateCode(lua, class, code)
    local b = code.code
    local sp = 0

    while true do
        lua:println('::_', string.format('%08x', b:tell()), '::')
        lua:indent()

        local op = b:read '1'
        sp = generators[op](lua, class, sp, b) or sp

        lua:unindent()
    end
end

return function(class)
    local lua = utils.codeGenerator()
    lua:println 'return {'
    lua:indent()

    for i = 1, class.methods.n do
        local method = class.methods[i]
        local name = get(method.nameIndex, class).bytes
        local descriptor = get(method.descriptorIndex, class).bytes
        local params = {}

        do
            local count = countParams(descriptor)

            for j = 0, count - 1 do
                params[#params + 1] = string.format('l%u', j)
            end
        end

        lua:println('[[', name, descriptor, ']] = function(', table.concat(params, ', '), ')')
        lua:indent()

        do
            local locals = {}
            
            for j = #params, method.attributes.code.maxLocals - 1 do
                locals[#locals + 1] = string.format('l%u', j)
            end

            if #locals > 0 then
                lua:println('local ', table.concat(locals, ', '))
            end
        end

        do
            local stack = {}

            for j = 1, method.attributes.code.maxStack do
                stack[#stack + 1] = string.format('s%u', j - 1)
            end

            if #stack > 0 then
                lua:println('local ', table.concat(stack, ', '))
            end
        end

        lua:eol()
        generateCode(lua, class, method.attributes.code)

        lua:unindent()
        lua:println 'end,'
        lua:eol()
    end

    lua:unindent()
    lua:println '}'
    print(lua:finish())
end
