package com.lzlk.base.exception;

import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.exception.enums.ExceptionLevelEnums;
import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * TODO 通用异常父类
 *
 * @author 邻座旅客
 * @Date 2020年9月20日 00:23:35
 * @Created by 湖南达联
 */
@Setter
@Getter
public class BaseException extends RuntimeException{

    /** 异常代码 */
    private String errorCode;

    /** 异常信息 */
    private String msg;

    /** 异常系统代码 */
    private String errorSysCode;

    /** 异常等级, 默认为info */
    private String errorLevel = ExceptionLevelEnums.INFO.getDescription();

    /** 异常数据 */
    private Object errorData;

    /** 异常参数 */
    private Object[] args;

    /** 异常 */
    private Throwable sourceT;

    public BaseException(String code, String msg,ExceptionSysMarkEnum sysMarkEnum) {
        super(getMessage(sysMarkEnum, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorSysCode = sysMarkEnum.getMark();
    }

    public BaseException(String code, String msg,ExceptionSysMarkEnum sysMarkEnum,Throwable t) {
        super(getMessage(sysMarkEnum, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorSysCode = sysMarkEnum.getMark();
        this.sourceT = t;
    }

    public BaseException(String code, String msg,ExceptionLevelEnums levelEnums, ExceptionSysMarkEnum sysMarkEnums) {
        super(getMessage(sysMarkEnums, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorLevel = levelEnums.getDescription();
    }

    public BaseException(String code, String msg,ExceptionLevelEnums levelEnums, ExceptionSysMarkEnum sysMarkEnums,Throwable t) {
        super(getMessage(sysMarkEnums, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorLevel = levelEnums.getDescription();
        this.sourceT = t;
    }

    public BaseException(String code, String msg,ExceptionLevelEnums levelEnums, ExceptionSysMarkEnum sysMarkEnums,
                              Object errorData) {
        super(getMessage(sysMarkEnums, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorLevel = levelEnums.getDescription();
        this.errorData = errorData;
    }

    public BaseException(String code, String msg,ExceptionLevelEnums levelEnums, ExceptionSysMarkEnum sysMarkEnums,
                         Object errorData,Throwable t) {
        super(getMessage(sysMarkEnums, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorLevel = levelEnums.getDescription();
        this.errorData = errorData;
        this.sourceT  = t;
    }

    public BaseException(String code, String msg,ExceptionLevelEnums levelEnums, ExceptionSysMarkEnum sysMarkEnums,
                         Object errorData,Object[] args) {
        super(getMessage(sysMarkEnums, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorLevel = levelEnums.getDescription();
        this.errorData = errorData;
        this.args = args;
    }

    public BaseException(String code, String msg,ExceptionLevelEnums levelEnums, ExceptionSysMarkEnum sysMarkEnums,
                         Object errorData,Object[] args,Throwable t) {
        super(getMessage(sysMarkEnums, code));
        this.errorCode = code;
        this.msg = msg;
        this.errorLevel = levelEnums.getDescription();
        this.errorData = errorData;
        this.args = args;
        this.sourceT = t;
    }

    /**
     * 打印异常数据字符串
     * @return
     */
    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("[异常系统 = ").append(errorSysCode).append("], [异常代码 = ").append(errorCode)
                .append("] , [异常信息 = ").append(msg).append("] , [异常等级 = ").append(errorLevel)
                .append("]");

        if(errorData != null){
            sb.append(" , [异常数据 = ").append(errorData.toString()).append("]");
        }
        if(args != null && args.length > 0){
            sb.append(" , [异常参数 = ||");
            for(Object object : args){
                sb.append(JSONObject.toJSONString(object)).append(" || ");
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public static String getMessage(ExceptionSysMarkEnum sysMarkEnum, String code) {
        if (StringUtils.isNotEmpty(code)) {
            StringBuffer str = new StringBuffer();
            if (code.indexOf(sysMarkEnum.getMark()) == -1) {
                str.append(sysMarkEnum.getMark());
            }
            str.append(code);
            return str.toString();
        }
        return null;
    }





}
