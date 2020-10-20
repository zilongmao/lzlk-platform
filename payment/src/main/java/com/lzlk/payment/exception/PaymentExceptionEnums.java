package com.lzlk.payment.exception;


import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/3/26 21 06
 * @Author: 邻座旅客
 */
public enum PaymentExceptionEnums {

    PAYMENT_EXCEPTION_ENUMS("12000000", "这是一个例子，请参考"),


    ;

    private String code;

    private String msg;

    PaymentExceptionEnums(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getExceptionMsg(String code) {
        PaymentExceptionEnums exceptionEnums = null;
        for (PaymentExceptionEnums enums : PaymentExceptionEnums.values()) {
            if (enums.code.equals(code)) {
                return enums.getMsg();
            }
        }

        if (exceptionEnums == null) {
            return PublicExceptionCodeEnum.getMsg(code);
        }
        return "";
    }
}
