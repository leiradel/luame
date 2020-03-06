import 'luame.Buffer'

local function defineClass(string)
    local b = luame.Buffer(string)
    local magic = b:peek(0, '4')

    if magic ~= 0xcafebabe then
        error(java.lang.ClassFormatError('Invalid magic number: 0x%08x', magic))
    end

    local minor = b:peek(4, '2')
    local major = b:peek(6, '2')

    if major > 1 or (major == 1 and minor > 3) then
        error(java.lang.ClassFormatError('Unsupported class version %u.%u', major, minor))
    end

    local cpoolCount = b:peek(8, '2')

    local cpool = readConstantPool(cpoolCount, b:sub(10, -1))
end
        
return function(class)
    class.defineClass = function(string)
        -- Protected call to real method so we catch Buffer errors.
        local ok, definedClass = xpcall(defineClass, debug.traceback, string)

        if not ok then
            local e = definedClass

            if type(e) == 'string' then
                e = java.lang.ClassFormatError(e)
            end

            error(e)
        end

        return definedClass
    end
end
