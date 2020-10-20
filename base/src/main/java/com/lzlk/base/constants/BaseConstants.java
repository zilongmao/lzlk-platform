package com.lzlk.base.constants;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 07
 * @Author: 邻座旅客
 */
public interface BaseConstants {

    /** 默认分页条数 */
    Integer DEFAULT_PAGE_LIMIT = 20;

    /** 空字符串 */
    String EMPTY_STRING = "";

    /** 订单详情 */
    String WE_CHAT_PAY_DETAIL = "";

    String WE_CHAT_JS_CODE_URL = "https://api.weixin.qq.com/sns/jscode2session?";

    String GRANT_TYPE = "authorization_code";

    String SUCCESS = "success";

    /**
     * 有效标志-有效
     */
    Integer VALID_SIGN_Y = 1;

    /**
     * 有效标志-无效
     */
    Integer VALID_SIGN_N = 0;

    /**
     * 同JWT_TOKEN_COOKIE_KEY 解决下划线问题
     */
    String TOKEN_KEY = "token";

    /**
     * 同HTTP_HEADER_KEY 解决下划线问题
     */
    String HEADER_KEY = "http-header-key";

    String JWT_TOKEN_KEY = "hndl_jwt_token_key";// jwt 加密密钥

    Long JWT_TOKEN_VALID_TIME = 2592000000L; //jwt token 有效时间

    String REQUEST_LOGIN_USER_ID_KET = "request_login_user_id_key";//Request里用户信息的key

    String PAY_NOTICE_LOCK_PACKAGE_KEY = "pay_notice";//支付锁的PACKAGE NAME

    /** 用户默认头像 - 大 */
    String USER_DEFAULT_HEAD_IMAGE_MAX = "https://idol-screen.oss-cn-hangzhou.aliyuncs.com/user/head_image/default/user_default_head_image_max.jpg";

    /** 用户默认头像 - 小 */
    String USER_DEFAULT_HEAD_IMAGE_MIN = "https://idol-screen.oss-cn-hangzhou.aliyuncs.com/user/head_image/default/user_default_head_image_min.jpg";

}
