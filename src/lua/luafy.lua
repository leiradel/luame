local utils = require 'luame.utils'
local bytecode = require 'luame.bytecode'
local crc32 = require 'luame.crc32'
local log = require 'luame.log'

local function dump(str)
    local hex = {}

    for i = 1, #str do
        hex[#hex + 1] = string.format('%02x', str:byte(i, i))
    end

    return string.format('-- %s', table.concat(hex, ' '))
end

local function invoke(lua, cpool, index, static, sp)
    local methodref = cpool[index]
    local className = cpool[cpool[methodref.classIndex].nameIndex].bytes
    local nameAndType = cpool[methodref.nameAndTypeIndex]
    local name = cpool[nameAndType.nameIndex].bytes
    local descriptor = cpool[nameAndType.descriptorIndex].bytes

    lua.scratch.imports[className] = true

    local params = {}
    local slots = utils.countSlots(descriptor)

    if not static then
        slots = slots + 1
    end

    for i = -slots, -1 do
        params[#params + 1] = string.format('s%u', sp + i)
    end

    lua:indentation()

    if not utils.isVoid(descriptor) then
        lua:write(string.format('s%u = ', sp - utils.countSlots(descriptor)))
    end

    lua:write(string.format('_%08x[%q](%s) -- %s', crc32(className), name .. descriptor, table.concat(params, ', '), className))
    lua:eol()
end

local generators = {
    [0x00] = function(vm, lua, class, sp, b)
    end,
    [0x01] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = nil')
    end,
    [0x02] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = -1')
    end,
    [0x03] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 0')
    end,
    [0x04] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 1')
    end,
    [0x05] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 2')
    end,
    [0x06] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 3')
    end,
    [0x07] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 4')
    end,
    [0x08] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 5')
    end,
    [0x09] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 0')
    end,
    [0x0a] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 1')
    end,
    [0x0b] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 0')
    end,
    [0x0c] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 1')
    end,
    [0x0d] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 2')
    end,
    [0x0e] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 0')
    end,
    [0x0f] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = 1')
    end,
    [0x10] = function(vm, lua, class, sp, b)
        local byte = b:read 'B'
        lua:println('s', sp, ' = byteToInt(', byte, ')')
    end,
    [0x11] = function(vm, lua, class, sp, b)
        local short = b:read 's'
        lua:println('s', sp, ' = shortToInt(', short, ')')
    end,
    [0x12] = function(vm, lua, class, sp, b)
        local constant = class.constantPool[b:read '1']
        local value = constant.bytes

        if constant.tag == 'string' then
            value = string.format('%q', class.constantPool[constant.stringIndex].bytes)
        end

        lua:println('s', sp, ' = ', value)
    end,
    [0x13] = function(vm, lua, class, sp, b)
        local constant = class.constantPool[b:read '2']
        local value = constant.bytes

        if constant.tag == 'string' then
            value = string.format('%q', class.constantPool[constant.stringIndex].bytes)
        end

        lua:println('s', sp, ' = ', value)
    end,
    [0x14] = function(vm, lua, class, sp, b)
        local constant = class.constantPool[b:read '2']
        local value = constant.bytes
        lua:println('s', sp, ' = ', value)
    end,
    [0x15] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1')
    end,
    [0x16] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1')
    end,
    [0x17] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1')
    end,
    [0x18] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1')
    end,
    [0x19] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l', b:read '1')
    end,
    [0x1a] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l0')
    end,
    [0x1b] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l1')
    end,
    [0x1c] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l2')
    end,
    [0x1d] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l3')
    end,
    [0x1e] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l0')
    end,
    [0x1f] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l1')
    end,
    [0x20] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l2')
    end,
    [0x21] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l3')
    end,
    [0x22] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l0')
    end,
    [0x23] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l1')
    end,
    [0x24] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l2')
    end,
    [0x25] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l3')
    end,
    [0x26] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l0')
    end,
    [0x27] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l1')
    end,
    [0x28] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l2')
    end,
    [0x29] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l3')
    end,
    [0x2a] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l0')
    end,
    [0x2b] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l1')
    end,
    [0x2c] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l2')
    end,
    [0x2d] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = l3')
    end,
    [0x2e] = function(vm, lua, class, sp, b)
        lua:println('throwNullPointerException(s', sp - 2, ')')
        lua:println('throwArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
    end,
    [0x2f] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
    end,
    [0x30] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
    end,
    [0x31] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
    end,
    [0x32] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = s', sp - 2, '[s', sp - 1, ']')
    end,
    [0x33] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = byteToInt(s', sp - 2, '[s', sp - 1, '])')
    end,
    [0x34] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = charToInt(s', sp - 2, '[s', sp - 1, '])')
    end,
    [0x35] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 2, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 2, ', s', sp - 1, ')')
        lua:println('s', sp - 2, ' = shortToInt(s', sp - 2, '[s', sp - 1, '])')
    end,
    [0x36] = function(vm, lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1)
    end,
    [0x37] = function(vm, lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1)
    end,
    [0x38] = function(vm, lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1)
    end,
    [0x39] = function(vm, lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1)
    end,
    [0x3a] = function(vm, lua, class, sp, b)
        lua:println('l', b:read '1', ' = s', sp - 1)
    end,
    [0x3b] = function(vm, lua, class, sp, b)
        lua:println('l0 = s', sp - 1)
    end,
    [0x3c] = function(vm, lua, class, sp, b)
        lua:println('l1 = s', sp - 1)
    end,
    [0x3d] = function(vm, lua, class, sp, b)
        lua:println('l2 = s', sp - 1)
    end,
    [0x3e] = function(vm, lua, class, sp, b)
        lua:println('l3 = s', sp - 1)
    end,
    [0x3f] = function(vm, lua, class, sp, b)
        lua:println('l0 = s', sp - 1)
    end,
    [0x40] = function(vm, lua, class, sp, b)
        lua:println('l1 = s', sp - 1)
    end,
    [0x41] = function(vm, lua, class, sp, b)
        lua:println('l2 = s', sp - 1)
    end,
    [0x42] = function(vm, lua, class, sp, b)
        lua:println('l3 = s', sp - 1)
    end,
    [0x43] = function(vm, lua, class, sp, b)
        lua:println('l0 = s', sp - 1)
    end,
    [0x44] = function(vm, lua, class, sp, b)
        lua:println('l1 = s', sp - 1)
    end,
    [0x45] = function(vm, lua, class, sp, b)
        lua:println('l2 = s', sp - 1)
    end,
    [0x46] = function(vm, lua, class, sp, b)
        lua:println('l3 = s', sp - 1)
    end,
    [0x47] = function(vm, lua, class, sp, b)
        lua:println('l0 = s', sp - 1)
    end,
    [0x48] = function(vm, lua, class, sp, b)
        lua:println('l1 = s', sp - 1)
    end,
    [0x49] = function(vm, lua, class, sp, b)
        lua:println('l2 = s', sp - 1)
    end,
    [0x4a] = function(vm, lua, class, sp, b)
        lua:println('l3 = s', sp - 1)
    end,
    [0x4b] = function(vm, lua, class, sp, b)
        lua:println('l0 = s', sp - 1)
    end,
    [0x4c] = function(vm, lua, class, sp, b)
        lua:println('l1 = s', sp - 1)
    end,
    [0x4d] = function(vm, lua, class, sp, b)
        lua:println('l2 = s', sp - 1)
    end,
    [0x4e] = function(vm, lua, class, sp, b)
        lua:println('l3 = s', sp - 1)
    end,
    [0x4f] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
    end,
    [0x50] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
    end,
    [0x51] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
    end,
    [0x52] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
    end,
    [0x53] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = s', sp - 1)
    end,
    [0x54] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = intToByte(s', sp - 1, ')')
    end,
    [0x55] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = intToChar(s', sp - 1, ')')
    end,
    [0x56] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 3, ')')
        lua:println('maybeThrowArrayIndexOutOfBoundsException(s', sp - 3, ', s', sp - 2, ')')
        lua:println('s', sp - 3, '[s', sp - 2, '] = intToShort(s', sp - 1, ')')
    end,
    [0x57] = function(vm, lua, class, sp, b)
    end,
    [0x59] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = s', sp - 1)
    end,
    [0x5a] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = s', sp - 1)
        lua:println('s', sp - 1, ' = s', sp - 2)
        lua:println('s', sp - 2, ' = s', sp)
    end,
    [0x5b] = function(vm, lua, class, sp, b)
        lua:println('s', sp, ' = s', sp - 1)
        lua:println('s', sp - 1, ' = s', sp - 2)
        lua:println('s', sp - 2, ' = s', sp - 3)
        lua:println('s', sp - 3, ' = s', sp)
    end,
    [0x5c] = function(vm, lua, class, sp, b)
        lua:println('s', sp + 1, ' = s', sp - 1)
        lua:println('s', sp, ' = s', sp - 2)
    end,
    [0x5d] = function(vm, lua, class, sp, b)
        lua:println('s', sp + 1, ' = s', sp - 1)
        lua:println('s', sp + 0, ' = s', sp - 2)
        lua:println('s', sp - 1, ' = s', sp - 3)
        lua:println('s', sp - 2, ' = s', sp + 1)
        lua:println('s', sp - 3, ' = s', sp + 0)
    end,
    [0x5e] = function(vm, lua, class, sp, b)
        lua:println('s', sp + 1, ' = s', sp - 1)
        lua:println('s', sp + 0, ' = s', sp - 2)
        lua:println('s', sp - 1, ' = s', sp - 3)
        lua:println('s', sp - 2, ' = s', sp - 4)
        lua:println('s', sp - 3, ' = s', sp + 1)
        lua:println('s', sp - 4, ' = s', sp + 0)
    end,
    [0x5f] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ', s', sp - 2, ' = s', sp - 2, ', s', sp - 1)
    end,
    [0x60] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' + s', sp - 1)
        return sp - 1
    end,
    [0x61] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' + s', sp - 1)
        return sp - 1
    end,
    [0x62] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' + s', sp - 1)
        return sp - 1
    end,
    [0x63] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' + s', sp - 1)
        return sp - 1
    end,
    [0x64] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' - s', sp - 1)
        return sp - 1
    end,
    [0x65] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' - s', sp - 1)
        return sp - 1
    end,
    [0x66] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' - s', sp - 1)
        return sp - 1
    end,
    [0x67] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' - s', sp - 1)
        return sp - 1
    end,
    [0x68] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' * s', sp - 1)
        return sp - 1
    end,
    [0x69] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' * s', sp - 1)
        return sp - 1
    end,
    [0x6a] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' * s', sp - 1)
        return sp - 1
    end,
    [0x6b] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' * s', sp - 1)
        return sp - 1
    end,
    [0x6c] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' // s', sp - 1)
        return sp - 1
    end,
    [0x6d] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' // s', sp - 1)
        return sp - 1
    end,
    [0x6e] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' / s', sp - 1)
        return sp - 1
    end,
    [0x6f] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' / s', sp - 1)
        return sp - 1
    end,
    [0x70] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' % s', sp - 1)
        return sp - 1
    end,
    [0x71] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' % s', sp - 1)
        return sp - 1
    end,
    [0x72] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' % s', sp - 1)
        return sp - 1
    end,
    [0x73] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' % s', sp - 1)
        return sp - 1
    end,
    [0x74] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = -s', sp - 1)
    end,
    [0x75] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = -s', sp - 1)
    end,
    [0x76] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = -s', sp - 1)
    end,
    [0x77] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = -s', sp - 1)
    end,
    [0x78] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' << s', sp - 1)
        return sp - 1
    end,
    [0x79] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' << s', sp - 1)
        return sp - 1
    end,
    [0x7a] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' >> s', sp - 1)
        return sp - 1
    end,
    [0x7b] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' >> s', sp - 1)
        return sp - 1
    end,
    [0x7c] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' >> s', sp - 1)
        return sp - 1
    end,
    [0x7d] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' >> s', sp - 1)
        return sp - 1
    end,
    [0x7e] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' & s', sp - 1)
        return sp - 1
    end,
    [0x7f] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' & s', sp - 1)
        return sp - 1
    end,
    [0x80] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' | s', sp - 1)
        return sp - 1
    end,
    [0x81] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' | s', sp - 1)
        return sp - 1
    end,
    [0x82] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' ~ s', sp - 1)
        return sp - 1
    end,
    [0x83] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = s', sp - 2, ' ~ s', sp - 1)
        return sp - 1
    end,
    [0x84] = function(vm, lua, class, sp, b)
        local index = b:read('1')
        local const = b:read('B')
        lua:println('l', index, ' = l', index, ' + ', const)
    end,
    [0x85] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToLong(s', sp - 1, ')')
    end,
    [0x86] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToFloar(s', sp - 1, ')')
    end,
    [0x87] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToDouble(s', sp - 1, ')')
    end,
    [0x88] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = longToInt(s', sp - 1, ')')
    end,
    [0x89] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = longToFloat(s', sp - 1, ')')
    end,
    [0x8a] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = longToDouble(s', sp - 1, ')')
    end,
    [0x8b] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = floatToInt(s', sp - 1, ')')
    end,
    [0x8c] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = floatToLong(s', sp - 1, ')')
    end,
    [0x8d] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = floatToDouble(s', sp - 1, ')')
    end,
    [0x8e] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = doubleToInt(s', sp - 1, ')')
    end,
    [0x8f] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = doubleToLong(s', sp - 1, ')')
    end,
    [0x90] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = doubleToFloat(s', sp - 1, ')')
    end,
    [0x91] = function(vm, lua, class, sp, b)
        lua_println('s', sp - 1, ' = intToByte(s', sp - 1, ')')
    end,
    [0x92] = function(vm, lua, class, sp, b)
        lua_println('s', sp - 1, ' = intToChar(s', sp - 1, ')')
    end,
    [0x93] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 1, ' = intToShort(s', sp - 1, ')')
    end,
    [0x94] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = longCompare(s', sp - 2, ', s', sp - 1, ')')
        return sp - 1
    end,
    [0x95] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = floatCompareLess(s', sp - 2, ', s', sp - 1, ')')
        return sp - 1
    end,
    [0x96] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = floatCompareGreater(s', sp - 2, ', s', sp - 1, ')')
        return sp - 1
    end,
    [0x97] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = doubleCompareLess(s', sp - 2, ', s', sp - 1, ')')
        return sp - 1
    end,
    [0x98] = function(vm, lua, class, sp, b)
        lua:println('s', sp - 2, ' = doubleCompareGreater(s', sp - 2, ', s', sp - 1, ')')
        return sp - 1
    end,
    [0x99] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' == 0 then goto ', label)
        return sp - 1
    end,
    [0x9a] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' ~= 0 then goto ', label)
        return sp - 1
    end,
    [0x9b] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' < 0 then goto ', label)
        return sp - 1
    end,
    [0x9c] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' >= 0 then goto ', label)
        return sp - 1
    end,
    [0x9d] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' > 0 then goto ', label)
        return sp - 1
    end,
    [0x9e] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' <= 0 then goto ', label)
        return sp - 1
    end,
    [0x9f] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' == s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa0] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' ~= s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa1] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' < s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa2] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' >= s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa3] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' > s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa4] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' <= s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa5] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' == s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa6] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 2, ' ~= s', sp - 1, ' then goto ', label)
        return sp - 2
    end,
    [0xa7] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('goto ', label)
    end,
    [0xa8] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 's'
        local label = string.format('_%04x', pc + offset)
        lua:println('s', sp, ' = ', pc + 3)
        lua:println('goto ', label)
        return sp + 1
    end,
    [0xa9] = function(vm, lua, class, sp, b)
        lua:println(dump(b:read(1)))
    end,
    [0xaa] = function(vm, lua, class, sp, b)
        -- TODO generate a binary search.
        local pc = b:tell() - 1
        b:seek((pc + 1 + 3) & ~3, 'set')
        local default = b:read 'I'
        local low = b:read 'I'
        local high = b:read 'I'
        local npairs = high - low + 1

        lua:println 'do'
        lua:println('local index = s', sp - 1, ' - ', low)

        for i = 1, npairs do
            local offset = b:read 'I'
            local label = string.format('_%04x', pc + offset)
            lua:println(i == 1 and '    if' or '    elseif', ' index == ', i - 1, ' then goto _', label)
        end

        if npairs == 0 then
            lua:println('    goto ', string.format('_%04x', pc + default))
        else
            lua:println('    else goto ', string.format('_%04x', pc + default))
            lua:println '    end'
        end

        lua:prinln 'end'
        return sp - 1
    end,
    [0xab] = function(vm, lua, class, sp, b)
        -- TODO generate a binary search.
        local pc = b:tell() - 1
        b:seek((pc + 1 + 3) & ~3, 'set')
        local default = b:read 'I'
        local npairs = b:read 'I'

        for i = 1, npairs do
            local match = b:read 'I'
            local offset = b:read 'I'
            local label = string.format('_%04x', pc + offset)
            lua:println(i == 1 and 'if' or 'elseif', ' s', sp - 1, ' == ', match, ' then goto _', label)
        end

        if npairs == 0 then
            lua:println('goto ', string.format('_%04x', pc + default))
        else
            lua:println('else goto ', string.format('_%04x', pc + default))
            lua:println('end')
        end

        return sp - 1
    end,
    [0xac] = function(vm, lua, class, sp, b)
        lua:println('return s', sp - 1)
        return 0
    end,
    [0xad] = function(vm, lua, class, sp, b)
        lua:println('return s', sp - 1)
        return 0
    end,
    [0xae] = function(vm, lua, class, sp, b)
        lua:println('return s', sp - 1)
        return 0
    end,
    [0xaf] = function(vm, lua, class, sp, b)
        lua:println('return s', sp - 1)
        return 0
    end,
    [0xb0] = function(vm, lua, class, sp, b)
        lua:println('return s', sp - 1)
        return 0
    end,
    [0xb1] = function(vm, lua, class, sp, b)
        lua:println('return')
        return 0
    end,
    [0xb2] = function(vm, lua, class, sp, b)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes

        lua.scratch.imports[className] = true

        lua:println(string.format('s%u = _%08x[%q] -- %s %s', sp, crc32(className), name, className, descriptor))
    end,
    [0xb3] = function(vm, lua, class, sp, b)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes

        lua.scratch.imports[className] = true

        lua:println(string.format('_%08x[%q] = s%u -- %s %s', crc32(className), name, sp - 1, className, descriptor))
    end,
    [0xb4] = function(vm, lua, class, sp, b)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes

        lua:println(string.format('s%u = s%u[%q] -- %s %s', sp - 1, sp - 1, name, className, descriptor))
    end,
    [0xb5] = function(vm, lua, class, sp, b)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes

        lua:println(string.format('s%u[%q] = s%u -- %s %s', sp - 1, name, sp - 1, className, descriptor))
    end,
    [0xb6] = function(vm, lua, class, sp, b)
        invoke(lua, class.constantPool, b:read '2', false, sp)
    end,
    [0xb7] = function(vm, lua, class, sp, b)
        invoke(lua, class.constantPool, b:read '2', false, sp)
    end,
    [0xb8] = function(vm, lua, class, sp, b)
        invoke(lua, class.constantPool, b:read '2', true, sp)
    end,
    [0xb9] = function(vm, lua, class, sp, b)
        invoke(lua, class.constantPool, b:read '2', false, sp)
    end,
    [0xbb] = function(vm, lua, class, sp, b)
        local cpool = class.constantPool
        local className = cpool[cpool[b:read '2'].nameIndex].bytes
        lua:println('s', sp, ' = {} -- ', className)
    end,
    [0xbc] = function(vm, lua, class, sp, b)
        lua:println(dump(b:read(1)))
    end,
    [0xbd] = function(vm, lua, class, sp, b)
        lua:println 'do'
        lua:println('    local n = s', sp - 1)
        lua:println('    maybeThrowNegativeArraySizeException(n)')
        lua:println('    s', sp - 1, ' = {n = n, t = \'L\'}')
        lua:println '    for i = 1, n do'
        lua:println('        s', sp - 1, '[i] = nil')
        lua:println '    end'
        lua:println 'end'
    end,
    [0xbe] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 1, ')')
        lua:println('s', sp - 1, ' = s', sp - 1, '.n')
    end,
    [0xbf] = function(vm, lua, class, sp, b)
        lua:println('maybeThrowNullPointerException(s', sp - 1, ')')
        lua:println('error(s', sp - 1, ')')
    end,
    [0xc0] = function(vm, lua, class, sp, b)
    end,
    [0xc1] = function(vm, lua, class, sp, b)
        local type = b:read '2'
        lua:println('-- ', type.tag)
    end,
    [0xc2] = function(vm, lua, class, sp, b)
        return sp - 1
    end,
    [0xc3] = function(vm, lua, class, sp, b)
        return sp - 1
    end,
    [0xc4] = function(vm, lua, class, sp, b)
        lua:println(dump(b:read(3)))
    end,
    [0xc5] = function(vm, lua, class, sp, b)
        local type = class.constantPool[b:read '2'].bytes
        local dimensions = b:read '1'
        local counts = {}

        for i = dimensions, 1, -1 do
            counts[#counts + 1] = string.format('s%u', sp - i)
        end

        count = table.concat(counts, ', ')
        lua:println('s', sp - dimensions, ' = newMultidimensionalArray("', type, '", ', count, ') -- multianewarray')
        return sp - dimensions + 1
    end,
    [0xc6] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('s')
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' == nil then goto ', label)
        return sp - 1
    end,
    [0xc7] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read('s')
        local label = string.format('_%04x', pc + offset)
        lua:println('if s', sp - 1, ' ~= nil then goto ', label)
        return sp - 1
    end,
    [0xc8] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 'I'
        local label = string.format('_%04x', pc + offset)
        lua:println('goto ', label)
    end,
    [0xc9] = function(vm, lua, class, sp, b)
        local pc = b:tell() - 1
        local offset = b:read 'I'
        local label = string.format('_%04x', pc + offset)
        lua:println '-- jsr_w'
        lua:println('s', sp, ' = ', pc + 5)
        lua:println('goto ', label)
        return sp + 1
    end,
}

