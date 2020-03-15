-- Size in bytes, delta stack in slots, insn name
local opcodes = {
    [0x00] = {  1,   0, 'nop'},
    [0x01] = {  1,   1, 'aconst_null'},
    [0x02] = {  1,   1, 'iconst_m1'},
    [0x03] = {  1,   1, 'iconst_0'},
    [0x04] = {  1,   1, 'iconst_1'},
    [0x05] = {  1,   1, 'iconst_2'},
    [0x06] = {  1,   1, 'iconst_3'},
    [0x07] = {  1,   1, 'iconst_4'},
    [0x08] = {  1,   1, 'iconst_5'},
    [0x09] = {  1,   1, 'lconst_0'},
    [0x0a] = {  1,   1, 'lconst_1'},
    [0x0b] = {  1,   1, 'fconst_0'},
    [0x0c] = {  1,   1, 'fconst_1'},
    [0x0d] = {  1,   1, 'fconst_2'},
    [0x0e] = {  1,   1, 'dconst_0'},
    [0x0f] = {  1,   1, 'dconst_1'},
    [0x10] = {  2,   1, 'bipush'},
    [0x11] = {  3,   1, 'sipush'},
    [0x12] = {  2,   1, 'ldc'},
    [0x13] = {  3,   1, 'ldc_w'},
    [0x14] = {  3,   1, 'ldc2_w'},
    [0x15] = {  2,   1, 'iload'},
    [0x16] = {  2,   1, 'lload'},
    [0x17] = {  2,   1, 'fload'},
    [0x18] = {  2,   1, 'dload'},
    [0x19] = {  2,   1, 'aload'},
    [0x1a] = {  1,   1, 'iload_0'},
    [0x1b] = {  1,   1, 'iload_1'},
    [0x1c] = {  1,   1, 'iload_2'},
    [0x1d] = {  1,   1, 'iload_3'},
    [0x1e] = {  1,   1, 'lload_0'},
    [0x1f] = {  1,   1, 'lload_1'},
    [0x20] = {  1,   1, 'lload_2'},
    [0x21] = {  1,   1, 'lload_3'},
    [0x22] = {  1,   1, 'fload_0'},
    [0x23] = {  1,   1, 'fload_1'},
    [0x24] = {  1,   1, 'fload_2'},
    [0x25] = {  1,   1, 'fload_3'},
    [0x26] = {  1,   1, 'dload_0'},
    [0x27] = {  1,   1, 'dload_1'},
    [0x28] = {  1,   1, 'dload_2'},
    [0x29] = {  1,   1, 'dload_3'},
    [0x2a] = {  1,   1, 'aload_0'},
    [0x2b] = {  1,   1, 'aload_1'},
    [0x2c] = {  1,   1, 'aload_2'},
    [0x2d] = {  1,   1, 'aload_3'},
    [0x2e] = {  1,  -1, 'iaload'},
    [0x2f] = {  1,  -1, 'laload'},
    [0x30] = {  1,  -1, 'faload'},
    [0x31] = {  1,  -1, 'daload'},
    [0x32] = {  1,  -1, 'aaload'},
    [0x33] = {  1,  -1, 'baload'},
    [0x34] = {  1,  -1, 'caload'},
    [0x35] = {  1,  -1, 'saload'},
    [0x36] = {  2,  -1, 'istore'},
    [0x37] = {  2,  -1, 'lstore'},
    [0x38] = {  2,  -1, 'fstore'},
    [0x39] = {  2,  -1, 'dstore'},
    [0x3a] = {  2,  -1, 'astore'},
    [0x3b] = {  1,  -1, 'istore_0'},
    [0x3c] = {  1,  -1, 'istore_1'},
    [0x3d] = {  1,  -1, 'istore_2'},
    [0x3e] = {  1,  -1, 'istore_3'},
    [0x3f] = {  1,  -1, 'lstore_0'},
    [0x40] = {  1,  -1, 'lstore_1'},
    [0x41] = {  1,  -1, 'lstore_2'},
    [0x42] = {  1,  -1, 'lstore_3'},
    [0x43] = {  1,  -1, 'fstore_0'},
    [0x44] = {  1,  -1, 'fstore_1'},
    [0x45] = {  1,  -1, 'fstore_2'},
    [0x46] = {  1,  -1, 'fstore_3'},
    [0x47] = {  1,  -1, 'dstore_0'},
    [0x48] = {  1,  -1, 'dstore_1'},
    [0x49] = {  1,  -1, 'dstore_2'},
    [0x4a] = {  1,  -1, 'dstore_3'},
    [0x4b] = {  1,  -1, 'astore_0'},
    [0x4c] = {  1,  -1, 'astore_1'},
    [0x4d] = {  1,  -1, 'astore_2'},
    [0x4e] = {  1,  -1, 'astore_3'},
    [0x4f] = {  1,  -3, 'iastore'},
    [0x50] = {  1,  -3, 'lastore'},
    [0x51] = {  1,  -3, 'fastore'},
    [0x52] = {  1,  -3, 'dastore'},
    [0x53] = {  1,  -3, 'aastore'},
    [0x54] = {  1,  -3, 'bastore'},
    [0x55] = {  1,  -3, 'castore'},
    [0x56] = {  1,  -3, 'sastore'},
    [0x57] = {  1,  -1, 'pop'},
    [0x57] = {  1,  -2, 'pop2'}, -- TODO
    [0x59] = {  1,   1, 'dup'},
    [0x5a] = {  1,   1, 'dup_x1'},
    [0x5b] = {  1,   1, 'dup_x2'}, -- TODO
    [0x5c] = {  1,   2, 'dup2'}, -- TODO
    [0x5d] = {  1,   2, 'dup2_x1'}, -- TODO
    [0x5e] = {  1,   2, 'dup2_x2'}, -- TODO
    [0x5f] = {  1,   0, 'swap'},
    [0x60] = {  1,  -1, 'iadd'},
    [0x61] = {  1,  -1, 'ladd'},
    [0x62] = {  1,  -1, 'fadd'},
    [0x63] = {  1,  -1, 'dadd'},
    [0x64] = {  1,  -1, 'isub'},
    [0x65] = {  1,  -1, 'lsub'},
    [0x66] = {  1,  -1, 'fsub'},
    [0x67] = {  1,  -1, 'dsub'},
    [0x68] = {  1,  -1, 'imul'},
    [0x69] = {  1,  -1, 'lmul'},
    [0x6a] = {  1,  -1, 'fmul'},
    [0x6b] = {  1,  -1, 'dmul'},
    [0x6c] = {  1,  -1, 'idiv'},
    [0x6d] = {  1,  -1, 'ldiv'},
    [0x6e] = {  1,  -1, 'fdiv'},
    [0x6f] = {  1,  -1, 'ddiv'},
    [0x70] = {  1,  -1, 'irem'},
    [0x71] = {  1,  -1, 'lrem'},
    [0x72] = {  1,  -1, 'frem'},
    [0x73] = {  1,  -1, 'drem'},
    [0x74] = {  1,   0, 'ineg'},
    [0x75] = {  1,   0, 'lneg'},
    [0x76] = {  1,   0, 'fneg'},
    [0x77] = {  1,   0, 'dneg'},
    [0x78] = {  1,  -1, 'ishl'},
    [0x79] = {  1,  -1, 'lshl'},
    [0x7a] = {  1,  -1, 'ishr'},
    [0x7b] = {  1,  -1, 'lshr'},
    [0x7c] = {  1,  -1, 'iushr'},
    [0x7d] = {  1,  -1, 'lushr'},
    [0x7e] = {  1,  -1, 'iand'},
    [0x7f] = {  1,  -1, 'land'},
    [0x80] = {  1,  -1, 'ior'},
    [0x81] = {  1,  -1, 'lor'},
    [0x82] = {  1,  -1, 'ixor'},
    [0x83] = {  1,  -1, 'lxor'},
    [0x84] = {  3,   0, 'iinc'},
    [0x85] = {  1,   0, 'i2l'},
    [0x86] = {  1,   0, 'i2f'},
    [0x87] = {  1,   0, 'i2d'},
    [0x88] = {  1,   0, 'l2i'},
    [0x89] = {  1,   0, 'l2f'},
    [0x8a] = {  1,   0, 'l2d'},
    [0x8b] = {  1,   0, 'f2i'},
    [0x8c] = {  1,   0, 'f2l'},
    [0x8d] = {  1,   0, 'f2d'},
    [0x8e] = {  1,   0, 'd2i'},
    [0x8f] = {  1,   0, 'd2l'},
    [0x90] = {  1,   0, 'd2f'},
    [0x91] = {  1,   0, 'i2b'},
    [0x92] = {  1,   0, 'i2c'},
    [0x93] = {  1,   0, 'i2s'},
    [0x94] = {  1,  -1, 'lcmp'},
    [0x95] = {  1,  -1, 'fcmpl'},
    [0x96] = {  1,  -1, 'fcmpg'},
    [0x97] = {  1,  -1, 'dcmpl'},
    [0x98] = {  1,  -1, 'dcmpg'},
    [0x99] = {  3,  -1, 'ifeq'},
    [0x9a] = {  3,  -1, 'ifne'},
    [0x9b] = {  3,  -1, 'iflt'},
    [0x9c] = {  3,  -1, 'ifge'},
    [0x9d] = {  3,  -1, 'ifgt'},
    [0x9e] = {  3,  -1, 'ifle'},
    [0x9f] = {  3,  -2, 'if_icmpeq'},
    [0xa0] = {  3,  -2, 'if_icmpne'},
    [0xa1] = {  3,  -2, 'if_icmplt'},
    [0xa2] = {  3,  -2, 'if_icmpge'},
    [0xa3] = {  3,  -2, 'if_icmpgt'},
    [0xa4] = {  3,  -2, 'if_icmple'},
    [0xa5] = {  3,  -2, 'if_acmpeq'},
    [0xa6] = {  3,  -2, 'if_acmpne'},
    [0xa7] = {  3,   0, 'goto'},
    [0xa8] = {  3,   1, 'jsr'},
    [0xa9] = {  2,   0, 'ret'},
    [0xaa] = {nil,  -1, 'tableswitch'},
    [0xab] = {nil,  -1, 'lookupswitch'},
    [0xac] = {  1,   0, 'ireturn'},
    [0xad] = {  1,   0, 'lreturn'},
    [0xae] = {  1,   0, 'freturn'},
    [0xaf] = {  1,   0, 'dreturn'},
    [0xb0] = {  1,   0, 'areturn'},
    [0xb1] = {  1,   0, 'return'},
    [0xb2] = {  3,   1, 'getstatic'},
    [0xb3] = {  3,  -1, 'putstatic'},
    [0xb4] = {  3,   0, 'getfield'},
    [0xb5] = {  3,  -2, 'putfield'},
    [0xb6] = {  3, nil, 'invokevirtual'},
    [0xb7] = {  3, nil, 'invokespecial'},
    [0xb8] = {  3, nil, 'invokestatic'},
    [0xb9] = {  5, nil, 'invokeinterface'},
    [0xbb] = {  3,   1, 'new'},
    [0xbc] = {  2,   0, 'newarray'},
    [0xbd] = {  3,   0, 'anewarray'},
    [0xbe] = {  1,   0, 'arraylength'},
    [0xbf] = {  1,   0, 'athrow'},
    [0xc0] = {  3,   0, 'checkcast'},
    [0xc1] = {  3,   0, 'instanceof'},
    [0xc2] = {  1,  -1, 'monitorenter'},
    [0xc3] = {  1,  -1, 'monitorexit'},
    [0xc4] = {nil, nil, 'wide'},
    [0xc5] = {  4, nil, 'multianewarray'},
    [0xc6] = {  3,  -1, 'ifnull'},
    [0xc7] = {  3,  -1, 'ifnonnull'},
    [0xc8] = {  5,   0, 'goto_w'},
    [0xc9] = {  5,   1, 'jsr_w'},
}

