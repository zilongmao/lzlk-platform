package com.lzlk.base.enums.redis;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 邻座旅客
 * @Description TODO
 * 做数据统计的最佳实现应在于负载上
 * 天的存储时间14天
 * 日的存储七天
 * @Date 2020/1/11 16:54
 * @Created by 湖南达联
 **/
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ViewKey {


    HOUR_PV("pv", "hour_pv_key", 691200000L, "每小时pv统计"),

    DAY_PV("pv", "day_pv_key", 1382400000L, "每天pv统计"),

    HOUR_UV("uv", "hour_uv_key", 691200000L, "每小时uv统计"),

    DAY_UV("uv", "day_uv_key", 1382400000L, "每天uv统计"),

    HOUR_IV("iv", "hour_iv_key", 691200000L, "每小时iv统计"),

    DAY_IV("iv", "day_iv_key", 1382400000L, "每天iv统计"),

    HOUR_DV("dv", "hour_dv_key", 691200000L, "每小时dv统计"),

    DAY_DV("dv", "day_dv_key", 1382400000L, "每天dv统计"),

    HOUR_NEW("new", "hour_new_key", 691200000L, "每小时新增用户"),

    DAY_NEW("new", "day_new_key", 1382400000L, "每日新增用户"),;

    /**
     * 包名
     */
    private String packageName;
    /**
     * 前缀
     */
    private String prefix;
    /**
     * 失效时间
     */
    private Long expireTime;
    /**
     * 描述
     */
    private String description;

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
            //joiner
            String joinedStr = Arrays.stream(args).map(arg -> arg == null ? "null" : arg.toString()).collect(Collectors.joining(SPLITTER));
            keyBuilder.append(joinedStr);
        }
        return keyBuilder.toString();
    }
}
