package com.lzlk.job.exception;

import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;

import java.util.Arrays;

/**
 * TODO: 异常码8位，15开头
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/16 15 51
 * @Author: 邻座旅客
 */
public enum JobExceptionEnums {

    JOB_EXCEPTION_ENUMS("15000000","这是一个例子，请参考"),

    ;


    private String code;

    private String msg;

    JobExceptionEnums(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public static String getExceptionMsg(String code){
        JobExceptionEnums exceptionEnums = Arrays.stream(JobExceptionEnums.values())
                .filter(wechatSysExceptionEnums -> wechatSysExceptionEnums.code.equals(code))
                  .findFirst().orElseGet(null);
        if(exceptionEnums == null){
           return PublicExceptionCodeEnum.getMsg(code);
        }
        return "";
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
