local utils = require 'luame.utils'
local bytecode = require 'luame.bytecode'
local crc32 = require 'luame.crc32'
local log = require 'luame.log'

local f = string.format
local r = string.rep

local function i(str)
    if str:match('^[a-zA-Z_][a-zA-Z_0-9]*$') then
        return f('.%s', str)
    else
        return f('[%q]', str)
    end
end

local function collectJumps(method, cpool)
    local near = {
        ['goto'] = true, jsr = true,
        if_acmpeq = true, if_acmpne = true,
        if_icmpeq = true, if_icmpne = true, if_icmplt = true, if_icmpge = true, if_icmpgt = true, if_icmple = true,
        ifeq = true, ifne = true, iflt = true, ifge = true, ifgt = true, ifle = true,
        ifnonnull = true, ifnull = true,
    }

    local b = method.attributes.code.code
    local jumps = {}
    local sp = 0

    for pc, op, name, size, deltasp in bytecode.opcodes(b, cpool) do
        sp = sp + deltasp -- compute SP for *after* the insn is executed!
        b:seek(pc + 1, 'set')

        if near[name] then
            local target = pc + b:read 's'
            jumps[#jumps + 1] = {source = pc, target = target, sp = sp, jsr = name == 'jsr'}
        elseif name == 'goto_w' or name == 'jsr_w' then
            local target = pc + b:read 'I'
            jumps[#jumps + 1] = {source = pc, target = target, sp = sp, jsr = name == 'jsr_w'}
        elseif name == 'lookupswitch' then
            local pc = b:tell() - 1
            b:seek((pc + 1 + 3) & ~3, 'set')
            local default = b:read 'I'
            local npairs = b:read 'I'
    
            jumps[#jumps + 1] = {source = pc, target = pc + default, sp = sp, jsr = false}
            
            for i = 1, npairs do
                local match = b:read 'I'
                local offset = b:read 'I'
                jumps[#jumps + 1] = {source = pc, target = pc + offset, sp = sp, jsr = false}
            end
        elseif name == 'tableswitch' then
            local pc = b:tell() - 1
            b:seek((pc + 1 + 3) & ~3, 'set')
            local default = b:read 'I'
            local low = b:read 'I'
            local high = b:read 'I'
            local npairs = high - low + 1

            jumps[#jumps + 1] = {source = pc, target = pc + default, sp = sp, jsr = false}

            for i = 1, npairs do
                local offset = b:read 'I'
                jumps[#jumps + 1] = {source = pc, target = pc + offset, sp = sp, jsr = false}
            end
        end
    end

    local et = method.attributes.code.exceptionTable

    for i = 1, et.n do
        local e = et[i]
        jumps[#jumps + 1] = {source = e.startPc, target = e.handlerPc, sp = 1, jsr = false}
    end

    jumps.n = #jumps
    return jumps
end

local function binop(code, sp, operator)
    code:println('s%u = %s(s%u, s%u)', sp - 2, operator, sp - 1, sp - 1)
    return -1
end

local function binop2(code, sp, operator)
    code:println('s%u = %s(s%u, s%u)', sp - 4, operator, sp - 2, sp - 2)
    return -2
end

local function generateIf(code, sp, b, operator, setnil)
    local pc = b:tell() - 1
    local offset = b:read 's'

    if setnil then
        code:println 'do'
        code:println('    local test1, test2 = s%u, s%u', sp - 2, sp - 1)
        code:println('    s%u, s%u = nil, nil', sp - 1, sp - 2)
        code:println('    if test1 %s test2 then goto _%04x end', operator, pc + offset)
        code:println 'end'
    else
        code:println('if s%u %s s%u then goto _%04x end', sp - 2, operator, sp - 1, pc + offset)
    end

    return -2
end

local function generateIf0(code, sp, b, operator)
    local pc = b:tell() - 1
    local offset = b:read 's'
    code:println('if s%u %s 0 then goto _%04x end', sp - 1, operator, pc + offset)
    return -1
end

local function generateInvoke(code, imports, sp, b, class, index, static)
    local cpool = class.constantPool
    local methodref = cpool[index]
    local className = cpool[cpool[methodref.classIndex].nameIndex].bytes
    local nameAndType = cpool[methodref.nameAndTypeIndex]
    local name = cpool[nameAndType.nameIndex].bytes
    local descriptor = cpool[nameAndType.descriptorIndex].bytes

    imports[className] = 'class'

    local slots = utils.countSlots(descriptor)

    if not static then
        slots = slots + 1
    end

    code:println('-- %s.%s%s', className, name, descriptor)
    code:indentation()

    if not utils.isVoid(descriptor) then
        code:write('s%u = ', sp - slots)
    end

    code:write('_%08x%s(', crc32(className), i(name .. descriptor))
    local comma = ''

    for i = -slots, -1 do
        code:write('%ss%u', comma, sp + i)
        comma = ', '
    end

    code:write(')')
    code:eol()

    if not static then
        if utils.isVoid(descriptor) then
            code:println('s%u = nil', sp - slots)
        end

        slots = slots - 1
    end

    for _, descriptor, s in utils.params(descriptor) do
        if descriptor:byte(1, 1) == 76 then -- L
            code:println('s%u = nil', sp - slots)
        end

        slots = slots - s
    end
end

local generators = {
    -- 00 nop
    [0] = function(code, imports, sp, b, class)
        return 0
    end,
    -- 01 aconst_null
    function(code, imports, sp, b, class)
        code:println('s%u = nil', sp)
    end,
    -- 02 iconst_m1
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(-1)', sp)
    end,
    -- 03 iconst_0
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(0)', sp)
    end,
    -- 04 iconst_1
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(1)', sp)
    end,
    -- 05 iconst_2
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(2)', sp)
    end,
    -- 06 iconst_3
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(3)', sp)
    end,
    -- 07 iconst_4
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(4)', sp)
    end,
    -- 08 iconst_5
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(5)', sp)
    end,
    -- 09 lconst_0
    function(code, imports, sp, b, class)
        code:println('s%u = 0', sp)
    end,
    -- 0a lconst_1
    function(code, imports, sp, b, class)
        code:println('s%u = 1', sp)
    end,
    -- 0b fconst_0
    function(code, imports, sp, b, class)
        code:println('s%u = l2f(0)', sp)
    end,
    -- 0c fconst_1
    function(code, imports, sp, b, class)
        code:println('s%u = l2f(1)', sp)
    end,
    -- 0d fconst_2
    function(code, imports, sp, b, class)
        code:println('s%u = l2f(2)', sp)
    end,
    -- 0e dconst_0
    function(code, imports, sp, b, class)
        code:println('s%u = l2d(0)', sp)
    end,
    -- 0f dconst_1
    function(code, imports, sp, b, class)
        code:println('s%u = l2d(1)', sp)
    end,
    -- 10 bipush
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(%d)', sp, b:read 'B')
    end,
    -- 11 sipush
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(%d)', sp, b:read 's')
    end,
    -- 12 ldc
    function(code, imports, sp, b, class)
        local cpool = class.constantPool
        local const = cpool[b:read '1']
        local value = const.bytes

        if const.tag == 'string' then
            value = cpool[const.stringIndex].bytes
            imports[value] = 'string'
            code:println('s%u = _%08x -- %q', sp, crc32(value), value)
        elseif const.tag == 'int' then
            code:println('s%u = l2i(%d)', sp, value)
        else
            code:println('s%u = l2f(%d)', sp, value)
        end
    end,
    -- 13 ldc_w
    function(code, imports, sp, b, class)
        local cpool = class.constantPool
        local const = cpool[b:read '2']
        local value = const.bytes

        if const.tag == 'string' then
            value = cpool[const.stringIndex].bytes
            imports[value] = 'string'
            code:println('s%u = _%08x -- %q', sp, crc32(value), value)
        elseif const.tag == 'int' then
            code:println('s%u = l2i(%d)', sp, value)
        else
            code:println('s%u = l2f(%d)', sp, value)
        end
    end,
    -- 14 ldc2_w
    function(code, imports, sp, b, class)
        local const = class.constantPool[b:read '2']
        local value = const.bytes

        if const.tag == 'long' then
            code:println('s%u = %d', sp, value)
        else
            code:println('s%u = l2d(%d)', sp, value)
        end
    end,
    -- 15 iload
    function(code, imports, sp, b, class)
        code:println('s%u = l%u', sp, b:read '1')
    end,
    -- 16 lload
    function(code, imports, sp, b, class)
        code:println('s%u = l%u', sp, b:read '1')
    end,
    -- 17 fload
    function(code, imports, sp, b, class)
        code:println('s%u = l%u', sp, b:read '1')
    end,
    -- 18 dload
    function(code, imports, sp, b, class)
        code:println('s%u = l%u', sp, b:read '1')
    end,
    -- 19 aload
    function(code, imports, sp, b, class)
        code:println('s%u = l%u', sp, b:read '1')
    end,
    -- 1a iload_0
    function(code, imports, sp, b, class)
        code:println('s%u = l0', sp)
    end,
    -- 1b iload_1
    function(code, imports, sp, b, class)
        code:println('s%u = l1', sp)
    end,
    -- 1c iload_2
    function(code, imports, sp, b, class)
        code:println('s%u = l2', sp)
    end,
    -- 1d iload_3
    function(code, imports, sp, b, class)
        code:println('s%u = l3', sp)
    end,
    -- 1e lload_0
    function(code, imports, sp, b, class)
        code:println('s%u = l0', sp)
    end,
    -- 1f lload_1
    function(code, imports, sp, b, class)
        code:println('s%u = l1', sp)
    end,
    -- 20 lload_2
    function(code, imports, sp, b, class)
        code:println('s%u = l2', sp)
    end,
    -- 21 lload_3
    function(code, imports, sp, b, class)
        code:println('s%u = l3', sp)
    end,
    -- 22 fload_0
    function(code, imports, sp, b, class)
        code:println('s%u = l0', sp)
    end,
    -- 23 fload_1
    function(code, imports, sp, b, class)
        code:println('s%u = l1', sp)
    end,
    -- 24 fload_2
    function(code, imports, sp, b, class)
        code:println('s%u = l2', sp)
    end,
    -- 25 fload_3
    function(code, imports, sp, b, class)
        code:println('s%u = l3', sp)
    end,
    -- 26 dload_0
    function(code, imports, sp, b, class)
        code:println('s%u = l0', sp)
    end,
    -- 27 dload_1
    function(code, imports, sp, b, class)
        code:println('s%u = l1', sp)
    end,
    -- 28 dload_2
    function(code, imports, sp, b, class)
        code:println('s%u = l2', sp)
    end,
    -- 29 dload_3
    function(code, imports, sp, b, class)
        code:println('s%u = l3', sp)
    end,
    -- 2a aload_0
    function(code, imports, sp, b, class)
        code:println('s%u = l0', sp)
    end,
    -- 2b aload_1
    function(code, imports, sp, b, class)
        code:println('s%u = l1', sp)
    end,
    -- 2c aload_2
    function(code, imports, sp, b, class)
        code:println('s%u = l2', sp)
    end,
    -- 2d aload_3
    function(code, imports, sp, b, class)
        code:println('s%u = l3', sp)
    end,
    -- 2e iaload
    function(code, imports, sp, b, class)
        code:println('throwNullPointerException(s%u)', sp - 2)
        code:println('throwArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 2f laload
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 2)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 30 faload
    function(code, imports, sp, b, class)
        code:println('throwNullPointerException(s%u)', sp - 2)
        code:println('throwArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 31 daload
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 2)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 32 aaload
    function(code, imports, sp, b, class)
        code:println('throwNullPointerException(s%u)', sp - 2)
        code:println('throwArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 33 baload
    function(code, imports, sp, b, class)
        code:println('throwNullPointerException(s%u)', sp - 2)
        code:println('throwArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 34 caload
    function(code, imports, sp, b, class)
        code:println('throwNullPointerException(s%u)', sp - 2)
        code:println('throwArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 35 saload
    function(code, imports, sp, b, class)
        code:println('throwNullPointerException(s%u)', sp - 2)
        code:println('throwArrayIndexOutOfBoundsException(s%u, s%u)', sp - 2, sp - 1)
        code:println('s%u = s%u[s%u]', sp - 2, sp - 2, sp - 1)
    end,
    -- 36 istore
    function(code, imports, sp, b, class)
        code:println('l%u = s%u', b:read '1', sp - 1)
    end,
    -- 37 lstore
    function(code, imports, sp, b, class)
        code:println('l%u = s%u', b:read '1', sp - 2)
    end,
    -- 38 fstore
    function(code, imports, sp, b, class)
        code:println('l%u = s%u', b:read '1', sp - 1)
    end,
    -- 39 dstore
    function(code, imports, sp, b, class)
        code:println('l%u = s%u', b:read '1', sp - 2)
    end,
    -- 3a astore
    function(code, imports, sp, b, class)
        code:println('l%u = s%u', b:read '1', sp - 1)
        code:println('s%u = nil', sp - 1)
    end,
    -- 3b istore_0
    function(code, imports, sp, b, class)
        code:println('l0 = s%u', sp - 1)
    end,
    -- 3c istore_1
    function(code, imports, sp, b, class)
        code:println('l1 = s%u', sp - 1)
    end,
    -- 3d istore_2
    function(code, imports, sp, b, class)
        code:println('l2 = s%u', sp - 1)
    end,
    -- 3e istore_3
    function(code, imports, sp, b, class)
        code:println('l3 = s%u', sp - 1)
    end,
    -- 3f lstore_0
    function(code, imports, sp, b, class)
        code:println('l0 = s%u', sp - 1)
    end,
    -- 40 lstore_1
    function(code, imports, sp, b, class)
        code:println('l1 = s%u', sp - 1)
    end,
    -- 41 lstore_2
    function(code, imports, sp, b, class)
        code:println('l2 = s%u', sp - 1)
    end,
    -- 42 lstore_3
    function(code, imports, sp, b, class)
        code:println('l3 = s%u', sp - 1)
    end,
    -- 43 fstore_0
    function(code, imports, sp, b, class)
        code:println('l0 = s%u', sp - 1)
    end,
    -- 44 fstore_1
    function(code, imports, sp, b, class)
        code:println('l1 = s%u', sp - 1)
    end,
    -- 45 fstore_2
    function(code, imports, sp, b, class)
        code:println('l2 = s%u', sp - 1)
    end,
    -- 46 fstore_3
    function(code, imports, sp, b, class)
        code:println('l3 = s%u', sp - 1)
    end,
    -- 47 dstore_0
    function(code, imports, sp, b, class)
        code:println('l0 = s%u', sp - 1)
    end,
    -- 48 dstore_1
    function(code, imports, sp, b, class)
        code:println('l1 = s%u', sp - 1)
    end,
    -- 49 dstore_2
    function(code, imports, sp, b, class)
        code:println('l2 = s%u', sp - 1)
    end,
    -- 4a dstore_3
    function(code, imports, sp, b, class)
        code:println('l3 = s%u', sp - 1)
    end,
    -- 4b astore_0
    function(code, imports, sp, b, class)
        code:println('l0 = s%u', sp - 1)
        code:println('s%u = nil', sp - 1)
    end,
    -- 4c astore_1
    function(code, imports, sp, b, class)
        code:println('l1 = s%u', sp - 1)
        code:println('s%u = nil', sp - 1)
    end,
    -- 4d astore_2
    function(code, imports, sp, b, class)
        code:println('l2 = s%u', sp - 1)
        code:println('s%u = nil', sp - 1)
    end,
    -- 4e astore_3
    function(code, imports, sp, b, class)
        code:println('l3 = s%u', sp - 1)
        code:println('s%u = nil', sp - 1)
    end,
    -- 4f iastore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 3)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 3, sp - 2)
        code:println('s%u[s%u] = s%u', sp - 3, sp - 2, sp - 1)
        code:println('s%u = nil', sp - 3)
    end,
    -- 50 lastore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 4)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 4, sp - 3)
        code:println('s%u[s%u] = s%u', sp - 4, sp - 3, sp - 2)
        code:println('s%u = nil', sp - 4)
    end,
    -- 51 fastore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 3)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 3, sp - 2)
        code:println('s%u[s%u] = s%u', sp - 3, sp - 2, sp - 1)
        code:println('s%u = nil', sp - 3)
    end,
    -- 52 dastore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 4)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 4, sp - 3)
        code:println('s%u[s%u] = s%u', sp - 4, sp - 3, sp - 2)
        code:println('s%u = nil', sp - 4)
    end,
    -- 53 aastore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 3)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 3, sp - 2)
        code:println('s%u[s%u] = s%u', sp - 3, sp - 2, sp - 1)
        code:println('s%u = nil', sp - 1)
        code:println('s%u = nil', sp - 3)
    end,
    -- 54 bastore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 3)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 3, sp - 2)
        code:println('s%u[s%u] = s%u', sp - 3, sp - 2, sp - 1)
        code:println('s%u = nil', sp - 3)
    end,
    -- 55 castore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 3)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 3, sp - 2)
        code:println('s%u[s%u] = s%u', sp - 3, sp - 2, sp - 1)
        code:println('s%u = nil', sp - 3)
    end,
    -- 56 sastore
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 3)
        code:println('maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)', sp - 3, sp - 2)
        code:println('s%u[s%u] = s%u', sp - 3, sp - 2, sp - 1)
        code:println('s%u = nil', sp - 3)
    end,
    -- 57 pop
    function(code, imports, sp, b, class)
        code:println('s%u = nil', sp - 1)
    end,
    -- 58 pop2
    function(code, imports, sp, b, class)
        code:println('s%u = nil', sp - 1)
        code:println('s%u = nil', sp - 2)
    end,
    -- 59 dup
    function(code, imports, sp, b, class)
        code:println('s%u = s%u', sp, sp - 1)
    end,
    -- 5a dup_x1
    function(code, imports, sp, b, class)
        code:println('s%u = s%u', sp,     sp - 1)
        code:println('s%u = s%u', sp - 1, sp - 2)
        code:println('s%u = s%u', sp - 2, sp)
    end,
    -- 5b dup_x2
    function(code, imports, sp, b, class)
        code:println('s%u = s%u', sp,     sp - 1)
        code:println('s%u = s%u', sp - 1, sp - 2)
        code:println('s%u = s%u', sp - 2, sp - 3)
        code:println('s%u = s%u', sp - 3, sp)
    end,
    -- 5c dup2
    function(code, imports, sp, b, class)
        code:println('s%u = s%u', sp + 1, sp - 1)
        code:println('s%u = s%u', sp,     sp - 2)
    end,
    -- 5d dup2_x1
    function(code, imports, sp, b, class)
        code:println('s%u = s%u', sp + 1, sp - 1)
        code:println('s%u = s%u', sp,     sp - 2)
        code:println('s%u = s%u', sp - 1, sp - 3)
        code:println('s%u = s%u', sp - 2, sp + 1)
        code:println('s%u = s%u', sp - 3, sp)
    end,
    -- 5e dup2_x2
    function(code, imports, sp, b, class)
        code:println('s%u = s%u', sp + 1, sp - 1)
        code:println('s%u = s%u', sp,     sp - 2)
        code:println('s%u = s%u', sp - 1, sp - 3)
        code:println('s%u = s%u', sp - 2, sp - 4)
        code:println('s%u = s%u', sp - 3, sp + 1)
        code:println('s%u = s%u', sp - 4, sp)
    end,
    -- 5f swap
    function(code, imports, sp, b, class)
        code:println('s%u, s%u = s%u, s%u', sp - 1, sp - 2, sp - 2, sp - 1)
    end,
    -- 60 iadd
    function(code, imports, sp, b, class)
        binop(code, sp, 'iadd')
    end,
    -- 61 ladd
    function(code, imports, sp, b, class)
        binop2(code, sp, 'ladd')
    end,
    -- 62 fadd
    function(code, imports, sp, b, class)
        binop(code, sp, 'fadd')
    end,
    -- 63 dadd
    function(code, imports, sp, b, class)
        binop2(code, sp, 'dadd')
    end,
    -- 64 isub
    function(code, imports, sp, b, class)
        binop(code, sp, 'isub')
    end,
    -- 65 lsub
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lsub')
    end,
    -- 66 fsub
    function(code, imports, sp, b, class)
        binop(code, sp, 'fsub')
    end,
    -- 67 dsub
    function(code, imports, sp, b, class)
        binop2(code, sp, 'dsub')
    end,
    -- 68 imul
    function(code, imports, sp, b, class)
        binop(code, sp, 'imul')
    end,
    -- 69 lmul
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lmul')
    end,
    -- 6a fmul
    function(code, imports, sp, b, class)
        binop(code, sp, 'fmul')
    end,
    -- 6b dmul
    function(code, imports, sp, b, class)
        binop2(code, sp, 'dmul')
    end,
    -- 6c idiv
    function(code, imports, sp, b, class)
        binop(code, sp, 'idiv')
    end,
    -- 6d ldiv
    function(code, imports, sp, b, class)
        binop2(code, sp, 'ldiv')
    end,
    -- 6e fdiv
    function(code, imports, sp, b, class)
        binop(code, sp, 'fdiv')
    end,
    -- 6f ddiv
    function(code, imports, sp, b, class)
        binop2(code, sp, 'ddiv')
    end,
    -- 70 irem
    function(code, imports, sp, b, class)
        binop(code, sp, 'irem')
    end,
    -- 71 lrem
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lrem')
    end,
    -- 72 frem
    function(code, imports, sp, b, class)
        binop(code, sp, 'frem')
    end,
    -- 73 drem
    function(code, imports, sp, b, class)
        binop2(code, sp, 'drem')
    end,
    -- 74 ineg
    function(code, imports, sp, b, class)
        code:println('s%u = ineg(s%u)', sp - 1, sp - 1)
    end,
    -- 75 lneg
    function(code, imports, sp, b, class)
        code:println('s%u = lneg(s%u)', sp - 2, sp - 2)
    end,
    -- 76 fneg
    function(code, imports, sp, b, class)
        code:println('s%u = fneg(s%u)', sp - 1, sp - 1)
    end,
    -- 77 dneg
    function(code, imports, sp, b, class)
        code:println('s%u = dneg(s%u)', sp - 2, sp - 2)
    end,
    -- 78 ishl
    function(code, imports, sp, b, class)
        binop(code, sp, 'ishl')
    end,
    -- 79 lshl
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lshl')
    end,
    -- 7a ishr
    function(code, imports, sp, b, class)
        binop(code, sp, 'ishr')
    end,
    -- 7b lshr
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lshr')
    end,
    -- 7c iushr
    function(code, imports, sp, b, class)
        binop(code, sp, 'iushr')
    end,
    -- 7d lushr
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lushr')
    end,
    -- 7e iand
    function(code, imports, sp, b, class)
        binop(code, sp, 'iand')
    end,
    -- 7f land
    function(code, imports, sp, b, class)
        binop2(code, sp, 'land')
    end,
    -- 80 ior
    function(code, imports, sp, b, class)
        binop(code, sp, 'ior')
    end,
    -- 81 lor
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lor')
    end,
    -- 82 ixor
    function(code, imports, sp, b, class)
        binop(code, sp, 'ixor')
    end,
    -- 83 lxor
    function(code, imports, sp, b, class)
        binop2(code, sp, 'lxor')
    end,
    -- 84 iinc
    function(code, imports, sp, b, class)
        local index = b:read('1')
        local const = b:read('B')
        code:println('l%u = iadd(l%u, %d)', index, index, const)
    end,
    -- 85 i2l
    function(code, imports, sp, b, class)
        code:println('s%u = i2l(s%u)', sp - 1, sp - 1)
    end,
    -- 86 i2f
    function(code, imports, sp, b, class)
        code:println('s%u = i2f(s%u)', sp - 1, sp - 1)
    end,
    -- 87 i2d
    function(code, imports, sp, b, class)
        code:println('s%u = i2d(s%u)', sp - 1, sp - 1)
    end,
    -- 88 l2i
    function(code, imports, sp, b, class)
        code:println('s%u = l2i(s%u)', sp - 2, sp - 2)
    end,
    -- 89 l2f
    function(code, imports, sp, b, class)
        code:println('s%u = l2f(s%u)', sp - 2, sp - 2)
    end,
    -- 8a l2d
    function(code, imports, sp, b, class)
        code:println('s%u = l2d(s%u)', sp - 2, sp - 2)
    end,
    -- 8b f2i
    function(code, imports, sp, b, class)
        code:println('s%u = f2i(s%u)', sp - 1, sp - 1)
    end,
    -- 8c f2l
    function(code, imports, sp, b, class)
        code:println('s%u = f2l(s%u)', sp - 1, sp - 1)
    end,
    -- 8d f2d
    function(code, imports, sp, b, class)
        code:println('s%u = f2d(s%u)', sp - 1, sp - 1)
    end,
    -- 8e d2i
    function(code, imports, sp, b, class)
        code:println('s%u = d2i(s%u)', sp - 2, sp - 2)
    end,
    -- 8f d2l
    function(code, imports, sp, b, class)
        code:println('s%u = d2l(s%u)', sp - 2, sp - 2)
    end,
    -- 90 d2f
    function(code, imports, sp, b, class)
        code:println('s%u = d2f(s%u)', sp - 2, sp - 2)
    end,
    -- 91 i2b
    function(code, imports, sp, b, class)
        code:println('s%u = i2b(s%u)', sp - 1, sp - 1)
    end,
    -- 92 i2c
    function(code, imports, sp, b, class)
        code:println('s%u = i2c(s%u)', sp - 1, sp - 1)
    end,
    -- 93 i2s
    function(code, imports, sp, b, class)
        code:println('s%u = i2s(s%u)', sp - 1, sp - 1)
    end,
    -- 94 lcmp
    function(code, imports, sp, b, class)
        code:println('s%u = lcmp(s%u, s%u)', sp - 4, sp - 4, sp - 2)
    end,
    -- 95 fcmpl
    function(code, imports, sp, b, class)
        code:println('s%u = fcmpl(s%u, s%u)', sp - 2, sp - 2, sp - 1)
    end,
    -- 96 fcmpg
    function(code, imports, sp, b, class)
        code:println('s%u = fcmpg(s%u, s%u)', sp - 2, sp - 2, sp - 1)
    end,
    -- 97 dcmpl
    function(code, imports, sp, b, class)
        code:println('s%u = dcmpl(s%u, s%u)', sp - 4, sp - 4, sp - 2)
    end,
    -- 98 dcmpg
    function(code, imports, sp, b, class)
        code:println('s%u = dcmpg(s%u, s%u)', sp - 4, sp - 4, sp - 2)
    end,
    -- 99 ifeq
    function(code, imports, sp, b, class)
        generateIf0(code, sp, b, '==')
    end,
    -- 9a ifne
    function(code, imports, sp, b, class)
        generateIf0(code, sp, b, '~=')
    end,
    -- 9b iflt
    function(code, imports, sp, b, class)
        generateIf0(code, sp, b, '<')
    end,
    -- 9c ifge
    function(code, imports, sp, b, class)
        generateIf0(code, sp, b, '>=')
    end,
    -- 9d ifgt
    function(code, imports, sp, b, class)
        generateIf0(code, sp, b, '>')
    end,
    -- 9e ifle
    function(code, imports, sp, b, class)
        generateIf0(code, sp, b, '<=')
    end,
    -- 9f if_icmpeq
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '==', false)
    end,
    -- a0 if_icmpne
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '~=', false)
    end,
    -- a1 if_icmplt
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '<', false)
    end,
    -- a2 if_icmpge
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '>=', false)
    end,
    -- a3 if_icmpgt
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '>', false)
    end,
    -- a4 if_icmple
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '<=', false)
    end,
    -- a5 if_acmpeq
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '==', true)
    end,
    -- a6 if_acmpne
    function(code, imports, sp, b, class)
        generateIf(code, sp, b, '~=', true)
    end,
    -- a7 goto
    function(code, imports, sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 's'
        code:println('goto _%04x', pc + offset)
    end,
    -- a8 jsr
    function(code, imports, sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 's'
        code:println('s%u = %u', sp, pc + 3)
        code:println('goto _%04x', pc + offset)
    end,
    -- a9 ret
    function(code, imports, sp, b, class)
        code:println('-- ret l%u', b:read '1')
    end,
    -- aa tableswitch
    function(code, imports, sp, b, class)
        -- TODO generate a binary search.
        local pc = b:tell() - 1
        b:seek((pc + 1 + 3) & ~3, 'set')
        local default = b:read 'I'
        local low = b:read 'I'
        local high = b:read 'I'
        local npairs = high - low + 1

        code:println 'do'
        code:println('    local index = s%u - %d', sp - 1, low)

        if npairs ~= 0 then
            local cmp = 'if'

            for i = 1, npairs do
                local offset = b:read 'I'
                code:println('    %s index == %u then goto _%04x', cmp, i - 1, pc + offset)
                cmp = 'elseif'
            end
    
            code:println('    else goto _%04x', pc + default)
            code:println '    end'
        else
            code:println('    goto _%04x', pc + default)
        end

        code:println 'end'
    end,
    -- lookupswitch
    function(code, imports, sp, b, class)
        -- TODO generate a binary search.
        local pc = b:tell() - 1
        b:seek((pc + 1 + 3) & ~3, 'set')
        local default = b:read 'I'
        local npairs = b:read 'I'

        if npairs ~= 0 then
            local cmp = 'if'

            for i = 1, npairs do
                local match = b:read 'I'
                local offset = b:read 'I'
                code:println('%s s%u == %d then goto _%04x', cmp, sp - 1, match, pc + offset)
                cmp = 'elseif'
            end

            code:println('else goto _%04x', pc + default)
            code:println 'end'
        else
            code:println('goto _%04x', pc + default)
        end
    end,
    -- ac ireturn
    function(code, imports, sp, b, class)
        code:println('do return s%u end', sp - 1)
    end,
    -- ad lreturn
    function(code, imports, sp, b, class)
        code:println('do return s%u end', sp - 1)
    end,
    -- ae freturn
    function(code, imports, sp, b, class)
        code:println('do return s%u end', sp - 1)
    end,
    -- af dreturn
    function(code, imports, sp, b, class)
        code:println('do return s%u end', sp - 1)
    end,
    -- b0 areturn
    function(code, imports, sp, b, class)
        code:println('do return s%u end', sp - 1)
    end,
    -- b1 return
    function(code, imports, sp, b, class)
        code:println 'do return end'
    end,
    -- b2 getstatic
    function(code, imports, sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        imports[className] = 'class'

        code:println('-- %s.%s %s', className, name, descriptor)
        code:println('s%u = _%08x%s', sp, crc32(className), i(name))
    end,
    -- b3 putstatic
    function(code, imports, sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        imports[className] = 'class'

        code:println('-- %s.%s %s', className, name, descriptor)
        code:println('_%08x%s = s%u', crc32(className), i(name), sp - slots)

        if descriptor:byte(1) == 76 then -- L
            code:println('s%u = nil', sp - 1)
        end
    end,
    -- b4 getfield
    function(code, imports, sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        code:println('-- %s.%s %s', className, name, descriptor)
        code:println('s%u = s%u%s', sp - 1, sp - 1, i(name))
    end,
    -- b5 putfield
    function(code, imports, sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        code:println('-- %s.%s %s', className, name, descriptor)
        code:println('s%u%s = s%u', sp - 1 - slots, i(name), sp - slots)
        code:println('s%u = nil', sp - slots)

        if descriptor:byte(1) == 76 then -- L
            code:println('s%u = nil', sp - 1)
        end
    end,
    -- b6 invokevirtual
    function(code, imports, sp, b, class)
        generateInvoke(code, imports, sp, b, class, b:read '2', false)
    end,
    -- b7 invokespecial
    function(code, imports, sp, b, class)
        generateInvoke(code, imports, sp, b, class, b:read '2', false)
    end,
    -- b8 invokestatic
    function(code, imports, sp, b, class)
        generateInvoke(code, imports, sp, b, class, b:read '2', true)
    end,
    -- b9 invokeinterface
    function(code, imports, sp, b, class)
        generateInvoke(code, imports, sp, b, class, b:read '2', false)
    end,
    -- ba
    nil,
    -- bb new
    function(code, imports, sp, b, class)
        local cpool = class.constantPool
        local className = cpool[cpool[b:read '2'].nameIndex].bytes
        code:println('s%u = {}', sp)
        code:println('_%08x%s(s%u) -- %s', crc32(className), i('<new>()V'), sp, className)
    end,
    -- bc newarray
    function(code, imports, sp, b, class)
        local type = b:read '1'
        code:println('s%u = newarray(s%u) -- %u', sp - 1, sp - 1, type)
    end,
    -- bd anewarray
    function(code, imports, sp, b, class)
        local index = b:read '2'
        local cpool = class.constantPool
        local className = cpool[cpool[index].nameIndex].bytes
        code:println('s%u = anewarray(s%u) -- %q', sp - 1, sp - 1, className)
    end,
    -- be arraylength
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%s)', sp - 1)
        code:println('s%u = s%u.n', sp - 1, sp - 1)
    end,
    -- bf athrow
    function(code, imports, sp, b, class)
        code:println('maybeThrowNullPointerException(s%u)', sp - 1)
        code:println('error(s%u)', sp - 1)
    end,
    -- c0 checkcast
    function(code, imports, sp, b, class)
    end,
    -- c1 instanceof
    function(code, imports, sp, b, class)
        local index = b:read '2'
        local cpool = class.constantPool
        local className = cpool[cpool[index].nameIndex].bytes
        code:println('s%u = instanceof(%q)', sp - 1, className)
    end,
    -- c2 monitorenter
    function(code, imports, sp, b, class)
        code:println('monitorenter(s%u)', sp - 1)
        code:println('s%u = nil', sp - 1)
    end,
    -- c3 monitorexit
    function(code, imports, sp, b, class)
        code:println('monitorexit(s%u)', sp - 1)
        code:println('s%u = nil', sp - 1)
    end,
    -- c4 wide
    function(code, imports, sp, b, class)
        local op = b:read '1'
        local index = b:read '2'

        if op == 0x15 or op == 0x17 or op == 0x19 then
            -- iload, fload, aload
            code:println('s%u = l%u', sp, index)
        elseif op == 0x36 or op == 0x38 or op == 0x3a then
            -- istore, fstore, astore
            code:println('l%u = s%u', index, sp - 1)
        elseif op == 0x16 or op == 0x18 then
            -- lload, dload
            code:println('s%u = l%u', sp, index)
        elseif op == 0x37 or op == 0x39 then
            -- lstore, dstore
            code:println('l%u = s%u', index, sp - 2)
        elseif op == 0xa9 then
            -- ret
            code:println('-- ret l%u', index)
        elseif op == 0x84 then
            -- iinc
            local const = b:read 's'
            code:println('l%u = iadd(l%u, %d)', index, index, const)
        end
    end,
    -- c5 multianewarray
    function(code, imports, sp, b, class)
        local index = b:read '2'
        local cpool = class.constantPool
        local className = cpool[cpool[index].nameIndex].bytes
        local dimensions = b:read '1'

        code:indentation()
        code:write('s%u = multianewarray(%u', sp - dimensions, dimensions)

        for i = dimensions, 1, -1 do
            code:write(', s%u', sp - i)
        end

        code:write(') -- %q', className)
        code:eol()
    end,
    -- c6 ifnull
    function(code, imports, sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read('s')

        code:println 'do'
        code:println('    local test = s%u', sp - 1)
        code:println('    s%u = nil', sp - 1)
        code:println('    if test == nil then goto _%04x end', pc + offset)
        code:println 'end'
    end,
    -- c7 ifnonnull
    function(code, imports, sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read('s')

        code:println 'do'
        code:println('    local test = s%u', sp - 1)
        code:println('    s%u = nil', sp - 1)
        code:println('    if test ~= nil then goto _%04x end', pc + offset)
        code:println 'end'
    end,
    -- c8 goto_w
    function(code, imports, sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 'I'
        code:println('goto _%04x', pc + offset)
    end,
    -- c9 jsr_w
    function(code, imports, sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 'I'
        code:println('s%u = %u', sp, pc + 3)
        code:println('goto _%04x', pc + offset)
    end,
}

local function generateNew(code, imports, class)
    local cpool = class.constantPool

    code:println('%s = function(l0)', i('<new>()V'))

    for j = 1, class.fields.n do
        local field = class.fields[j]
        local name = cpool[field.nameIndex].bytes
        local descriptor = cpool[field.descriptorIndex].bytes

        local value

        if descriptor:byte(1, 1) == 76 then -- L
            value = 'nil'
        else
            value = '0'
        end

        code:println('    l0%s = %s', i(name), value)
    end

    if class.superClass ~= 0 then
        local superName = cpool[cpool[class.superClass].nameIndex].bytes
        imports[superName] = 'class'

        code:eol()
        code:println('    _%08x%s(l0) -- %s', crc32(superName), i('<new>()V'), superName)
    end

    code:println 'end,'
    code:eol()
end

local function generateCode(code, imports, b, class, jumps)
    local cpool = class.constantPool
    local sp = 0

    for pc, op, name, size, deltasp in bytecode.opcodes(b, cpool) do
        local addr = f('-- %04x     ', pc)

        for i = 1, jumps.n do
            local jump = jumps[i]

            if jump.target == pc then
                sp = jump.sp
                addr = f('::_%04x:: --', pc)
                break
            end
        end

        code:println('%s %02x %s %s %u %2u', addr, op, name, r(' ', 16 - #name), size, sp)

        b:seek(pc + 1, 'set')
        generators[op](code, imports, sp, b, class)

        sp = sp + deltasp
        code:eol()
    end
end

local function generateMethod(code, imports, class, method)
    local cpool = class.constantPool
    local name = cpool[method.nameIndex].bytes
    local descriptor = cpool[method.descriptorIndex].bytes

    if method.accessFlags.native then
        local className = cpool[cpool[class.thisClass].nameIndex].bytes
        code:println('%s = luame.native[]', i(name .. descriptor), i(f('%s.%s%s', className, name, descriptor)))
        code:eol()
        return
    end

    -- Exception table.
    do
        local et = method.attributes.code.exceptionTable

        for i = 1, et.n do
            local e = et[i]
            local catchType = e.catchType == 0 and 'java.lang.Throwable' or cpool[cpool[e.catchType].nameIndex].bytes
            
            code:println('-- %04x %04x %04x %s', e.startPc, e.endPc, e.handlerPc, catchType)
        end
    end

    -- Method definition with locals.
    -- All locals will be defined as parameters, which is fine.
    do
        code:indentation()
        code:write('%s = function(', i(name .. descriptor))
        local comma = ''
    
        for i = 1, method.attributes.code.maxLocals do
            code:write('%sl%u', comma, i - 1)
            comma = ', '
        end
    
        code:write ')'
        code:eol()
    end

    code:indent()

    -- Stack.
    do
        local maxStack = method.attributes.code.maxStack

        if maxStack > 0 then
            code:indentation()
            code:write 'local '
            local comma = ''
    
            for i = 1, maxStack do
                code:write('%ss%u', comma, i - 1)
                comma = ', '
            end
    
            code:eol()
            code:eol()
        end
    end

    local jumps = collectJumps(method, cpool)
    generateCode(code, imports, method.attributes.code.code, class, jumps)

    code:unindent()
    code:println 'end,'
    code:eol()
end

return function(vm, class)
    local cpool = class.constantPool
    local code = utils.codeGenerator()
    local imports = {}

    code:indent()
    code:println 'return {'
    code:indent()

    generateNew(code, imports, class)

    for i = 1, class.methods.n do
        generateMethod(code, imports, class, class.methods[i])
    end

    code:unindent()
    code:println '}'
    code:unindent()

    local header = utils.codeGenerator()
    header:println 'return function(vm)'

    for name, type in pairs(imports) do
        if type == 'class' then
            if name ~= cpool[cpool[class.thisClass].nameIndex].bytes then
                header:println('    local _%08x = vm:define %q', crc32(name), name)
            end
        elseif type == 'string' then
            header:println('    local _%08x = vm:string %q', crc32(name), name)
        end
    end

    header:eol()
    header:write(code:finish())
    header:println 'end'
    
    local source = header:finish()
    print(source)
    return source
end
