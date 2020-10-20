package com.lzlk.main.exception;


import com.lzlk.base.exception.SysException;
import com.lzlk.base.exception.enums.ExceptionLevelEnums;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;

/**
 * TODO: 主站系统异常
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/8 14 50
 * @Author: 邻座旅客
 */
public class MainException extends SysException {


    public MainException(String code) {
        super(ExceptionSysMarkEnum.MAIN, code, com.lzlk.main.exception.MainExceptionEnums.getExceptionMsg(code));
    }

    public MainException(String code, Throwable e) {
        super(ExceptionSysMarkEnum.MAIN, code, com.lzlk.main.exception.MainExceptionEnums.getExceptionMsg(code), e);
    }

    public MainException(String code, ExceptionLevelEnums errorLevel, Throwable e) {
        super(ExceptionSysMarkEnum.MAIN, code, com.lzlk.main.exception.MainExceptionEnums.getExceptionMsg(code), errorLevel, e);
    }
}
