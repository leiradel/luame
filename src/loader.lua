local log = require 'luame.log'

local function xlateAccessFlags(flags)
    local set = {}

    set.public    = (flags & 0x0001) ~= 0
    set.private   = (flags & 0x0002) ~= 0
    set.protected = (flags & 0x0004) ~= 0
    set.static    = (flags & 0x0008) ~= 0
    set.final     = (flags & 0x0010) ~= 0
    set.super     = (flags & 0x0020) ~= 0
    set.volatile  = (flags & 0x0040) ~= 0
    set.transient = (flags & 0x0080) ~= 0
    set.native    = (flags & 0x0100) ~= 0
    set.interface = (flags & 0x0200) ~= 0
    set.abstract  = (flags & 0x0400) ~= 0
    set.strict    = (flags & 0x0800) ~= 0

    return set
end

local function readConstantClass(b)
    return {
        tag = 'class',
        nameIndex = b:read '2'
    }
end

local function readConstantFieldref(b)
    return {
        tag = 'fieldref',
        classIndex = b:read '2',
        nameAndTypeIndex = b:read '2'
    }
end

local function readConstantMethodref(b)
    return {
        tag = 'methodref',
        classIndex = b:read '2',
        nameAndTypeIndex = b:read '2'
    }
end

local function readConstantInterfaceMethodref(b)
    return {
        tag = 'interfaceMethodref',
        classIndex = b:read '2',
        nameAndTypeIndex = b:read '2'
    }
end

local function readConstantString(b)
    return {
        tag = 'string',
        stringIndex = b:read '2'
    }
end

local function readConstantInteger(b)
    return {
        tag = 'integer',
        -- bytes is the actual integer, not the bytes.
        bytes = b:read 'I'
    }
end

local function readConstantFloat(b)
    return {
        tag = 'float',
        -- bytes is the actual float, not the bytes.
        bytes = b:read 'F'
    }
end

local function readConstantLong(b)
    return {
        tag = 'long',
        -- bytes is the actual long, not the bytes.
        bytes = b:read 'J'
    }
end

local function readConstantDouble(b)
    return {
        tag = 'double',
        -- bytes is the actual double, not the bytes.
        bytes = b:read 'D'
    }
end

local function readConstantNameAndType(b)
    return {
        tag = 'nameAndType',
        nameIndex = b:read '2',
        descriptorIndex = b:read '2'
    }
end

local function readConstantUtf8(b)
    local length = b:read '2'
    local bytes = b:read(length)

    return {
        tag = 'utf8',
        length = length,
        -- bytes is the actual string, not the bytes.
        bytes = bytes
    }
end

local function readConstant(b)
    local tag = b:read '1'

    if tag == 7 then
        return readConstantClass(b)
    elseif tag == 9 then
        return readConstantFieldref(b)
    elseif tag == 10 then
        return readConstantMethodref(b)
    elseif tag == 11 then
        return readConstantInterfaceMethodref(b)
    elseif tag == 8 then
        return readConstantString(b)
    elseif tag == 3 then
        return readConstantInteger(b)
    elseif tag == 4 then
        return readConstantFloat(b)
    elseif tag == 5 then
        return readConstantLong(b)
    elseif tag == 6 then
        return readConstantDouble(b)
    elseif tag == 12 then
        return readConstantNameAndType(b)
    elseif tag == 1 then
        return readConstantUtf8(b)
    else
        error(string.format('invalid tag in constant pool: %u', tag))
    end
end

local function readConstantPool(n, b)
    local cpool = {n = n}
    local i = 1

    while i <= n do
        local const = readConstant(b)
        cpool[i] = const
        i = i + 1

        if const.tag == 'long' or const.tag == 'double' then
            -- Longs and doubles take two slots.
            i = i + 1
        end
    end

    return cpool
end

local function readInterfaces(n, b)
    local interfaces = {n = n}

    for i = 1, n do
        interfaces[i] = b:read '2'
    end

    return interfaces
end

local readAttributes

local function readConstantValue(b)
    return {
        tag = 'constantValue',
        index = b:read '2'
    }
end

local function readExceptionTable(n, b)
    local table = {n = n}

    for i = 1, n do
        table[i] = {
            startPc = b:read '2',
            endPc = b:read '2',
            handlerPc = b:read '2',
            catchType = b:read '2'
        }
    end

    return table
end

local function readCode(b, cpool)
    local maxStack = b:read '2'
    local maxLocals = b:read '2'
    local codeLength = b:read '4'

    local pos = b:tell()
    local code = b:sub(pos, codeLength)
    b:seek(codeLength, 'cur')

    local exceptionTableLength = b:read '2'
    local exceptionTable = readExceptionTable(exceptionTableLength, b)
    local attributesCount = b:read '2'
    local attributes = readAttributes(attributesCount, b, cpool)

    return {
        tag = 'code',
        maxStack = maxStack,
        maxLocals = maxLocals,
        code = code,
        exceptionTable = exceptionTable,
        attributes = attributes
    }
end

local function readExceptions(b)
    local n = b:read '2'
    local indexTable = {n = n}

    for i = 1, n do
        indexTable[i] = b:read '2'
    end

    return {
        tag = 'exceptions',
        indexTable = indexTable
    }
end

local function readSynthetic(b)
    return {
        tag = 'synthetic'
    }
end

local function readSourceFile(b)
    return {
        tag = 'sourceFile',
        index = b:read '2'
    }
end