local function generateCode(vm, imports, lua, class, code)
    local sp = 0
    local b = code.code

    for pc, op, name, size, deltasp in bytecode.opcodes(b, class.constantPool) do
        lua:println(string.format('-- %04x %02x %-16s %d %2d %d', pc, op, name, size, deltasp, sp))

        do
            local label = string.format('%04x', pc)

            if lua.scratch.targets[label] then
                lua:println(string.format('::_%s::', label))
            end
        end

        b:seek(pc + 1, 'set')
        generators[op](vm, lua, class, sp, b)
        
        if deltasp < 0 then
            for i = -1, deltasp, -1 do
                lua:println('s', sp + i, ' = nil')
            end
        end

        sp = sp + deltasp
        lua:eol()
    end
end

return function(vm, class)
    local lua = utils.codeGenerator()
    lua.scratch.imports = {}

    lua:println 'return {'
    lua:indent()

    for i = 1, class.methods.n do
        local method = class.methods[i]
        local name = class.constantPool[method.nameIndex].bytes
        local descriptor = class.constantPool[method.descriptorIndex].bytes

        do
            local params = {}

            for i = 1, method.attributes.code.maxLocals do
                params[i] = string.format('l%u', i - 1)
            end

            lua:println(string.format('[%q]', name .. descriptor), ' = function(', table.concat(params,', '), ')')
        end

        lua:indent()

        do
            if method.attributes.code.maxStack > 0 then
                local stack = {}

                for i = 1, method.attributes.code.maxStack do
                    stack[i] = string.format('s%u', i - 1)
                end

                lua:println('local ', table.concat(stack, ', '))
            end
        end

        lua:eol()

        do
            local near = {
                ['goto'] = true, jsr = true,
                if_acmpeq = true, if_acmpne = true,
                if_icmpeq = true, if_icmpne = true, if_icmplt = true, if_icmpge = true, if_icmpgt = true, if_icmple = true,
                ifeq = true, ifne = true, iflt = true, ifge = true, ifgt = true, ifle = true,
                ifnonnull = true, ifnull = true,
            }

            lua.scratch.targets = {}
            lua.scratch.jsrTargets = {}

            local b = method.attributes.code.code

            for pc, op, name, size, deltasp in bytecode.opcodes(b) do
                local source, target

                b:seek(pc + 1, 'set')

                if near[name] then
                    source, target = pc, pc + b:read 's'
                elseif name == 'goto_w' or name == 'jsr_w' then
                    source, target = pc, pc + b:read 'I'
                end

                if source then
                    source = string.format('%04x', source)
                    target = string.format('%04x', target)

                    lua.scratch.targets[target] = true

                    if name == 'jsr' or name == 'jsr_w' then
                        local sources = lua.scratch.jsrTargets[target] or {}
                        lua.scratch.jsrTargets = sources

                        sources[#sources + 1] = source
                    end
                end
            end
        end

        generateCode(vm, imports, lua, class, method.attributes.code)

        lua:unindent()
        lua:println 'end,'
        lua:eol()
    end

    lua:unindent()
    lua:println '}'

    lua:swap()

    for className in pairs(lua.scratch.imports) do
        lua:println(string.format('local _%08x = vm:load(%q)', crc32(className), className))
    end

    lua:eol()
    lua:swap()

    print(lua:finish())
end
