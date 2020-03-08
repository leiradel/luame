local buffer = require 'buffer'

local function readConstantClass(b)
    return {
        tag = 'class',
        nameIndex = b:read('2')
    }
end

local function readConstantFieldref(b)
    return {
        tag = 'fieldref',
        classIndex = b:read('2'),
        nameAndTypeIndex = b:read('2')
    }
end

local function readConstantMethodref(b)
    return {
        tag = 'methodref',
        classIndex = b:read('2'),
        nameAndTypeIndex = b:read('2')
    }
end

local function readConstantInterfaceMethodref(b)
    return {
        tag = 'interfaceMethodref',
        classIndex = b:read('2'),
        nameAndTypeIndex = b:read('2')
    }
end

local function readConstantString(b)
    return {
        tag = 'string',
        stringIndex = b:read('2')
    }
end

local function readConstantInteger(b)
    return {
        tag = 'integer',
        -- bytes is the actual integer, not the bytes.
        bytes = b:read('I')
    }
end

local function readConstantFloat(b)
    return {
        tag = 'float',
        -- bytes is the actual float, not the bytes.
        bytes = b:read('F')
    }
end

local function readConstantLong(b)
    return {
        tag = 'long',
        -- bytes is the actual long, not the bytes.
        bytes = b:read('J')
    }
end

local function readConstantDouble(b)
    return {
        tag = 'double',
        -- bytes is the actual double, not the bytes.
        bytes = b:read('D')
    }
end

local function readConstantNameAndType(b)
    return {
        tag = 'nameAndType',
        nameIndex = b:read('2'),
        descriptorIndex = b:read('2')
    }
end

local function readConstantUtf8(b)
    local length = b:read('2')
    local bytes = b:read(length)

    return {
        tag = 'utf8',
        length = length,
        -- bytes is the actual string, not the bytes.
        bytes = bytes
    }
end

local function readConstant(b)
    local tag = b:read('1')

    if tag == 7 then
        return class:readConstantClass(b)
    elseif tag == 9 then
        return class:readConstantFieldref(b)
    elseif tag == 10 then
        return class:readConstantMethodref(b)
    elseif tag == 11 then
        return class:readConstantInterfaceMethodref(b)
    elseif tag == 8 then
        return class:readConstantString(b)
    elseif tag == 3 then
        return class:readConstantInteger(b)
    elseif tag == 4 then
        return class:readConstantFloat(b)
    elseif tag == 5 then
        return class:readConstantLong(b)
    elseif tag == 6 then
        return class:readConstantDouble(b)
    elseif tag == 12 then
        return class:readConstantNameAndType(b)
    elseif tag == 1 then
        return class:readConstantUtf8(b)
    else
        error(string.format('Invalid constant in constnt pool: %u', tag))
    end
end

local function readConstantPool(cpoolCount, b)
    local cpool = {}

    for i = 1, cpoolCount do
        cpool[i] = readConstant(b)
    end

    return cpool
end

local function readInterfaces(interfacesCount, b)
    local interfaces = {}

    for i = 1, interfacesCount do
        interfaces[i] = b:read('2')
    end

    return interfaces
end

local readAttributes

local function readConstantValue(b)
    return {
        tag = 'constantValue',
        index = b:read('2')
    }
end

local function readExceptionTable(tableLength, b)
    local table = {}

    for i = 1, tableLength do
        table[i] = {
            startPc = read('2'),
            endPc = read('2'),
            handlerPc = read('2'),
            catchType = read('2')
        }
    end

    return table
end

local function readCode(b, cpool)
    local maxStack = b:read('2')
    local maxLocals = b:read('2')
    local codeLength = b:read('4')
    local code = b:read(codeLength)
    local exceptionTableLength = b:read('2')
    local exceptionTable = readExceptionTable(exceptionTableLength, b)
    local attributesCount = b:read('2')
    local attributes = readAttributes(attributesCount, b, cpool)

    return {
        tag = 'code',
        maxStack = maxStack,
        maxLocals = maxLocals,
        codeLength = codeLength,
        code = code,
        exceptionTableLength = exceptionTableLength,
        exceptionTable = exceptionTable,
        attributesCount = attributesCount,
        attributes = attributes
    }
