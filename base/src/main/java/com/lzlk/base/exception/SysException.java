package com.lzlk.base.exception;


import com.lzlk.base.exception.enums.ExceptionLevelEnums;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;


/**
 * TODO: 系统异常
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/17 10 43
 * @Author: 邻座旅客
 */
public abstract class SysException extends BaseException {

    public SysException(ExceptionSysMarkEnum exceptionSysMarkEnum, String code, String msg) {
        super(code, msg, exceptionSysMarkEnum);
    }

    public SysException(ExceptionSysMarkEnum exceptionSysMarkEnum, String code, String mag, Throwable e) {
        super(code, mag, exceptionSysMarkEnum, e);
    }

    public SysException(ExceptionSysMarkEnum exceptionSysMarkEnum, String code, String msg, ExceptionLevelEnums errorLevel, Throwable e) {
        super(code, msg, errorLevel, exceptionSysMarkEnum, e);
    }

}
