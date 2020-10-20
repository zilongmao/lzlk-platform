package com.lzlk.nosql.redis.service;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/13 16:54
 * @Created by 湖南达联
 */
public interface RedisService {
    

    /**
     * 获取key的类型
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @return
     */
    DataType getkeyType(String key);

    /**
     * 设置key的值成功则true，否则false
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @param value
     * @return
     */

    <T> Boolean setIfAbsent(String key, T value);

    /**
     * 设置key的值成功则true，否则false
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @param value
     * @param unixTime
     *            过期时间,单位毫秒
     * @return
     */
    <T> Boolean setIfAbsent(String key, T value, long unixTime);

    /**
     * 插入数据
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @param value
     */
    <T> void set(String key, T value);


    /**
     * 插入数据（指定过期时间，单位秒）
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @param value
     * @param timeout
     *            过期时间
     */
    <T> void set(String key, T value, Long timeout);

    /**
     * 获取指定 key值
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @return
     */
    <T> T get(String key);


    /**
     * 删除指定key
     *
     * @param key
     * @return
     */
    boolean del(String key);


    /**
     * 删除lock
     * @param key
     * @return
     */
    boolean delNX(String key);

    /**
     * 判断KEY是否存在
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @return
     */
    boolean exists(final String key);

    /**
     * 设置指定key的失效时间<br/>
     * 失效
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @param seconds
     *            单位秒
     * @return
     */
    boolean expireInSeconds(String key, int seconds);

    /**
     * 设置指定key的失效时间<br/>
     * 失效
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @param milliSeconds
     *            毫秒
     * @return
     */
    boolean expireInMilliSeconds(String key, long milliSeconds);

    /**
     * 设置指定key在某时间失效<br/>
     * 失效后K/v不消失
     *
     * @Description
     * @author 邻座旅客
     * @param key
     * @return
     */
    boolean expireAt(String key, long unixTime);

    /**
     * 获取指定key的存活时间
     *
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 通过正则匹配keys
     *
     * @Description
     * @author 邻座旅客
     * @param pattern
     * @return
     */
    Set<String> keys(String pattern);




    /**
     * 对某个KEY的值进行加法运算<br>
     * 1:只作用于值为数值类型，否则会出现异常
     *
     * @param key
     * @return
     */
    Double opsForValueIncrement(String key, double value);



    /**
     * 原子递增1并设置失效时间
     *  可能并非是个原子性的
     *  由于redis文档上并未找到incr操作并设置失效时间命令
     * @param key 存储的key
     * @param sec 时间单位s
     * @return
     */
    Long opsForValInc(String key, long sec);

    /**
     *  原子递增并设置失效时间
     * @param key
     * @param value
     * @param sec
     * @return
     */
    Long opsForValInc(String key, long value, long sec);


    /**
     * bit实现
     * 可能并不是原子操作
     * 偏移量有大小之分
     * redis setBit命令中没有找到设置失效时间
     * @param key
     * @param off 偏移量代表位置
     * @param sec
     */
    boolean opsForValBit(String key, long off, long sec);

    /**
     * 获取一个set集合的大小
     * @param key
     * @return
     */
    Long getSetSize(String key);


    /**
     * 查询两集合不同的元素
     * @param key
     * @param othKey
     * @return
     */
     Set opsForSetDiff(String key, String othKey);

    /**
     * 如果key不存在，则将key的值设为value; 否则不做操作
     * @param key
     * @param value
     * @param timeOutSeconds
     * @return
     */
    boolean setNX(final String key, final String value, long timeOutSeconds);

    /**
     * set成功后将值返回
     * @param key
     * @param value
     * @param timeOutSeconds
     * @return
     */
    String getSet(final String key, final String value, long timeOutSeconds);


    /**
     * 获取zset的操作类
     * @return
     */
    ZSetOperations opsForZSet();

    /**
     * zSet add 命令
     * @param key
     * @param value
     * @param d
     * @param <V>
     * @return
     */
   <V> boolean opsForZSetAdd(String key, V value, double d);

    /**
     * 执行
     * @param key
     * @param sec
     * @param sets
     */
    Long opsForSetAdd(String key, Long sec, Set<?>... sets);

}
