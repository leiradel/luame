local buffer = require 'buffer'

local function readConstantClass(b)
    local constant = {
        nameIndex = b:peek(0, '2')
    }

    return constant, b:sub(2, b:size() - 1)
end

local function readConstantFieldref(b)
    local constant = {
        classIndex = b:peek(0, '2'),
        nameAndTypeIndex = b:peek(2, '2')
    }

    return constant, b:sub(4, b:size() - 1)
end

local function readConstantString(b)
    local constant = {
        stringIndex = b:peek(0, '2')
    }

    return constant, b:sub(2, b:size() - 1)
end

local function readConstantInteger(b)
    local constant = {
        bytes = b:peek(0, '4'),
        value = b:peek(0, 'I')
    }

    return constant, b:sub(4, b:size() - 1)
end

local function readConstantFloat(b)
    local constant = {
        bytes = b:peek(0, '4'),
        value = b:peek(0, 'F')
    }

    return constant, b:sub(4, b:size() - 1)
end

local function readConstantLong(b)
    local constant = {
        bytes = b:peek(0, '8'),
        value = b:peek(0, 'J')
    }

    return constant, b:sub(8, b:size() - 1)
end

local function readConstantDouble(b)
    local constant = {
        bytes = b:peek(0, '8'),
        value = b:peek(0, 'D')
    }

    return constant, b:sub(8, b:size() - 1)
end

local function readConstantNameAndType(b)
    local constant = {
        nameIndex = b:peek(0, '2'),
        descriptorIndex = b:peek(2, '2')
    }

    return constant, b:sub(4, b:size() - 1)
end

local function readConstantUtf8(b)
    local length = b:peek(0, '2')
    local bytes = b:sub(2, 2 + length - 1)

    local constant = {
        length = length,
        bytes = bytes,

        -- The field below doesn't exist in the CONSTANT_Utf8_info structure.
        string = tostring(bytes)
    }

    return constant, b:sub(2 + length, b:size() - 1)
end

local function readConstantPoolEntry(b)
    local tag = b:peek(0, '1')
    b = b:sub(1, b:size() - 1)

    if tag == 7 then
        return class:readConstantClass(b)
    elseif tag == 9 then
        return class:readConstantFieldref(b)
    elseif tag == 10 then
        return class:readConstantFieldref(b) -- readConstantMethodref
    elseif tag == 11 then
        return class:readConstantFieldref(b) -- readConstantInterfaceMethodRef
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
        error('Invalid tag in constant pool: %u', tag)
    end
end

local function readConstantPool(cpoolCount, b)
    local cpool = {}

    for i = 1, cpoolCount do
        cpool[i], b = readConstantPoolEntry(b)
    end

    return cpool, b
end

local function readInterfaces(interfacesCount, b)
    local interfaces = {}

    for i = 1, interfacesCount do
        interfaces[i] = b:peek((i - 1) * 2, '2')
    end

    return interfaces, b:sub(interfacesCount * 2, b:size() - 1)
end

local function readAttributeEntry(b)
end

local function readAttributes(attributesCount, b)
    local attributes = {}

    for i = 1, attributesCount do
        attributes[i], b = readAttributeEntry(b)
    end

    return attributes, b
end

local function readFieldEntry(b)
    local attributesCount = b:peek(6, '2')
    local attributes, b = readAttributes(attributesCount, b:sub(8, b:size() - 1))

    local field = {
        accessFlags = b:peek(0, '2'),
        nameIndex = b:peek(2, '2'),
        descriptorIndex = b:peek(4, '2'),
        attributesCount = attributesCount,
        attributes = attributes
    }

    return field, b
end

local function readFields(fieldsCount, b)
    local fields = {}

    for i = 1, fieldsCount do
        fields[i], b = readFieldEntry(b)
    end

    return fields
end

local function defineClass(string)
    local b = buffer.new(string)
    local magic = b:peek(0, '4')

    if magic ~= 0xcafebabe then
        error('Invalid magic number: 0x%08x', magic)
    end

    local minor = b:peek(4, '2')
    local major = b:peek(6, '2')

    if major > 47 then
        -- Versions greater than JDK 1.3 are an error.
        error('Unsupported class version %u.%u', major, minor)
    end

    local cpoolCount = b:peek(8, '2') - 1
    local cpool, b = readConstantPool(cpoolCount, b:sub(10, b:size() - 1))

    local accessFlags = b:peek(0, '2')
    local thisClass = b:peek(2, '2')
    local superClass = b:peek(4, '2')
    
    local interfacesCount = b:peek(6, '2')
    local interfaces, b = readInterfaces(interfacesCount, b:sub(8, b:size() - 1))

    local fieldsCount = b:peek(0, '2')
    local fields, b = readFields(fieldsCount, b:sub(2, b:size() - 1))
end

return defineClass
