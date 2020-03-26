local utils = require 'luame.utils'
local bytecode = require 'luame.bytecode'
local crc32 = require 'luame.crc32'
local log = require 'luame.log'

local format = string.format
local rep = string.rep

local function index(str)
    if str:match('^[a-zA-Z_][a-zA-Z_0-9]*$') then
        return format('.%s', str)
    else
        return format('[%q]', str)
    end
end

local function yield(indent, fmt, ...)
    local str = format('%s%s', string.rep('    ', indent), format(fmt, ...))
    io.write(str)
    coroutine.yield(str)
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

local function binop(sp, operator)
    yield(3, 's%u = %s(s%u, s%u)\n', sp - 2, operator, sp - 1, sp - 1)
end

local function binop2(sp, operator)
    yield(3, 's%u = %s(s%u, s%u)\n', sp - 4, operator, sp - 2, sp - 2)
end

local function generateIf(sp, b, operator, setnil)
    local pc = b:tell() - 1
    local offset = b:read 's'

    if setnil then
        yield(3, 'do')
        yield(4, 'local test1, test2 = s%u, s%u\n', sp - 2, sp - 1)
        yield(4, 's%u, s%u = nil, nil\n', sp - 1, sp - 2)
        yield(4, 'if test1 %s test2 then goto _%04x end\n', operator, pc + offset)
        yield(3, 'end\n')
    else
        yield(3, 'if s%u %s s%u then goto _%04x end\n', sp - 2, operator, sp - 1, pc + offset)
    end
end

local function generateIf0(sp, b, operator)
    local pc = b:tell() - 1
    local offset = b:read 's'
    yield(3, 'if s%u %s 0 then goto _%04x end\n', sp - 1, operator, pc + offset)
    return -1
end

local function generateInvoke(sp, b, class, ndx, static)
    local cpool = class.constantPool
    local methodref = cpool[ndx]
    local className = cpool[cpool[methodref.classIndex].nameIndex].bytes
    local nameAndType = cpool[methodref.nameAndTypeIndex]
    local name = cpool[nameAndType.nameIndex].bytes
    local descriptor = cpool[nameAndType.descriptorIndex].bytes

    local slots = utils.countSlots(descriptor)

    if not static then
        slots = slots + 1
    end

    yield(3, '-- %s.%s%s\n', className, name, descriptor)

    if not utils.isVoid(descriptor) then
        yield(3, 's%u = ', sp - slots)
    else
        yield(3, '')
    end

    yield(0, '_%08x%s(', crc32(className), index(name .. descriptor))
    local comma = ''

    for i = -slots, -1 do
        yield(0, '%ss%u', comma, sp + i)
        comma = ', '
    end

    yield(0, ')\n')

    if not static then
        if utils.isVoid(descriptor) then
            yield(3, 's%u = nil\n', sp - slots)
        end

        slots = slots - 1
    end

    for _, descriptor, s in utils.params(descriptor) do
        local k = descriptor:byte(1, 1)

        if k == 76 or k == 91 then -- 'L' or '[''
            yield(3, 's%u = nil\n', sp - slots)
        end

        slots = slots - s
    end
end

