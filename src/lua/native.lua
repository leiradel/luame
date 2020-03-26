local array = require 'luame.array'

-- Fake an "UTF-8" string instance
local bytes = array.new(5, 8) -- [B
bytes[0] = 85 -- 'U'
bytes[1] = 84 -- 'T'
bytes[2] = 70 -- 'F'
bytes[3] = 45 -- '-'
bytes[4] = 56 -- '8'

local utf8String = {bytes = bytes}

return function(vm)
    -- java/lang/String.construct([BIILjava/lang/String;)V
    local function _cadaf983(this, bytes, off, len, enc)
        this.bytes = bytes
        enc = tostring(enc.bytes)
    
        if enc ~= 'UTF-8' then
            error('unsupported encoding ' .. enc)
        end
    
        bytes = tostring(bytes)
        local count = 0
    
        for _, cp in utf8.codes(bytes) do
            if (cp >= 0x0000 and cp <= 0xd7ff) or (cp >= 0xe000 and cp <= 0xffff) then
                count = count + 1
            elseif cp >= 0x010000 and cp <= 0x10ffff then
                count = count + 2
            else
                error(string.format('invalid codepoint in UTF-8 sequence: %08x', cp))
            end
        end
    
        local chars = array.new(count, 5) -- [C
        count = 0
    
        for _, cp in utf8.codes(bytes) do
            if (cp >= 0x0000 and cp <= 0xd7ff) or (cp >= 0xe000 and cp <= 0xffff) then
                chars[count] = cp
                count = count + 1
            elseif cp >= 0x010000 and cp <= 0x10ffff then
                cp = cp - 0x10000
                chars[count] = 0xd800 | (cp >> 10)
                chars[count + 1] = 0xdc00 | (cp & 0x03ff)
                count = count + 2
            end
        end
    
        this.chars = chars
        this.length = count
    end
    
    -- java/lang/String.construct([BII)V
    local function _1fcf5373(this, bytes, off, len)
        -- java/lang/String.construct([BIILjava/lang/String;)V
        _cadaf983(this, bytes, off, len, utf8String)
    end
    
    return {
        ["java/lang/String.construct([BIILjava/lang/String;)V"] = _cadaf983,
        ["java/lang/String.construct([BII)V"] = _1fcf5373,
    }
end
