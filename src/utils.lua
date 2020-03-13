return {
    params = function(descriptor)
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
            local slots = (k == 74 or k == 68) and 2 or 1
            return var + 1, desc, slots
        end
    
        return iterator, {descriptor, 1}, 0
    end,

    parseManifest = function(jar)
        local manifest = jar:read 'META-INF/MANIFEST.MF'
        local pairs = {}
    
        while true do
            local line = manifest:read 'l'
    
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
            code = {},
            level = 0,
    
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
                local code = table.concat(self.code, '')
                self.code = nil
                return code
            end
        }
    end
}