local generators = {
    -- 00 nop
    [0] = function(sp, b, class)
    end,
    -- 01 aconst_null
    function(sp, b, class)
        yield(3, 's%u = nil\n', sp)
    end,
    -- 02 iconst_m1
    function(sp, b, class)
        yield(3, 's%u = l2i(-1)\n', sp)
    end,
    -- 03 iconst_0
    function(sp, b, class)
        yield(3, 's%u = l2i(0)\n', sp)
    end,
    -- 04 iconst_1
    function(sp, b, class)
        yield(3, 's%u = l2i(1)\n', sp)
    end,
    -- 05 iconst_2
    function(sp, b, class)
        yield(3, 's%u = l2i(2)\n', sp)
    end,
    -- 06 iconst_3
    function(sp, b, class)
        yield(3, 's%u = l2i(3)\n', sp)
    end,
    -- 07 iconst_4
    function(sp, b, class)
        yield(3, 's%u = l2i(4)\n', sp)
    end,
    -- 08 iconst_5
    function(sp, b, class)
        yield(3, 's%u = l2i(5)\n', sp)
    end,
    -- 09 lconst_0
    function(sp, b, class)
        yield(3, 's%u = 0\n', sp)
    end,
    -- 0a lconst_1
    function(sp, b, class)
        yield(3, 's%u = 1\n', sp)
    end,
    -- 0b fconst_0
    function(sp, b, class)
        yield(3, 's%u = l2f(0)\n', sp)
    end,
    -- 0c fconst_1
    function(sp, b, class)
        yield(3, 's%u = l2f(1)\n', sp)
    end,
    -- 0d fconst_2
    function(sp, b, class)
        yield(3, 's%u = l2f(2)\n', sp)
    end,
    -- 0e dconst_0
    function(sp, b, class)
        yield(3, 's%u = l2d(0)\n', sp)
    end,
    -- 0f dconst_1
    function(sp, b, class)
        yield(3, 's%u = l2d(1)\n', sp)
    end,
    -- 10 bipush
    function(sp, b, class)
        yield(3, 's%u = l2i(%d)\n', sp, b:read 'B')
    end,
    -- 11 sipush
    function(sp, b, class)
        yield(3, 's%u = l2i(%d)\n', sp, b:read 's')
    end,
    -- 12 ldc
    function(sp, b, class)
        local cpool = class.constantPool
        local const = cpool[b:read '1']
        local value = const.bytes

        if const.tag == 'string' then
            value = cpool[const.stringIndex].bytes
            yield(3, 's%u = _%08x -- %q\n', sp, crc32(value), value)
        elseif const.tag == 'int' then
            yield(3, 's%u = l2i(%d)\n', sp, value)
        else
            yield(3, 's%u = l2f(%d)\n', sp, value)
        end
    end,
    -- 13 ldc_w
    function(sp, b, class)
        local cpool = class.constantPool
        local const = cpool[b:read '2']
        local value = const.bytes

        if const.tag == 'string' then
            value = cpool[const.stringIndex].bytes
            yield(3, 's%u = _%08x -- %q\n', sp, crc32(value), value)
        elseif const.tag == 'int' then
            yield(3, 's%u = l2i(%d)\n', sp, value)
        else
            yield(3, 's%u = l2f(%d)\n', sp, value)
        end
    end,
    -- 14 ldc2_w
    function(sp, b, class)
        local const = class.constantPool[b:read '2']
        local value = const.bytes

        if const.tag == 'long' then
            yield(3, 's%u = %d\n', sp, value)
        else
            yield(3, 's%u = l2d(%d)\n', sp, value)
        end
    end,
    -- 15 iload
    function(sp, b, class)
        yield(3, 's%u = l%u\n', sp, b:read '1')
    end,
    -- 16 lload
    function(sp, b, class)
        yield(3, 's%u = l%u\n', sp, b:read '1')
    end,
    -- 17 fload
    function(sp, b, class)
        yield(3, 's%u = l%u\n', sp, b:read '1')
    end,
    -- 18 dload
    function(sp, b, class)
        yield(3, 's%u = l%u\n', sp, b:read '1')
    end,
    -- 19 aload
    function(sp, b, class)
        yield(3, 's%u = l%u\n', sp, b:read '1')
    end,
    -- 1a iload_0
    function(sp, b, class)
        yield(3, 's%u = l0\n', sp)
    end,
    -- 1b iload_1
    function(sp, b, class)
        yield(3, 's%u = l1\n', sp)
    end,
    -- 1c iload_2
    function(sp, b, class)
        yield(3, 's%u = l2\n', sp)
    end,
    -- 1d iload_3
    function(sp, b, class)
        yield(3, 's%u = l3\n', sp)
    end,
    -- 1e lload_0
    function(sp, b, class)
        yield(3, 's%u = l0\n', sp)
    end,
    -- 1f lload_1
    function(sp, b, class)
        yield(3, 's%u = l1\n', sp)
    end,
    -- 20 lload_2
    function(sp, b, class)
        yield(3, 's%u = l2\n', sp)
    end,
    -- 21 lload_3
    function(sp, b, class)
        yield(3, 's%u = l3\n', sp)
    end,
    -- 22 fload_0
    function(sp, b, class)
        yield(3, 's%u = l0\n', sp)
    end,
    -- 23 fload_1
    function(sp, b, class)
        yield(3, 's%u = l1\n', sp)
    end,
    -- 24 fload_2
    function(sp, b, class)
        yield(3, 's%u = l2\n', sp)
    end,
    -- 25 fload_3
    function(sp, b, class)
        yield(3, 's%u = l3\n', sp)
    end,
    -- 26 dload_0
    function(sp, b, class)
        yield(3, 's%u = l0\n', sp)
    end,
    -- 27 dload_1
    function(sp, b, class)
        yield(3, 's%u = l1\n', sp)
    end,
    -- 28 dload_2
    function(sp, b, class)
        yield(3, 's%u = l2\n', sp)
    end,
    -- 29 dload_3
    function(sp, b, class)
        yield(3, 's%u = l3\n', sp)
    end,
    -- 2a aload_0
    function(sp, b, class)
        yield(3, 's%u = l0\n', sp)
    end,
    -- 2b aload_1
    function(sp, b, class)
        yield(3, 's%u = l1\n', sp)
    end,
    -- 2c aload_2
    function(sp, b, class)
        yield(3, 's%u = l2\n', sp)
    end,
    -- 2d aload_3
    function(sp, b, class)
        yield(3, 's%u = l3\n', sp)
    end,
    -- 2e iaload
    function(sp, b, class)
        yield(3, 'throwNullPointerException(s%u)\n', sp - 2)
        yield(3, 'throwArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 2f laload
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 2)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 30 faload
    function(sp, b, class)
        yield(3, 'throwNullPointerException(s%u)\n', sp - 2)
        yield(3, 'throwArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 31 daload
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 2)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 32 aaload
    function(sp, b, class)
        yield(3, 'throwNullPointerException(s%u)\n', sp - 2)
        yield(3, 'throwArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 33 baload
    function(sp, b, class)
        yield(3, 'throwNullPointerException(s%u)\n', sp - 2)
        yield(3, 'throwArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 34 caload
    function(sp, b, class)
        yield(3, 'throwNullPointerException(s%u)\n', sp - 2)
        yield(3, 'throwArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 35 saload
    function(sp, b, class)
        yield(3, 'throwNullPointerException(s%u)\n', sp - 2)
        yield(3, 'throwArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 2, sp - 1)
        yield(3, 's%u = s%u[s%u]\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 36 istore
    function(sp, b, class)
        yield(3, 'l%u = s%u\n', b:read '1', sp - 1)
    end,
    -- 37 lstore
    function(sp, b, class)
        yield(3, 'l%u = s%u\n', b:read '1', sp - 2)
    end,
    -- 38 fstore
    function(sp, b, class)
        yield(3, 'l%u = s%u\n', b:read '1', sp - 1)
    end,
    -- 39 dstore
    function(sp, b, class)
        yield(3, 'l%u = s%u\n', b:read '1', sp - 2)
    end,
    -- 3a astore
    function(sp, b, class)
        yield(3, 'l%u = s%u\n', b:read '1', sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- 3b istore_0
    function(sp, b, class)
        yield(3, 'l0 = s%u\n', sp - 1)
    end,
    -- 3c istore_1
    function(sp, b, class)
        yield(3, 'l1 = s%u\n', sp - 1)
    end,
    -- 3d istore_2
    function(sp, b, class)
        yield(3, 'l2 = s%u\n', sp - 1)
    end,
    -- 3e istore_3
    function(sp, b, class)
        yield(3, 'l3 = s%u\n', sp - 1)
    end,
    -- 3f lstore_0
    function(sp, b, class)
        yield(3, 'l0 = s%u\n', sp - 1)
    end,
    -- 40 lstore_1
    function(sp, b, class)
        yield(3, 'l1 = s%u\n', sp - 1)
    end,
    -- 41 lstore_2
    function(sp, b, class)
        yield(3, 'l2 = s%u\n', sp - 1)
    end,
    -- 42 lstore_3
    function(sp, b, class)
        yield(3, 'l3 = s%u\n', sp - 1)
    end,
    -- 43 fstore_0
    function(sp, b, class)
        yield(3, 'l0 = s%u\n', sp - 1)
    end,
    -- 44 fstore_1
    function(sp, b, class)
        yield(3, 'l1 = s%u\n', sp - 1)
    end,
    -- 45 fstore_2
    function(sp, b, class)
        yield(3, 'l2 = s%u\n', sp - 1)
    end,
    -- 46 fstore_3
    function(sp, b, class)
        yield(3, 'l3 = s%u\n', sp - 1)
    end,
    -- 47 dstore_0
    function(sp, b, class)
        yield(3, 'l0 = s%u\n', sp - 1)
    end,
    -- 48 dstore_1
    function(sp, b, class)
        yield(3, 'l1 = s%u\n', sp - 1)
    end,
    -- 49 dstore_2
    function(sp, b, class)
        yield(3, 'l2 = s%u\n', sp - 1)
    end,
    -- 4a dstore_3
    function(sp, b, class)
        yield(3, 'l3 = s%u\n', sp - 1)
    end,
    -- 4b astore_0
    function(sp, b, class)
        yield(3, 'l0 = s%u\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- 4c astore_1
    function(sp, b, class)
        yield(3, 'l1 = s%u\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- 4d astore_2
    function(sp, b, class)
        yield(3, 'l2 = s%u\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- 4e astore_3
    function(sp, b, class)
        yield(3, 'l3 = s%u\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- 4f iastore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 3)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 3, sp - 2)
        yield(3, 's%u[s%u] = s%u\n', sp - 3, sp - 2, sp - 1)
        yield(3, 's%u = nil\n', sp - 3)
    end,
    -- 50 lastore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 4)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 4, sp - 3)
        yield(3, 's%u[s%u] = s%u\n', sp - 4, sp - 3, sp - 2)
        yield(3, 's%u = nil\n', sp - 4)
    end,
    -- 51 fastore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 3)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 3, sp - 2)
        yield(3, 's%u[s%u] = s%u\n', sp - 3, sp - 2, sp - 1)
        yield(3, 's%u = nil\n', sp - 3)
    end,
    -- 52 dastore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 4)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 4, sp - 3)
        yield(3, 's%u[s%u] = s%u\n', sp - 4, sp - 3, sp - 2)
        yield(3, 's%u = nil\n', sp - 4)
    end,
    -- 53 aastore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 3)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 3, sp - 2)
        yield(3, 's%u[s%u] = s%u\n', sp - 3, sp - 2, sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 3)
    end,
    -- 54 bastore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 3)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 3, sp - 2)
        yield(3, 's%u[s%u] = s%u\n', sp - 3, sp - 2, sp - 1)
        yield(3, 's%u = nil\n', sp - 3)
    end,
    -- 55 castore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 3)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 3, sp - 2)
        yield(3, 's%u[s%u] = s%u\n', sp - 3, sp - 2, sp - 1)
        yield(3, 's%u = nil\n', sp - 3)
    end,
    -- 56 sastore
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 3)
        yield(3, 'maybeThrowArrayIndexOutOfBoundsException(s%u, s%u)\n', sp - 3, sp - 2)
        yield(3, 's%u[s%u] = s%u\n', sp - 3, sp - 2, sp - 1)
        yield(3, 's%u = nil\n', sp - 3)
    end,
    -- 57 pop
    function(sp, b, class)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- 58 pop2
    function(sp, b, class)
        yield(3, 's%u = nil\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 2)
    end,
    -- 59 dup
    function(sp, b, class)
        yield(3, 's%u = s%u\n', sp, sp - 1)
    end,
    -- 5a dup_x1
    function(sp, b, class)
        yield(3, 's%u = s%u\n', sp,     sp - 1)
        yield(3, 's%u = s%u\n', sp - 1, sp - 2)
        yield(3, 's%u = s%u\n', sp - 2, sp)
    end,
    -- 5b dup_x2
    function(sp, b, class)
        yield(3, 's%u = s%u\n', sp,     sp - 1)
        yield(3, 's%u = s%u\n', sp - 1, sp - 2)
        yield(3, 's%u = s%u\n', sp - 2, sp - 3)
        yield(3, 's%u = s%u\n', sp - 3, sp)
    end,
    -- 5c dup2
    function(sp, b, class)
        yield(3, 's%u = s%u\n', sp + 1, sp - 1)
        yield(3, 's%u = s%u\n', sp,     sp - 2)
    end,
    -- 5d dup2_x1
    function(sp, b, class)
        yield(3, 's%u = s%u\n', sp + 1, sp - 1)
        yield(3, 's%u = s%u\n', sp,     sp - 2)
        yield(3, 's%u = s%u\n', sp - 1, sp - 3)
        yield(3, 's%u = s%u\n', sp - 2, sp + 1)
        yield(3, 's%u = s%u\n', sp - 3, sp)
    end,
    -- 5e dup2_x2
    function(sp, b, class)
        yield(3, 's%u = s%u\n', sp + 1, sp - 1)
        yield(3, 's%u = s%u\n', sp,     sp - 2)
        yield(3, 's%u = s%u\n', sp - 1, sp - 3)
        yield(3, 's%u = s%u\n', sp - 2, sp - 4)
        yield(3, 's%u = s%u\n', sp - 3, sp + 1)
        yield(3, 's%u = s%u\n', sp - 4, sp)
    end,
    -- 5f swap
    function(sp, b, class)
        yield(3, 's%u, s%u = s%u, s%u\n', sp - 1, sp - 2, sp - 2, sp - 1)
    end,
    -- 60 iadd
    function(sp, b, class)
        binop(sp, 'iadd')
    end,
    -- 61 ladd
    function(sp, b, class)
        binop2(sp, 'ladd')
    end,
    -- 62 fadd
    function(sp, b, class)
        binop(sp, 'fadd')
    end,
    -- 63 dadd
    function(sp, b, class)
        binop2(sp, 'dadd')
    end,
    -- 64 isub
    function(sp, b, class)
        binop(sp, 'isub')
    end,
    -- 65 lsub
    function(sp, b, class)
        binop2(sp, 'lsub')
    end,
    -- 66 fsub
    function(sp, b, class)
        binop(sp, 'fsub')
    end,
    -- 67 dsub
    function(sp, b, class)
        binop2(sp, 'dsub')
    end,
    -- 68 imul
    function(sp, b, class)
        binop(sp, 'imul')
    end,
    -- 69 lmul
    function(sp, b, class)
        binop2(sp, 'lmul')
    end,
    -- 6a fmul
    function(sp, b, class)
        binop(sp, 'fmul')
    end,
    -- 6b dmul
    function(sp, b, class)
        binop2(sp, 'dmul')
    end,
    -- 6c idiv
    function(sp, b, class)
        binop(sp, 'idiv')
    end,
    -- 6d ldiv
    function(sp, b, class)
        binop2(sp, 'ldiv')
    end,
    -- 6e fdiv
    function(sp, b, class)
        binop(sp, 'fdiv')
    end,
    -- 6f ddiv
    function(sp, b, class)
        binop2(sp, 'ddiv')
    end,
    -- 70 irem
    function(sp, b, class)
        binop(sp, 'irem')
    end,
    -- 71 lrem
    function(sp, b, class)
        binop2(sp, 'lrem')
    end,
    -- 72 frem
    function(sp, b, class)
        binop(sp, 'frem')
    end,
    -- 73 drem
    function(sp, b, class)
        binop2(sp, 'drem')
    end,
    -- 74 ineg
    function(sp, b, class)
        yield(3, 's%u = ineg(s%u)\n', sp - 1, sp - 1)
    end,
    -- 75 lneg
    function(sp, b, class)
        yield(3, 's%u = lneg(s%u)\n', sp - 2, sp - 2)
    end,
    -- 76 fneg
    function(sp, b, class)
        yield(3, 's%u = fneg(s%u)\n', sp - 1, sp - 1)
    end,
    -- 77 dneg
    function(sp, b, class)
        yield(3, 's%u = dneg(s%u)\n', sp - 2, sp - 2)
    end,
    -- 78 ishl
    function(sp, b, class)
        binop(sp, 'ishl')
    end,
    -- 79 lshl
    function(sp, b, class)
        binop2(sp, 'lshl')
    end,
    -- 7a ishr
    function(sp, b, class)
        binop(sp, 'ishr')
    end,
    -- 7b lshr
    function(sp, b, class)
        binop2(sp, 'lshr')
    end,
    -- 7c iushr
    function(sp, b, class)
        binop(sp, 'iushr')
    end,
    -- 7d lushr
    function(sp, b, class)
        binop2(sp, 'lushr')
    end,
    -- 7e iand
    function(sp, b, class)
        binop(sp, 'iand')
    end,
    -- 7f land
    function(sp, b, class)
        binop2(sp, 'land')
    end,
    -- 80 ior
    function(sp, b, class)
        binop(sp, 'ior')
    end,
    -- 81 lor
    function(sp, b, class)
        binop2(sp, 'lor')
    end,
    -- 82 ixor
    function(sp, b, class)
        binop(sp, 'ixor')
    end,
    -- 83 lxor
    function(sp, b, class)
        binop2(sp, 'lxor')
    end,
    -- 84 iinc
    function(sp, b, class)
        local index = b:read('1')
        local const = b:read('B')
        yield(3, 'l%u = iadd(l%u, %d)\n', index, index, const)
    end,
    -- 85 i2l
    function(sp, b, class)
        yield(3, 's%u = i2l(s%u)\n', sp - 1, sp - 1)
    end,
    -- 86 i2f
    function(sp, b, class)
        yield(3, 's%u = i2f(s%u)\n', sp - 1, sp - 1)
    end,
    -- 87 i2d
    function(sp, b, class)
        yield(3, 's%u = i2d(s%u)\n', sp - 1, sp - 1)
    end,
    -- 88 l2i
    function(sp, b, class)
        yield(3, 's%u = l2i(s%u)\n', sp - 2, sp - 2)
    end,
    -- 89 l2f
    function(sp, b, class)
        yield(3, 's%u = l2f(s%u)\n', sp - 2, sp - 2)
    end,
    -- 8a l2d
    function(sp, b, class)
        yield(3, 's%u = l2d(s%u)\n', sp - 2, sp - 2)
    end,
    -- 8b f2i
    function(sp, b, class)
        yield(3, 's%u = f2i(s%u)\n', sp - 1, sp - 1)
    end,
    -- 8c f2l
    function(sp, b, class)
        yield(3, 's%u = f2l(s%u)\n', sp - 1, sp - 1)
    end,
    -- 8d f2d
    function(sp, b, class)
        yield(3, 's%u = f2d(s%u)\n', sp - 1, sp - 1)
    end,
    -- 8e d2i
    function(sp, b, class)
        yield(3, 's%u = d2i(s%u)\n', sp - 2, sp - 2)
    end,
    -- 8f d2l
    function(sp, b, class)
        yield(3, 's%u = d2l(s%u)\n', sp - 2, sp - 2)
    end,
    -- 90 d2f
    function(sp, b, class)
        yield(3, 's%u = d2f(s%u)\n', sp - 2, sp - 2)
    end,
    -- 91 i2b
    function(sp, b, class)
        yield(3, 's%u = i2b(s%u)\n', sp - 1, sp - 1)
    end,
    -- 92 i2c
    function(sp, b, class)
        yield(3, 's%u = i2c(s%u)\n', sp - 1, sp - 1)
    end,
    -- 93 i2s
    function(sp, b, class)
        yield(3, 's%u = i2s(s%u)\n', sp - 1, sp - 1)
    end,
    -- 94 lcmp
    function(sp, b, class)
        yield(3, 's%u = lcmp(s%u, s%u)\n', sp - 4, sp - 4, sp - 2)
    end,
    -- 95 fcmpl
    function(sp, b, class)
        yield(3, 's%u = fcmpl(s%u, s%u)\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 96 fcmpg
    function(sp, b, class)
        yield(3, 's%u = fcmpg(s%u, s%u)\n', sp - 2, sp - 2, sp - 1)
    end,
    -- 97 dcmpl
    function(sp, b, class)
        yield(3, 's%u = dcmpl(s%u, s%u)\n', sp - 4, sp - 4, sp - 2)
    end,
    -- 98 dcmpg
    function(sp, b, class)
        yield(3, 's%u = dcmpg(s%u, s%u)\n', sp - 4, sp - 4, sp - 2)
    end,
    -- 99 ifeq
    function(sp, b, class)
        generateIf0(sp, b, '==')
    end,
    -- 9a ifne
    function(sp, b, class)
        generateIf0(sp, b, '~=')
    end,
    -- 9b iflt
    function(sp, b, class)
        generateIf0(sp, b, '<')
    end,
    -- 9c ifge
    function(sp, b, class)
        generateIf0(sp, b, '>=')
    end,
    -- 9d ifgt
    function(sp, b, class)
        generateIf0(sp, b, '>')
    end,
    -- 9e ifle
    function(sp, b, class)
        generateIf0(sp, b, '<=')
    end,
    -- 9f if_icmpeq
    function(sp, b, class)
        generateIf(sp, b, '==', false)
    end,
    -- a0 if_icmpne
    function(sp, b, class)
        generateIf(sp, b, '~=', false)
    end,
    -- a1 if_icmplt
    function(sp, b, class)
        generateIf(sp, b, '<', false)
    end,
    -- a2 if_icmpge
    function(sp, b, class)
        generateIf(sp, b, '>=', false)
    end,
    -- a3 if_icmpgt
    function(sp, b, class)
        generateIf(sp, b, '>', false)
    end,
    -- a4 if_icmple
    function(sp, b, class)
        generateIf(sp, b, '<=', false)
    end,
    -- a5 if_acmpeq
    function(sp, b, class)
        generateIf(sp, b, '==', true)
    end,
    -- a6 if_acmpne
    function(sp, b, class)
        generateIf(sp, b, '~=', true)
    end,
    -- a7 goto
    function(sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 's'
        yield(3, 'goto _%04x\n', pc + offset)
    end,
    -- a8 jsr
    function(sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 's'
        yield(3, 's%u = %u\n', sp, pc + 3)
        yield(3, 'goto _%04x\n', pc + offset)
    end,
    -- a9 ret
    function(sp, b, class)
        yield(3, '-- ret l%u\n', b:read '1')
    end,
    -- aa tableswitch
    function(sp, b, class)
        -- TODO generate a binary search.
        local pc = b:tell() - 1
        b:seek((pc + 1 + 3) & ~3, 'set')
        local default = b:read 'I'
        local low = b:read 'I'
        local high = b:read 'I'
        local npairs = high - low + 1

        yield(3, 'do\n')
        yield(4, 'local index = s%u - %d\n', sp - 1, low)

        if npairs ~= 0 then
            local cmp = 'if'

            for i = 1, npairs do
                local offset = b:read 'I'
                yield(4, '%s index == %u then goto _%04x\n', cmp, i - 1, pc + offset)
                cmp = 'elseif'
            end
    
            yield(4, 'else goto _%04x\n', pc + default)
            yield(4, 'end\n')
        else
            yield(4, 'goto _%04x\n', pc + default)
        end

        yield(3, 'end\n')
    end,
    -- lookupswitch
    function(sp, b, class)
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
                yield(3, '%s s%u == %d then goto _%04x\n', cmp, sp - 1, match, pc + offset)
                cmp = 'elseif'
            end

            yield(3, 'else goto _%04x\n', pc + default)
            yield(3, 'end\n')
        else
            yield(3, 'goto _%04x\n', pc + default)
        end
    end,
    -- ac ireturn
    function(sp, b, class)
        yield(3, 'do return s%u end\n', sp - 1)
    end,
    -- ad lreturn
    function(sp, b, class)
        yield(3, 'do return s%u end\n', sp - 1)
    end,
    -- ae freturn
    function(sp, b, class)
        yield(3, 'do return s%u end\n', sp - 1)
    end,
    -- af dreturn
    function(sp, b, class)
        yield(3, 'do return s%u end\n', sp - 1)
    end,
    -- b0 areturn
    function(sp, b, class)
        yield(3, 'do return s%u end\n', sp - 1)
    end,
    -- b1 return
    function(sp, b, class)
        yield(3, 'do return end\n')
    end,
    -- b2 getstatic
    function(sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        yield(3, '-- %s %s.%s\n', descriptor, className, name)
        yield(3, 's%u = _%08x%s\n', sp, crc32(className), index(name))
    end,
    -- b3 putstatic
    function(sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        yield(3, '-- %s %s.%s\n', descriptor, className, name)
        yield(3, '_%08x%s = s%u\n', crc32(className), index(name), sp - slots)

        local k = descriptor:byte(1)

        if k == 76 or k == 91 then -- 'L' or '['
            yield(3, 's%u = nil\n', sp - 1)
        end
    end,
    -- b4 getfield
    function(sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        yield(3, '-- %s %s.%s\n', descriptor, className, name)
        yield(3, 's%u = s%u%s\n', sp - 1, sp - 1, index(name))
    end,
    -- b5 putfield
    function(sp, b, class)
        local cpool = class.constantPool
        local fieldref = cpool[b:read '2']
        local className = cpool[cpool[fieldref.classIndex].nameIndex].bytes
        local nameAndType = cpool[fieldref.nameAndTypeIndex]
        local name = cpool[nameAndType.nameIndex].bytes
        local descriptor = cpool[nameAndType.descriptorIndex].bytes
        local slots = utils.countSlots(descriptor)

        yield(3, '-- %s %s.%s\n', descriptor, className, name)
        yield(3, 's%u%s = s%u\n', sp - 1 - slots, index(name), sp - slots)
        yield(3, 's%u = nil\n', sp - slots)

        local k = descriptor:byte(1)

        if k == 76 or k == 91 then -- 'L' or '['
            yield(3, 's%u = nil\n', sp - 1)
        end
    end,
    -- b6 invokevirtual
    function(sp, b, class)
        generateInvoke(sp, b, class, b:read '2', false)
    end,
    -- b7 invokespecial
    function(sp, b, class)
        generateInvoke(sp, b, class, b:read '2', false)
    end,
    -- b8 invokestatic
    function(sp, b, class)
        generateInvoke(sp, b, class, b:read '2', true)
    end,
    -- b9 invokeinterface
    function(sp, b, class)
        generateInvoke(sp, b, class, b:read '2', false)
    end,
    -- ba
    nil,
    -- bb new
    function(sp, b, class)
        local cpool = class.constantPool
        local className = cpool[cpool[b:read '2'].nameIndex].bytes
        yield(3, 's%u = {}\n', sp)
        yield(3, '_%08x%s(s%u) -- %s\n', crc32(className), index('<new>()V'), sp, className)
    end,
    -- bc newarray
    function(sp, b, class)
        local type = b:read '1'
        yield(3, 's%u = newarray(s%u, %u)\n', sp - 1, sp - 1, type)
    end,
    -- bd anewarray
    function(sp, b, class)
        local index = b:read '2'
        local cpool = class.constantPool
        local className = cpool[cpool[index].nameIndex].bytes
        yield(3, 's%u = anewarray(s%u) -- %q\n', sp - 1, sp - 1, className)
    end,
    -- be arraylength
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%s)\n', sp - 1)
        yield(3, 's%u = s%u.n\n', sp - 1, sp - 1)
    end,
    -- bf athrow
    function(sp, b, class)
        yield(3, 'maybeThrowNullPointerException(s%u)\n', sp - 1)
        yield(3, 'error(s%u)\n', sp - 1)
    end,
    -- c0 checkcast
    function(sp, b, class)
    end,
    -- c1 instanceof
    function(sp, b, class)
        local index = b:read '2'
        local cpool = class.constantPool
        local className = cpool[cpool[index].nameIndex].bytes
        yield(3, 's%u = instanceof(%q)\n', sp - 1, className)
    end,
    -- c2 monitorenter
    function(sp, b, class)
        yield(3, 'monitorenter(s%u)\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- c3 monitorexit
    function(sp, b, class)
        yield(3, 'monitorexit(s%u)\n', sp - 1)
        yield(3, 's%u = nil\n', sp - 1)
    end,
    -- c4 wide
    function(sp, b, class)
        local op = b:read '1'
        local index = b:read '2'

        if op == 0x15 or op == 0x17 or op == 0x19 then
            -- iload, fload, aload
            yield(3, 's%u = l%u\n', sp, index)
        elseif op == 0x36 or op == 0x38 or op == 0x3a then
            -- istore, fstore, astore
            yield(3, 'l%u = s%u\n', index, sp - 1)
        elseif op == 0x16 or op == 0x18 then
            -- lload, dload
            yield(3, 's%u = l%u\n', sp, index)
        elseif op == 0x37 or op == 0x39 then
            -- lstore, dstore
            yield(3, 'l%u = s%u\n', index, sp - 2)
        elseif op == 0xa9 then
            -- ret
            yield(3, '-- ret l%u\n', index)
        elseif op == 0x84 then
            -- iinc
            local const = b:read 's'
            yield(3, 'l%u = iadd(l%u, %d)\n', index, index, const)
        end
    end,
    -- c5 multianewarray
    function(sp, b, class)
        local index = b:read '2'
        local cpool = class.constantPool
        local className = cpool[cpool[index].nameIndex].bytes
        local dimensions = b:read '1'

        yield(3, 's%u = multianewarray(%u', sp - dimensions, dimensions)

        for i = dimensions, 1, -1 do
            yield(0, ', s%u', sp - i)
        end

        yield(0, ') -- %q\n', className)
    end,
    -- c6 ifnull
    function(sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read('s')

        yield(3, 'do\n')
        yield(4, 'local test = s%u\n', sp - 1)
        yield(4, 's%u = nil\n', sp - 1)
        yield(4, 'if test == nil then goto _%04x end\n', pc + offset)
        yield(3, 'end\n')
    end,
    -- c7 ifnonnull
    function(sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read('s')

        yield(3, 'do\n')
        yield(4, 'local test = s%u\n', sp - 1)
        yield(4, 's%u = nil\n', sp - 1)
        yield(4, 'if test ~= nil then goto _%04x end\n', pc + offset)
        yield(3, 'end\n')
    end,
    -- c8 goto_w
    function(sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 'I'
        yield(3, 'goto _%04x\n', pc + offset)
    end,
    -- c9 jsr_w
    function(sp, b, class)
        local pc = b:tell() - 1
        local offset = b:read 'I'
        yield(3, 's%u = %u\n', sp, pc + 3)
        yield(3, 'goto _%04x\n', pc + offset)
    end,
}

local function generateOpcodes()
    yield(1, 'local l2i = vm:opcodes "l2i"\n')
    yield(1, 'local maybeThrowNullPointerException = vm:opcodes "maybeThrowNullPointerException"\n')
end

local function generateUpvalues(class)
    local cpool = class.constantPool

    for i = 1, cpool.n do
        local const = cpool[i]

        if const then
            if const.tag == 'class' then
                local name = cpool[const.nameIndex].bytes
                yield(1, 'local _%08x -- %s\n', crc32(name), name)
            elseif const.tag == 'string' then
                local str = cpool[const.stringIndex].bytes
                yield(1, 'local _%08x -- %s\n', crc32(str), str)
            end
        end
    end
end

local function generatePost(class)
    local cpool = class.constantPool
    local className = cpool[cpool[class.thisClass].nameIndex].bytes

    yield(2, '%s = function()\n', index('<post>()V'))

    for i = 1, cpool.n do
        local const = cpool[i]

        if const then
            if const.tag == 'class' then
                local name = cpool[const.nameIndex].bytes
                local k = name:byte(1)

                if name ~= className and k ~= 91 then -- '['
                    yield(3, '_%08x = vm:define %q\n', crc32(name), name)
                end
            elseif const.tag == 'string' then
                local str = cpool[const.stringIndex].bytes
                yield(3, '_%08x = vm:string %q\n', crc32(str), str)
            end
        end
    end

    yield(2, 'end,\n')
end    

local function generateNew(class)
    local cpool = class.constantPool
    local hasInstanceFields = false

    yield(2, '%s = function(l0)\n', index('<new>()V'))

    for j = 1, class.fields.n do
        local field = class.fields[j]

        if not field.accessFlags.static then
            local name = cpool[field.nameIndex].bytes
            local descriptor = cpool[field.descriptorIndex].bytes

            local value

            if descriptor:byte(1, 1) == 76 then -- L
                value = 'nil'
            else
                value = '0'
            end

            yield(3, 'l0%s = %s\n', index(name), value)
            hasInstanceFields = true
        end
    end

    if class.superClass ~= 0 then
        local superName = cpool[cpool[class.superClass].nameIndex].bytes

        if hasInstanceFields then
            yield(0, '\n')
        end

        yield(3, '_%08x%s(l0) -- %s\n', crc32(superName), index('<new>()V'), superName)
    end

    yield(2, 'end,\n')
end

local function generateCode(b, class, jumps)
    local cpool = class.constantPool
    local sp = 0

    for pc, op, name, size, deltasp in bytecode.opcodes(b, cpool) do
        local addr = format('-- %04x     ', pc)

        for i = 1, jumps.n do
            local jump = jumps[i]

            if jump.target == pc then
                sp = jump.sp
                addr = format('::_%04x:: --', pc)
                break
            end
        end

        yield(3, '%s %02x %s %s %u %2u\n', addr, op, name, rep(' ', 16 - #name), size, sp)

        b:seek(pc + 1, 'set')
        generators[op](sp, b, class)

        sp = sp + deltasp
        yield(0, '\n')
    end
end

local function generateMethod(class, method)
    local cpool = class.constantPool
    local name = cpool[method.nameIndex].bytes
    local descriptor = cpool[method.descriptorIndex].bytes

    if method.accessFlags.native then
        local className = cpool[cpool[class.thisClass].nameIndex].bytes
        yield(2, '%s = vm:native %q,\n', index(name .. descriptor), format('%s.%s%s', className, name, descriptor))
        log.info(format('NATIVE %s.%s%s _%08x', className, name, descriptor, crc32(format('%s.%s%s', className, name, descriptor))))
        return
    elseif method.accessFlags.abstract then
        local className = cpool[cpool[class.thisClass].nameIndex].bytes
        yield(2, '%s = vm:abstract %q,\n', index(name .. descriptor), format('%s.%s%s', className, name, descriptor))
        return
    end

    -- Exception table.
    do
        local et = method.attributes.code.exceptionTable

        for i = 1, et.n do
            local e = et[i]
            local catchType = e.catchType == 0 and 'java.lang.Throwable' or cpool[cpool[e.catchType].nameIndex].bytes
            
            yield(2, '-- %04x %04x %04x %s\n', e.startPc, e.endPc, e.handlerPc, catchType)
        end
    end

    -- Method definition with locals.
    -- All locals will be defined as parameters, which is fine.
    do
        yield(2, '%s = function(', index(name .. descriptor))
        local comma = ''
    
        for i = 1, method.attributes.code.maxLocals do
            yield(0, '%sl%u', comma, i - 1)
            comma = ', '
        end
    
        yield(0, ')\n')
    end

    -- Stack.
    do
        local maxStack = method.attributes.code.maxStack

        if maxStack > 0 then
            yield(3, 'local ')
            local comma = ''
    
            for i = 1, maxStack do
                yield(0, '%ss%u', comma, i - 1)
                comma = ', '
            end
    
            yield(0, '\n\n')
        end
    end

    local jumps = collectJumps(method, cpool)
    generateCode(method.attributes.code.code, class, jumps)

    yield(2, 'end,\n')
end

local function generate(vm, class)
    local cpool = class.constantPool
    local className = cpool[cpool[class.thisClass].nameIndex].bytes
    local crc = crc32(className)

    yield(0, 'local opcodes = require "luame.opcodes"\n')
    yield(0, '\n')
    
    yield(0, '-- %s\n', className)
    yield(0, 'return function(vm)\n')
        
    generateOpcodes()
    yield(0, '\n')

    generateUpvalues(class)
    yield(0, '\n')

    yield(1, '_%08x = {\n', crc)

    generatePost(class)
    yield(0, '\n')

    generateNew(class)

    for i = 1, class.methods.n do
        yield(0, '\n')
        generateMethod(class, class.methods[i])
    end

    yield(1, '}\n\n')
    yield(1, 'return _%08x\n', crc)
    yield(0, 'end\n')
end

return function(vm, class)
    local loader = coroutine.wrap(function()
        generate(vm, class)
    end)

    --[[while true do
        io.write(loader())
    end]]

    local cpool = class.constantPool
    local className = cpool[cpool[class.thisClass].nameIndex].bytes
    return load(loader, format('%s.lua', className), 't')
end
