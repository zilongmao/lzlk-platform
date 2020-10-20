package com.lzlk.admin.exception;


import com.lzlk.base.exception.SysException;
import com.lzlk.base.exception.enums.ExceptionLevelEnums;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;

public class AdminException extends SysException {


    public AdminException(String code) {
        super(ExceptionSysMarkEnum.ADMIN,code, AdminExceptionEnums.getExceptionMsg(code));
    }

    public AdminException(String code, Throwable e) {
        super(ExceptionSysMarkEnum.ADMIN,code, AdminExceptionEnums.getExceptionMsg(code), e);
    }

    public AdminException(String code, ExceptionLevelEnums errorLevel, Throwable e) {
        super(ExceptionSysMarkEnum.ADMIN,code, AdminExceptionEnums.getExceptionMsg(code),errorLevel, e);
    }
}
