package com.lzlk.nosql.redis.service.impl;

import com.lzlk.nosql.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/13 16:59
 * @Created by 湖南达联
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {


    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;


    @Override
    public DataType getkeyType(String key) {
        return redisTemplate.type(key);
    }

    @Override
    public <T> Boolean setIfAbsent(String key, T value) {
        boolean isSucc = false;
        try {
            BoundValueOperations<?, T> boundValueOperations = redisTemplate.boundValueOps(key);
            isSucc = boundValueOperations.setIfAbsent(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSucc;
    }

    @Override
    public <T> Boolean setIfAbsent(String key, T value, long unixTime) {

        boolean isSucc = false;
        try {
            BoundValueOperations<?, T> boundValueOperations = redisTemplate.boundValueOps(key);
            isSucc = boundValueOperations.setIfAbsent(value);
            if (isSucc) {
                boundValueOperations.expire(unixTime, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSucc;
    }

    @Override
    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <T> void set(String key, T value, Long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public boolean delNX(String key) {
        return (Boolean) redisTemplate.execute((RedisConnection conn) -> conn.del(key.getBytes()) > 0);
    }

    @Override
    public boolean exists(String key) {
        return (Boolean) redisTemplate.execute((RedisConnection conn) -> conn.exists(key.getBytes()));
    }

    @Override
    public boolean expireInSeconds(String key, int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean expireInMilliSeconds(String key, long milliSeconds) {
        return redisTemplate.expire(key, milliSeconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean expireAt(String key, long unixTime) {

        return redisTemplate.expireAt(key, new Date(unixTime * 1000));
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public Double opsForValueIncrement(String key, double value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    /**
     * 原子递增1并设置失效时间
     *
     * @param key
     * @param sec
     * @return
     */
    @Override
    public Long opsForValInc(String key, long sec) {
        byte[] keyBytes = rawKey(key);
        return (Long) redisTemplate.execute(conn -> {
            Long incr = conn.incr(keyBytes);
            if (!conn.expire(keyBytes, sec)) {
                log.error("redis inc val sec error!");
            }
            conn.close();
            return incr;
        }, true);
    }


    /**
     * 原子递增并设置失效时间
     *
     * @param key
     * @param value
     * @param sec
     * @return
     */
    @Override
    public Long opsForValInc(String key, long value, long sec) {
        byte[] keyBytes = rawKey(key);
        return (Long) redisTemplate.execute(conn -> {
            Long incr = conn.incrBy(keyBytes, value);
            if (!conn.expire(keyBytes, sec)) {
                log.error("redis inc val sec error!");
            }
            conn.close();
            return incr;
        }, true);
    }


    /**
     * bit实现可能并不是原子操作
     * redis setBit命令中没有找到设置失效时间
     *
     * @param key
     * @param off 偏移量代表位置
     * @param sec
     */
    @Override
    public boolean opsForValBit(String key, long off, long sec) {
        byte[] keyBytes = rawKey(key);
        return (boolean) redisTemplate.execute((RedisCallback) conn -> {
            if (conn.setBit(keyBytes, off, true)) {
                return conn.expire(keyBytes, sec);
            }
            conn.close();
            return false;
        });
    }

    /**
     * 获取一个set集合的大小
     *
     * @param key
     * @return
     */
    @Override
    public Long getSetSize(String key) {


        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 查询两集合不同的元素
     *
     * @param key
     * @param othKey
     * @return
     */
    @Override
    public Set opsForSetDiff(String key, String othKey) {
        return redisTemplate.opsForSet().difference(key, othKey);
    }


    /**
     * @param key
     * @param value
     * @param timeOutSeconds
     * @return
     */
    @Override
    public boolean setNX(String key, String value, long timeOutSeconds) {
        Object obj = null;
        StringRedisSerializer serializer = new StringRedisSerializer();
        try {
            obj = redisTemplate.execute((RedisConnection connection) -> {
                Boolean success = connection.setNX(key.getBytes(), serializer.serialize(value));
                if (success) {
                    connection.expire(serializer.serialize(key), timeOutSeconds);
                }
                connection.close();
                return success;
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.error("setNX redis error, key : {}", key);
        }
        return obj != null ? (Boolean) obj : false;
    }

    @Override
    public String getSet(String key, String value, long timeOutSeconds) {
        Object obj = null;
        try {
            obj = redisTemplate.execute((RedisConnection connection) -> {
                RedisSerializer serializer = new StringRedisSerializer();
                byte[] ret = connection.getSet(serializer.serialize(key), serializer.serialize(value));
                if (ret != null) {
                    connection.expire(serializer.serialize(key), timeOutSeconds);
                }
                connection.close();
                return serializer.deserialize(ret);
            });
        } catch (Exception e) {
            log.error("setNX redis error, key : {}", key);
        }
        return obj != null ? (String) obj : null;
    }


    @Override
    public ZSetOperations opsForZSet() {

        return redisTemplate.opsForZSet();
    }

    /**
     * zSet add 命令
     *
     * @param key
     * @param value
     * @param d
     * @return
     */
    @Override
    public <V> boolean opsForZSetAdd(String key, V value, double d) {
        return redisTemplate.opsForZSet().add(key, value, d);
    }

    /**
     * @param key
     * @param sec
     * @param sets
     */
    @Override
    public Long opsForSetAdd(String key, Long sec, Set<?>... sets) {
        byte[] keyBytes = redisTemplate.getKeySerializer().serialize(key);
        return (Long) redisTemplate.execute((RedisCallback) conn -> {
            Long add = conn.sAdd(keyBytes, rawValues(sets));
            if (null != add) {
                if (!conn.expire(keyBytes, sec)) {
                    log.error("redis expire error，key:{}", key);
                }
            }
            return add;
        });
    }


    /**
     * @param key
     * @return
     */
    private byte[] rawKey(Object key) {
        return redisTemplate.getKeySerializer().serialize(key);
    }

    /**
     * @param values
     * @return
     */
    private byte[][] rawValues(Object... values) {

        byte[][] rawValues = new byte[values.length][];
        int i = 0;
        for (Object value : values) {
            rawValues[i++] = rawValue(value);
        }
        return rawValues;
    }

    /**
     * @param value
     * @return
     */
    private byte[] rawValue(Object value) {
        return redisTemplate.getValueSerializer().serialize(value);
    }


}
