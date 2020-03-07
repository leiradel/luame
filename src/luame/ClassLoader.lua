import 'luame.Buffer'

local function defineClass(class, string)
    local b = luame.Buffer(string)
    local magic = b:peek(0, '4')

    if magic ~= 0xcafebabe then
        error(java.lang.ClassFormatError('Invalid magic number: 0x%08x', magic))
    end

    local minor = b:peek(4, '2')
    local major = b:peek(6, '2')

    if major > 47 then
        -- Versions greater than JDK 1.3 are an error.
        error(java.lang.ClassFormatError('Unsupported class version %u.%u', major, minor))
    end

    local cpoolCount = b:peek(8, '2') - 1
    local cpool = class:readConstantPool(cpoolCount, b:sub(10, -1))
end

local function readConstantPool(class, cpoolCount, b)
    local cpool = {}

    for i = 1, cpoolCount do
        cpool[#cpool + 1], b = class:readConstantPoolEntry(b)
    end

    return cpool
end

local function readConstantPoolEntry(class, b)
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
        error(java.lang.ClassFormatError('Invalid tag in constant pool: %u', tag))
    end
end

local function readConstantClass(class, b)
    local constant = {
        nameIndex = b:peek(0, '2')
    }

    return constant, b:sub(2, b:size() - 1)
end

local function readConstantFieldref(class, b)
    local constant = {
        classIndex = b:peek(0, '2'),
        nameAndTypeIndex = b:peek(2, '2')
    }

    return constant, b:sub(4, b:size() - 1)
end

local function readConstantString(class, b)
    local constant = {
        stringIndex = b:peek(0, '2')
    }

    return constant, b:sub(2, b:size() - 1)
end

local function readConstantInteger(class, b)
    local constant = {
        bytes = b:peek(0, '4'),
        value = b:peek(0, 'I')
    }

    return constant, b:sub(4, b:size() - 1)
end

local function readConstantFloat(class, b)
    local constant = {
        bytes = b:peek(0, '4'),
        value = b:peek(0, 'F')
    }

    return constant, b:sub(4, b:size() - 1)
end

local function readConstantLong(class, b)
    local constant = {
        bytes = b:peek(0, '8'),
        value = b:peek(0, 'J')
    }

    return constant, b:sub(8, b:size() - 1)
end

local function readConstantDouble(class, b)
    local constant = {
        bytes = b:peek(0, '8'),
        value = b:peek(0, 'D')
    }

    return constant, b:sub(8, b:size() - 1)
end

local function readConstantNameAndType(class, b)
    local constant = {
        nameIndex = b:peek(0, '2'),
        descriptorIndex = b:peek(2, '2')
    }

    return constant, b:sub(4, b:size() - 1)
end

return luame.newClass('luame.ClassLoader', {
    defineClass = defineClass,
    readConstantPool = readConstantPool,
    readConstantPoolEntry = readConstantPoolEntry,
})
