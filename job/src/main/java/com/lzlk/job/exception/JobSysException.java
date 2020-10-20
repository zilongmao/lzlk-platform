package com.lzlk.job.exception;


import com.lzlk.base.exception.SysException;
import com.lzlk.base.exception.enums.ExceptionLevelEnums;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;

public class JobSysException extends SysException {


    public JobSysException(String code) {
        super(ExceptionSysMarkEnum.JOB, code, JobExceptionEnums.getExceptionMsg(code));
    }

    public JobSysException(String code, Throwable e) {
        super(ExceptionSysMarkEnum.JOB, code, JobExceptionEnums.getExceptionMsg(code), e);
    }

    public JobSysException(String code, ExceptionLevelEnums errorLevel, Throwable e) {
        super(ExceptionSysMarkEnum.JOB, code, JobExceptionEnums.getExceptionMsg(code), errorLevel, e);
    }
}
