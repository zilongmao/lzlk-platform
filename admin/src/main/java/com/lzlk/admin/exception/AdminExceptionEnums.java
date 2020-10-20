package com.lzlk.admin.exception;


import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;

/**
 * @Classname 邻座旅客
 * @Description 业务异常类，异常码8位，16开头
 * @Date 2019/3/20 9:32
 * @Created by 湖南达联
 */
public enum AdminExceptionEnums {

    ADMIN_EXCEPTION_ENUMS("17000000", "这是一个例子，请参考"),

    ADMIN_LOGIN_NAME_EXCEPTION("17010101", "用户名以及存在"),

    ADMIN_USER_NOT_FIND_EXCEPTION("17010102", "用户不存在"),

    ADMIN_USER_NOT_DEL_ME_EXCEPTION("17010103", "不能删除自己"),

    ADMIN_USER_NOT_RE_ME_EXCEPTION("17010104", "不能重置自己的密码"),

    ADMIN_LOGIN_EX("17010105", "请不要重复登陆"),

    ADMIN_ROLE_NOT_FIND_EXCEPTION("17010201", "角色不存在"),

    ADMIN_USER_ROLE_IS_TRUE_EXCEPTION("17010202", "用户角色已经绑定"),

    ADMIN_USER_ROLE_IS_NOT_EXCEPTION("17010203", "用户角色没有绑定"),

    ADMIN_PATH_EXCEPTION("17010301", "错误的路径"),

    ADMIN_ROLE_IS_NOT_EXCEPTION("17010302", "角色名不能为重复"),

    /*  =====================上传图片==========================*/
    ADMIN_PIC_UPLOAD_FAIL_EXCEPTION("17010401", "图片上传失败"),

    ;


    private String code;

    private String msg;

    AdminExceptionEnums(String code, String message) {
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
        AdminExceptionEnums exceptionEnums = null;
        for (AdminExceptionEnums enums : AdminExceptionEnums.values()) {
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
