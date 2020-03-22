local loader = require 'luame.loader'
local utils = require 'luame.utils'
local luafy = require 'luame.luafy'
local classes = require 'luame.classes'
local log = require 'luame.log'

local format = string.format

return function(jar)
    local cache = {}
    local strings = {}

    return {
        load = function(self, className)
            log.info('Loading class ', className)
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
            
            return loader(buffer)
        end,

        parseManifest = function(self)
            local buffer = jar:read 'META-INF/MANIFEST.MF'
            return utils.parseManifest(buffer)
        end,

        luafy = function(self, class)
            local cpool = class.constantPool
            local className = cpool[cpool[class.thisClass].nameIndex].bytes

            log.info('Translating ', className, ' to Lua source code')
            local source = luafy(self, class)

            log.info('Parsing Lua source code for ', className)
            local defFunc, err = load(source, className .. '.lua', 't')

            if not defFunc then
                log.error('Error in Lua source code for ', className, ': ', err)
                error(err)
            end

            log.info('Running defining Lua function for ', className)
            local creator = defFunc()

            log.info('Running creator for ', className)
            return creator(self)
        end,

        define = function(self, className)
            log.info('Defining class ', className)
            local class = cache[className]

            if class then
                log.info('Class ', className, ' found in cache')
                return class
            end

            class = self:load(className)
            class = self:luafy(class)

            log.info('Adding class ', className, ' to the cache')
            cache[className] = class
            return class
        end,

        string = function(self, str)
            local instance = strings[str]

            if instance then
                return instance
            end

            local stringClass = self:define('java/lang/String')
            instance = self:new(stringClass, '<init>(I)V', str)
            strings[str] = instance
            return instance
        end,

        new = function(self, class, constructor, ...)
            local instance = {}
            class['<new>()V'](instance)
            class[constructor](instance, ...)
            return instance
        end
    }
end
