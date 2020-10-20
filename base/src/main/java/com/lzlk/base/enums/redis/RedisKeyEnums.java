package com.lzlk.base.enums.redis;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020年8月31日 10:45:18
 * @Created by 湖南爱豆
 */
public enum RedisKeyEnums {

    /** 系统配置 */
    SYSTEM_CONFIG_CACHE_KEY("system", "system_config_cache_key", 60L, "系统配置key"),

    ;

    /** 包名 */
    private String packageName;

    /** 前缀 */
    private String prefix;

    /** 过期时间 - 秒 */
    private Long expireTime;

    /** 注释 */
    private String description;

    RedisKeyEnums(String packageName, String prefix, Long expireTime, String description) {
        this.packageName = packageName;
        this.prefix = prefix;
        this.expireTime = expireTime;
        this.description = description;
    }

    public String getPrefix() {
        return prefix;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public String getDescription() {
        return description;
    }

    private static final String SPLITTER = "_";

    /**
     * 获取对应的cacheKey
     *
     * @param args
     * @return
     */
    public String getCacheKey(Object... args) {
        StringBuilder keyBuilder = new StringBuilder(this.packageName).append(":").append(this.prefix);
        if (args != null && args.length > 0) {
            keyBuilder.append(SPLITTER);
            String joinedStr = Arrays.stream(args).map(arg -> arg == null ? "null" : arg.toString())
                    .collect(Collectors.joining(SPLITTER));
            keyBuilder.append(joinedStr);
        }
        return keyBuilder.toString();
    }
}
