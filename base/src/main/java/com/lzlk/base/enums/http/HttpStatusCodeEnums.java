package com.lzlk.base.enums.http;

/**
 * TODO: HTTP状态码枚举
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/23 15 40
 * @Author: 邻座旅客
 */
public enum HttpStatusCodeEnums {

    SUCCESS(200, "成功"),;

    private Integer code;
    private String remake;

    HttpStatusCodeEnums(Integer code, String remake) {
        this.code = code;
        this.remake = remake;
    }

    public Integer getCode() {
        return code;
    }

    public String getRemake() {
        return remake;
    }
}
