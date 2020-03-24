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

        luafy = function(self, class, className)
            log.info('Translating ', className, ' to Lua source code')
            local main, err = luafy(self, class)

            if not main then
                log.error('Error in Lua source code for ', className, ': ', err)
                error(err)
            end

            return main
        end,

        define = function(self, className)
            log.info('Defining class ', className)
            local class = cache[className]

            if class then
                log.info('Class ', className, ' found in cache')
                return class
            end

            class = self:load(className)
            local main = self:luafy(class, className)

            log.info('Defining class ', className)
            local creator = main()
            local definition = creator(self)

            log.info('Adding class ', className, ' to the cache')
            cache[className] = definition

            log.info('Running post initialization for ', className)
            definition['<post>()V']()

            return definition
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
        end,

        abstract = function(self, methodName)
            return function()
                error(string.format('call to abstract method %s', methodName))
            end
        end,

        native = function(self, methodName)
            return function()
                error(string.format('call to native method %s', methodName))
            end
        end
    }
end
