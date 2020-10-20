package com.lzlk.base.exception.enums;

/**
 * TODO: 异常-系统标识-枚举
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/8 14 50
 * @Author: 邻座旅客
 */
public enum ExceptionSysMarkEnum {

    /** 主站系统 */
    MAIN("MAIN"),

    /** 支付系统 */
    PAY("PAY"),

    /** 后台管理中心 */
    ADMIN("ADMIN"),

    /** 定时任务 */
    JOB("JOB"),

    ;

    
    private String mark;

    ExceptionSysMarkEnum(String mark) {
        this.mark = mark;

    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
