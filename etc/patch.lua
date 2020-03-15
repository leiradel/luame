local file = assert(io.open(arg[1], 'rb'))
local class = file:read('*a')
file:close()

local patched =
    class:sub(1, 6) ..
    '\x00\x2f' ..
    class:sub(9, -1)

file = assert(io.open(arg[1], 'wb'))
file:write(patched)
file:close()
