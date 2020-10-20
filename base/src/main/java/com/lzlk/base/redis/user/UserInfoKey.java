package com.lzlk.base.redis.user;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/4/19 16:06
 * @Created by 湖南达联
 */
public class UserInfoKey {


    private static  final String USER_INFO_CACHE_KEY = "user:user_info_cache_";//手机验证码

    public static final Long OUT_TIME = 1800L;


    public static String getUserInfoCacheKey(Long userUniId){
        return  USER_INFO_CACHE_KEY + userUniId;
    }

    public static void main(String[] args) {
    }
}