end

local function readExceptions(b)
    local number = b:read('2')
    local indexTable = {}

    for i = 1, number do
        indexTable[i] = b:read('2')
    end

    return {
        tag = 'exceptions',
        number = number,
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
        index = b:read('2')
    }
end

local function readLineNumberTable(b)
    local tableLength = b:read('2')
    local table = {}

    for i = 1, tableLength do
        table[i] = {
            startPc = b:read('2'),
            lineNumber = b:read('2')
        }
    end

    return {
        tag = 'lineNumberTable',
        tableLength = tableLength,
        table = table
    }
end

local function readLocalVariableTable(b)
    local tableLength = b:read('2')
    local table = {}

    for i = 1, tableLength do
        table[i] = {
            startPc = b:read('2'),
            length = b:read('2'),
            nameIndex = b:read('2'),
            descriptorIndex = b:read('2'),
            index = b:read('2')
        }
    end

    return {
        tag = 'localVariableTable',
        tableLength = tableLength,
        table = table
    }
end

local function readDeprecated(b)
    return {
        tag = 'deprecated'
    }
end

local function readAttribute(b, cpool)
    local nameIndex = b:read('2')
    local name = cpool[nameIndex]

    if not name or name.tag ~= 'utf8' then
        error(string.format('Invalid attribute nameIndex: %u', nameIndex))
    end

    local length = b:read('4')
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
    else
        b:seek(length, 'cur')
    end
end

-- Local since forward-declared above.
function readAttributes(attributesCount, b, cpool)
    local attributes = {}

    for i = 1, attributesCount do
        attributes[i] = readAttribute(b, cpool)
    end

    return attributes
end

local function readFields(fieldsCount, b)
    local fields = {}

    for i = 1, fieldsCount do
        local accessFlags = b:read('2')
        local nameIndex = b:read('2')
        local descriptorIndex = b:read('2')
        local attributesCount = b:read('2')
        local attributes = readAtributes(attributesCount)

        fields[i] = {
            accessFlags = accessFlags,
            nameIndex = nameIndex,
            descriptorIndex = descriptorIndex,
            attributesCount = attributesCount,
            attributes = attributes
        }
    end

    return fields
end

local function readMethods(methodsCount, b)
    local methods = {}

    for i = 1, methodsCount do
        local accessFlags = b:read('2')
        local nameIndex = b:read('2')
        local descriptorIndex = b:read('2')
        local attributesCount = b:read('2')
        local attributes = readAtributes(attributesCount)

        methods[i] = {
            accessFlags = accessFlags,
            nameIndex = nameIndex,
            descriptorIndex = descriptorIndex,
            attributesCount = attributesCount,
            attributes = attributes
        }
    end

    return methods
end

local function defineClass(string)
    local b = buffer.new(string)
    local magic = b:read('4')

    if magic ~= 0xcafebabe then
        error(string.format('Invalid magic number: 0x%08x', magic))
    end

    local minor = b:read('2')
    local major = b:read('2')

    if major > 47 then
        -- Versions greater than JDK 1.3 are an error.
        error(string.format('Unsupported class version %u.%u', major, minor))
    end

    local cpoolCount = b:read('2') - 1
    local cpool = readConstantPool(cpoolCount, b)

    local accessFlags = b:read('2')
    local thisClass = b:read('2')
    local superClass = b:read('2')
    
    local interfacesCount = b:read('2')
    local interfaces = readInterfaces(interfacesCount, b)

    local fieldsCount = b:read('2')
    local fields = readFields(fieldsCount, b)

    local methodsCount = b:read('2')
    local methods = readMethods(methodsCount, b)

    local attributesCount = b:read('2')
    local attributes = readAttributes(attributesCount, b, cpool)

    return {
        magic = magic,
        major = major,
        minor = minor,
        constantPoolCount = cpoolCount,
        constantPool = cpool,
        accessFlags = accessFlags,
        thisClass = thisClass,
        superClass = superClass,
        interfacesCount = interfacesCount,
        interfaces = interfaces,
        fieldsCount = fieldsCount,
        fields = fields,
        methodsCount = methodsCount,
        methods = methods,
        attributesCount = attributesCount,
        attributes = attributes
    }
end

return defineClass
