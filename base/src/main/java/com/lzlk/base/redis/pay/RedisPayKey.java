package com.lzlk.base.redis.pay;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 18
 * @Author: 邻座旅客
 */
public class RedisPayKey {

    private static final String PAY_NOTICE_REDIS_KEY = "pay_sys:redis_key_notice_";

    public static final Long OUT_TIME = 300L;

    public static String getPayNoticeRedisKey(Integer payChannel, Long localTradeNo) {
        return PAY_NOTICE_REDIS_KEY + payChannel + "_" + localTradeNo;
    }

}
