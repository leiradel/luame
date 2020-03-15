if _LUAME_DEBUG then
    return {
        info = function(...)
            local args = {...}
            io.write('[INFO ] ')

            for i = 1, #args do
                io.write(tostring(args[i]))
            end

            io.write('\n')
        end,

        warn = function(...)
            local args = {...}
            io.write('[WARN ] ')

            for i = 1, #args do
                io.write(tostring(args[i]))
            end

            io.write('\n')
        end,

        error = function(...)
            local args = {...}
            io.write('[ERROR] ')

            for i = 1, #args do
                io.write(tostring(args[i]))
            end

            io.write('\n')
        end
    }
else
    local function dummy() end

    return {
        info = dummy,
        warn = dummy,
        error = dummy
    }
end
