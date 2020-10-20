package com.lzlk.base.lock;



import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.utils.string.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/23 14:17
 * @Created by 湖南达联
 */

public class RedisLockKey {

    private static final String REDIS_PACKAGE= "lock";//redis 的文件夹

    private static final String COLON = ":";

    private static final String SPLITTER = "_";

    private static final String  REDIS_NX_KEY = "redis_nx_key";//redis key

    public static final String  DEFAULT_VALUE = "default_value";//默认锁的值

    public static final Long DEFAULT_TIME = 15000L;


    public static String builderRedisKey(String businessPag, Object... args){

        if(StringUtils.isBlank(businessPag)){
             throw new NullPointerException(" businessPag is not null...");
        }

        StringBuilder builder = new StringBuilder(REDIS_PACKAGE).append(COLON);
        builder.append(businessPag).append(COLON).append(REDIS_NX_KEY).append(SPLITTER);
        if(args !=null && args.length > 0){
           String redisKey = Arrays.stream(args).map(arg -> arg == null ? "null" : arg.toString())
                    .collect(Collectors.joining(SPLITTER));
           builder.append(redisKey);
        }
        return builder.toString().intern();
    }


    public static void main(String[] args) {
    }


}
