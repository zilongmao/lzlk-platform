package com.lzlk.base.exception.enums;

import java.util.Arrays;

/**
 * <pre>
 *  
 *
 * 【标题】: 
 * 【描述】: 
 * 【版权】: 湖南爱豆
 * 【作者】: 邻座旅客
 * 【时间】: 2018年3月1日 上午11:56:37
 * </pre>
 */
public enum ExceptionLevelEnums {

    TRACE(1, "TRACE"), DEBUG(2, "DEBUG"), INFO(3, "INFO"), WARN(4, "WARN"), ERROR(5, "ERROR"), FATAL(6, "FATAL");

    private Integer code;

    private String description;

    ExceptionLevelEnums(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 是否包含
     * 
     * @param code
     * @return
     */
    public static boolean contains(Integer code) {
        if (code == null) {
            return false;
        }
        return Arrays.stream(ExceptionLevelEnums.values()).anyMatch(input -> input.getCode().equals(code));
    }

}