local function readLineNumberTable(b)
    local n = b:read '2'
    local table = {n = n}

    for i = 1, n do
        table[i] = {
            startPc = b:read '2',
            lineNumber = b:read '2'
        }
    end

    return {
        tag = 'lineNumberTable',
        table = table
    }
end

local function readLocalVariableTable(b)
    local n = b:read '2'
    local table = {n = n}

    for i = 1, n do
        table[i] = {
            startPc = b:read '2',
            length = b:read '2',
            nameIndex = b:read '2',
            descriptorIndex = b:read '2',
            index = b:read '2'
        }
    end

    return {
        tag = 'localVariableTable',
        table = table
    }
end

local function readDeprecated(b)
    return {
        tag = 'deprecated'
    }
end

local function readInnerClasses(b)
    local n = b:read '2'
    local classes = {n = n}

    for i = 1, n do
        classes[i] = {
            innerClassInfoIndex = b:read '2',
            outerClassInfoIndex = b:read '2',
            innerNameIndex = b:read '2',
            innerClassAccessFlags = xlateAccessFlags(b:read '2')
        }
    end

    return {
        tag = 'innerClasses',
        classes = classes
    }
end

local warned = _LUAME_DEBUG and {} or nil

local function readAttribute(b, cpool)
    local nameIndex = b:read '2'
    local name = cpool[nameIndex]

    if type(name) ~= 'table' or name.tag ~= 'utf8' then
        error(string.format('invalid attribute nameIndex: %u', nameIndex))
    end

    local length = b:read '4'
    local tag = name.bytes

    if tag == 'ConstantValue' then
        return readConstantValue(b)
    elseif tag == 'Code' then
        return readCode(b, cpool)
    elseif tag == 'Exceptions' then
        return readExceptions(b)
    elseif tag == 'Synthetic' then
        return readSynthetic(b)
    elseif tag == 'SourceFile' then
        return readSourceFile(b)
    elseif tag == 'LineNumberTable' then
        return readLineNumberTable(b)
    elseif tag == 'LocalVariableTable' then
        return readLocalVariableTable(b)
    elseif tag == 'Deprecated' then
        return readDeprecated(b)
    elseif tag == 'InnerClasses' then
        return readInnerClasses(b)
    else
        if _LUAME_DEBUG then
            if not warned[tag] then
                log.warn('Skipping unknown attribute ', tag, ' (only showed once)')
                warned[tag] = true
            end
        end

        b:seek(length, 'cur')
    end
end

-- This function is local because of the forward-declare above.
function readAttributes(n, b, cpool)
    local attributes = {n = n}

    for i = 1, n do
        local attribute = readAttribute(b, cpool)

        if attribute then
            attributes[i] = attribute
            attributes[attribute.tag] = attribute
        end
    end

    return attributes
end

local function readFields(n, b, cpool)
    local fields = {n = n}

    for i = 1, n do
        local accessFlags = xlateAccessFlags(b:read '2')
        local nameIndex = b:read '2'
        local descriptorIndex = b:read '2'
        local attributesCount = b:read '2'
        local attributes = readAttributes(attributesCount, b, cpool)

        local field = {
            accessFlags = accessFlags,
            nameIndex = nameIndex,
            descriptorIndex = descriptorIndex,
            attributes = attributes
        }

        local name = cpool[nameIndex].bytes
        local descriptor = cpool[descriptorIndex].bytes

        fields[i] = field
        fields[string.format('%s(%s)', name, descriptor)] = field
    end

    return fields
end

local function readMethods(n, b, cpool)
    local methods = {n = n}

    for i = 1, n do
        local accessFlags = xlateAccessFlags(b:read '2')
        local nameIndex = b:read '2'
        local descriptorIndex = b:read '2'
        local attributesCount = b:read '2'
        local attributes = readAttributes(attributesCount, b, cpool)

        local method = {
            accessFlags = accessFlags,
            nameIndex = nameIndex,
            descriptorIndex = descriptorIndex,
            attributes = attributes
        }

        local name = cpool[nameIndex].bytes
        local descriptor = cpool[descriptorIndex].bytes

        methods[i] = method
        methods[string.format('%s%s', name, descriptor)] = method
    end

    return methods
end

return function(b)
    local magic = b:read '4'

    if magic ~= 0xcafebabe then
        error(string.format('invalid magic number: 0x%08x', magic))
    end

    local minor = b:read '2'
    local major = b:read '2'

    if major > 47 then
        -- Versions greater than JDK 1.3 are an error.
        error(string.format('unsupported class version %u.%u', major, minor))
    end

    local cpoolCount = (b:read '2') - 1
    local cpool = readConstantPool(cpoolCount, b)

    local accessFlags = xlateAccessFlags(b:read '2')
    local thisClass = b:read '2'
    local superClass = b:read '2'
    
    local interfacesCount = b:read '2'
    local interfaces = readInterfaces(interfacesCount, b)

    local fieldsCount = b:read '2'
    local fields = readFields(fieldsCount, b, cpool)

    local methodsCount = b:read '2'
    local methods = readMethods(methodsCount, b, cpool)

    local attributesCount = b:read '2'
    local attributes = readAttributes(attributesCount, b, cpool)

    return {
        magic = magic,
        major = major,
        minor = minor,
        constantPool = cpool,
        accessFlags = accessFlags,
        thisClass = thisClass,
        superClass = superClass,
        interfaces = interfaces,
        fields = fields,
        methods = methods,
        attributes = attributes
    }
end
