local loader = require 'luame.loader'
local utils = require 'luame.utils'
local luafy = require 'luame.luafy'
local classes = require 'luame.classes'
local log = require 'luame.log'

local format = string.format

return function(jar)
    local cache = {}

    return {
        load = function(self, className)
            log.info('Loading class ', className)
            local class = cache[className]

            if class then
                log.info('Class ', className, ' found in cache')
                return class
            end

            local path = format('%s.class', className)
            local buffer

            if classes:exists(path) then
                log.info('Class ', className, ' found in classes.zip, loading')
                buffer = classes:read(path)
            elseif jar:exists(path) then
                log.info('Class ', className, ' found in jar file, loading')
                buffer = jar:read(path)
            else
                error(string.format('could not find file "%s"', path))
            end
            
            class = loader(buffer)

            log.info('Adding class ', className, ' to the cache')
            cache[className] = class
            return class
        end,

        parseManifest = function(self)
            local buffer = jar:read 'META-INF/MANIFEST.MF'
            return utils.parseManifest(buffer)
        end,

        luafy = function(self, class)
            return luafy(self, class)
        end
    }
end
