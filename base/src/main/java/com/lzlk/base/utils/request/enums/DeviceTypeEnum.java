package com.lzlk.base.utils.request.enums;

/**
 * <pre>
 *
 *
 * 【标题】:
 * 【描述】:
 * 【版权】: 湖南爱豆
 * 【作者】: 张国栋
 * 【时间】: 2017年9月29日 下午7:02:32
 * </pre>
 */
public enum DeviceTypeEnum {

    PC(10, "PC"),
    ANDROID(11, "ANDROID"),
    IPHONE(12, "IPHONE"),
    IPAD(13, "IPAD"),
    OTHER(99, "OTHER");

    private Integer code;

    private String name;

    DeviceTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
