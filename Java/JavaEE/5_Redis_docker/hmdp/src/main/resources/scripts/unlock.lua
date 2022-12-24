-- 比较传入的id和缓存中的id是否一致
if(redis.call('get', KEYS[1]) == ARGV[1]) then
    -- 如果一致则删除互斥锁
    return redis.call('del', KEYS[1])
end
return 0