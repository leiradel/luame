return function(jar)
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
end
