package com.lzlk.payment.exception;


import com.lzlk.base.exception.SysException;
import com.lzlk.base.exception.enums.ExceptionLevelEnums;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;

/**
 * TODO: 支付系统异常
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/8 14 50
 * @Author: 邻座旅客
 */
public class PaymentException extends SysException {


    public PaymentException(String code) {
        super(ExceptionSysMarkEnum.PAY, code, PaymentExceptionEnums.getExceptionMsg(code));
    }

    public PaymentException(String code, Throwable e) {
        super(ExceptionSysMarkEnum.PAY, code, PaymentExceptionEnums.getExceptionMsg(code), e);
    }

    public PaymentException(String code, ExceptionLevelEnums errorLevel, Throwable e) {
        super(ExceptionSysMarkEnum.PAY, code, PaymentExceptionEnums.getExceptionMsg(code), errorLevel, e);
    }
}
