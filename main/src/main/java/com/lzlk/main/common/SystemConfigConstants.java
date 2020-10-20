package com.lzlk.main.common;

/**
 * TODO: 系统变量常量集
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/30 18 29
 * @Author: 邻座旅客
 */
public interface SystemConfigConstants {

    /** 碎片兑金币的汇率 */
    String SHARD_CONVERT_GOLD_EXCHANGE_RATE_KEY = "shard_convert_gold_exchange_rate_key";

    /** 人民币兑换点劵汇率 */
    String RMB_EXCHANGE_COUPON_RATE_KEY = "rmb_exchange_coupon_rate_key";

    /** 游戏要求 */
    String GAME_FREQUENCY_REQUIREMENT_KEY = "game_frequency_requirement_key_";

    /** 游戏价格 */
    String GAME_RMB_PRICE_KEY = "game_rmb_price_key_";

    /** 金手指要求 */
    String GAME_GOLD_FINGER_REQUIREMENT_KEY = "game_gold_finger_requirement_key_";

    /** 金手指价格 */
    String GAME_GOLD_FINGER_PRICE_KEY = "game_gold_finger_price_key_";

    /** 游戏要求 - 看视频 */
    int GAME_REQUIREMENT_VIDEO = 1;

    /** 游戏要求 - 分享 */
    int GAME_REQUIREMENT_SHARE = 2;

    /** 游戏要求 - 支付 */
    int GAME_REQUIREMENT_PAY = 3;

    /** 支付通知分布式锁的时间 */
    Long PAY_SYS_NOTICE_LOCK_TIME = 300L;

    /** 金币1数量 */
    Long GOLD1_COUNT = 80L;

    /** 金币2数量 */
    Long GOLD2_COUNT = 30L;

    /** 金币3数量 */
    Long GOLD3_COUNT = 10L;

    /** 点券数量 */
    Long COUPON_COUNT = 5L;



}
