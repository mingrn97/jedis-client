package com.mingrn.common.redis.client;

import com.mingrn.common.redis.client.base.BaseRedisClient;
import com.mingrn.common.redis.config.RedisPoolConfig;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis SET API
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 2019-08-13 20:51
 */
public class RedisSetClient<T extends RedisPoolConfig> extends BaseRedisClient<T> implements RedisSetApi {

    private T poolConfig;

    public RedisSetClient(T poolConfig) {
        super(poolConfig);
        this.poolConfig = poolConfig;
    }

    @Override
    public Long setAdd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sadd(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setRemove(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.srem(key, members);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Boolean setIsMember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sismember(key, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public String setPop(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.spop(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setPop(String key, long count) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.spop(key, count);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setMembers(String key) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.smembers(key);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setMove(String source, String destination, String member) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.smove(source, destination, member);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setInter(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sinter(keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setUnion(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sunion(keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Set<String> setDiff(String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sdiff(keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setInterAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sinterstore(destination, keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setUnionAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sunionstore(destination, keys);
        } finally {
            T.releaseResource(jedis);
        }
    }

    @Override
    public Long setDiffAndStore(String destination, String... keys) {
        Jedis jedis = null;
        try {
            jedis = poolConfig.acquireResource();
            return jedis.sdiffstore(destination, keys);
        } finally {
            T.releaseResource(jedis);
        }
    }
}