package com.lzlk.admin.common.annotation.constants;

/**
 * @author 邻座旅客
 * @Description 后台常量类
 * @Date 2019/6/18 17:20
 * @Created by 湖南达联
 */
public interface AdminConstants {


    String LOGIN_USER_SESSION_KEY = "login_user_session_key";//shiro session key

    String AUTHORIZATIONINFO = "authorizationinfo";

    String LOGIN_HEAD_KEY = "login_head_key";

    String INIT_CHAT_MESSAGE_REDIS_NAME = "init_chat_message";

    Long INIT_CHAT_MESSAGE_REDIS_TIME_OUT = 3000L;

    Long MEUN_ROOT = 0L;//根菜单

    Integer PAGE_LIMIT = 50;

    Integer ZERO = 0;

    Integer CDK_NUMBER_MAX = 10000;

    Integer BATCH_CDK_NUMBER_MAX = 1000000;

    Long  DEFAULT_SESSION_TIME_OUT = 3600000L;

    String[] SUBSCRIPTION_RATIO ={"shard_convert_gold_exchange_rate_key","rmb_exchange_coupon_rate_key"};

}