--[===[
local lengths = {[0] = 0, 0, 0, 0}

for i = 0, 255 do
    local op = opcodes[i]
    local name

    if op then
        name = op[3]

        if i == 0 then
            name = string.format('[0] = %q', name)
        else
            name = string.format('%q', name)
        end
    else
        name = string.format('nil --[[%02x]]', i)
    end

    local j = i & 3
    lengths[j] = math.max(lengths[j], #name)
end

io.write('local names = {\n')

for i = 0, 255 do
    local op = opcodes[i]
    local name

    if op then
        name = op[3]

        if i == 0 then
            name = string.format('[0] = %q', name)
        else
            name = string.format('%q', name)
        end
    else
        name = string.format('nil --[[%02x]]', i)
    end

    local j = i & 3
    local indent = string.rep(' ', j == 0 and 4 or 0)
    local fill = string.rep(' ', lengths[j] - #name + 1)
    local eol = j == 3 and '\n' or ''
    io.write(string.format('%s%s,%s%s', indent, name, fill, eol))
end

io.write('}\n')
]===]

--[===[
io.write('local deltasps = {\n')

for i = 0, 255 do
    local op = opcodes[i]
    local delta

    if op and op[2] then
        delta = string.format(' %2d', op[2])
    else
        delta = 'nil'
    end

    local j = i & 15
    local indent = string.rep(' ', j == 0 and 4 or 0)
    local eol = j == 15 and '\n' or ''
    io.write(string.format('%s%s, %s', indent, delta, eol))
end

io.write('}\n\n')

for i = 0, 255 do
    local op = opcodes[i]

    if op and not op[2] then
        io.write('-- ', op[3], '\n')
        io.write(string.format('deltasps[0x%02x] = function(b)\n', i))
        io.write('end\n\n')
    end
end
]===]

io.write('local sizes = {\n')

for i = 0, 255 do
    local op = opcodes[i]
    local size

    if op and op[1] then
        size = string.format('  %d', op[1])
    else
        size = 'nil'
    end

    local j = i & 15
    local indent = string.rep(' ', j == 0 and 4 or 0)
    local eol = j == 15 and '\n' or ''
    io.write(string.format('%s%s, %s', indent, size, eol))
end

io.write('}\n\n')

for i = 0, 255 do
    local op = opcodes[i]

    if op and not op[1] then
        io.write('-- ', op[3], '\n')
        io.write(string.format('sizes[0x%02x] = function(b)\n', i))
        io.write('end\n\n')
    end
end
