local function params(descriptor)
    local iterator = function(s, var)
        local descriptor = s[1]
        local char = s[2]

        local k = descriptor:byte(char)
        local begin = char

        -- Skip array part.
        if k == 91 then -- '['
            repeat
                char = char + 1
                k = descriptor:byte(char)
            until k ~= 91 -- '['
        end

        -- Skip class name.
        if k == 76 then -- 'L'
            repeat
                char = char + 1
                k = descriptor:byte(char)
            until k == 59 -- ';'
        elseif k ~= 66 and k ~= 90 and k ~= 67 and k ~= 73 and k ~= 70 and k ~= 74 and k ~= 68 and k ~= 86 then
            -- End of interation, not 'B' 'Z' 'C' 'I' 'F' 'J' 'D' 'V'
            return nil
        end

        s[2] = char + 1

        local desc = descriptor:sub(begin, char)
        local slots = (k == 74 or k == 68) and 2 or 1 -- 'J' or 'D'
        return var + 1, desc, slots
    end

    local start = descriptor:byte(1, 1) == 40 and 2 or 1 -- '('
    return iterator, {descriptor, start}, 0
end

return {
    params = params,

    countParams = function(descriptor)
        local count = 0
    
        for _, _, _ in params(descriptor) do
            count = count + 1
        end
    
        return count
    end,

    countSlots = function(descriptor)
        local count = 0

        for _, _, slots in params(descriptor) do
            count = count + slots
        end

        return count
    end,

    isVoid = function(descriptor)
        return descriptor:byte(-1, -1) == 86 -- 'V'
    end,

    makeParams = function(descriptor, isStatic)
        local p = {'class'}
        local count = isStatic and 0 or 1

        for _, _, slots in params(descriptor) do
            count = count + slots
        end

        for i = 1, count do
            p[i + 1] = string.format('l%u', i - 1)
        end

        return p
    end,

    makeLocals = function(descriptor, isStatic, maxLocals)
        local p = {}
        local count = isStatic and 0 or 1

        for _, _, slots in params(descriptor) do
            count = count + slots
        end

        for i = count, maxLocals - 1 do
            p[#p + 1] = string.format('l%u', #p)
        end

        return p
    end,

    makeStack = function(maxStack)
        local p = {}

        for i = 1, maxStack do
            p[i] = string.format('s%u', i - 1)
        end

        return p
    end,

    parseManifest = function(buffer)
        local pairs = {}
    
        while true do
            local line = buffer:read 'l'
    
            if not line then
                return pairs
            end
    
            local key, value = line:match '(.-):%s*(.*)'
    
            if key and value then
                pairs[key] = value
            end
        end
    end,

    codeGenerator = function()
        return {
            header = {},
            code = {},
            level = 0,
            scratch = {},
    
            indentation = function(self)
                local code = self.code
                code[#code + 1] = string.rep('    ', self.level)
            end,
    
            write = function(self, ...)
                local args = {...}
                local code = self.code
    
                for i = 1, #args do
                    code[#code + 1] = tostring(args[i])
                end
            end,
    
            eol = function(self)
                local code = self.code
                code[#code + 1] = '\n'
            end,
    
            println = function(self, ...)
                self:indentation()
                self:write(...)
                self:eol()
            end,
    
            indent = function(self)
                self.level = self.level + 1
            end,
    
            unindent = function(self)
                self.level = self.level - 1
            end,
    
            finish = function(self)
                local header = table.concat(self.header, '')
                local code = table.concat(self.code, '')
                return header .. code
            end,

            swap = function(self)
                local header, code = self.code, self.header
                self.header = header
                self.code = code
            end
        }
    end
}
