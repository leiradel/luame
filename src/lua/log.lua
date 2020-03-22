if _LUAME_DEBUG then
    return {
        info = function(...)
            local args = {...}
            io.stderr:write('[INFO ] ')

            for i = 1, #args do
                io.stderr:write(tostring(args[i]))
            end

            io.stderr:write('\n')
        end,

        warn = function(...)
            local args = {...}
            io.stderr:write('[WARN ] ')

            for i = 1, #args do
                io.stderr:write(tostring(args[i]))
            end

            io.stderr:write('\n')
        end,

        error = function(...)
            local args = {...}
            io.stderr:write('[ERROR] ')

            for i = 1, #args do
                io.stderr:write(tostring(args[i]))
            end

            io.stderr:write('\n')
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
