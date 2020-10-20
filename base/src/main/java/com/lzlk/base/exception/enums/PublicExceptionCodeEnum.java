package com.lzlk.base.exception.enums;

/**
 * <pre>
 *  
 *
 * 【标题】: 公用异常代码定义-枚举
 * 【描述】: 
 *    注：
 *     1: 公用异常代码，如果觉得觉得该异常比较通用，定义代码时，请放到该枚举
 *     2: 除了SUCCESS，跟UNKNOWN外，所以异常代码为6位
 *     3：6位异常代码从999999降序定义
 *     4: 其中message的信息，是开发者见，不作为用户所看到的提示信息，用户所看到提示信息，将由前段处理
 * 【版权】: 湖南
 * 【时间】: 2017年6月21日 下午2:59:05
 * </pre>
 */
public enum PublicExceptionCodeEnum {

    /**
     * 通用-成功代码
     */
    SUCCESS("00000000", "成功"),

    /**
     * 通用-需要付费
     */
    NEED_ORDER("00000001", "需要付费"),
    
    /**
     * 通用-未知异常代码
     */
    EX_UNKNOWN("99999999", "未知异常"),

    /**
     * 通用查询失败
     */
    EX_SELECT_ERROR("900001", "查询失败！"),

    /**
     * 通用创建失败
     */
    EX_CREATE_ERROR("900002", "创建失败！"),

    /**
     * 通用更新失败
     */
    EX_UPDATE_ERROR("900003", "更新失败！"),

    /**
     * 通用删除失败
     */
    EX_DELETE_ERROR("900004", "删除失败！"),

    /**
     * 请求
     */
    EX_METHOD_ERROR("900005", "请求method类型不正确"),

    /**
     * 请求超时
     */
    EX_TIME_OUT("900006", "请求超时"),
    /**
     * db 异常
     */
    EX_DB_ERROR("900007", "DB异常"),

    /**
     * 登录用户名/密码不正确
     */
    EX_LOGIN_USER_PWD_ERROR("900010", "登录用户名/密码不正确"),
    /**
     * 两次密码不一致
     */
    EX_UPDATE_USER_PWD_ERROR("900020", "两次密码不一致"),
    /**
     * session超时
     */
    EX_SESSION_TIME_OUT("900030", "session超时"),

    EX_PARAM_NOT_READABLE_ERROR("900046","无法解析参数"),
    /**
     * 参数类型错误
     */
    EX_PARAM_TYPE_ERROR("900047","参数类型错误"),
    /**
     * 参数错误
     */
    Ex_PARAM_ERROR("900048", "参数错误"),

    /**
     * 类型不匹配
     */
     Type_MissMatch_Error("900049","类型不匹配"),

    /**
     * 用户名格式错误
     */
    Ex_LOGIN_NAME_ERROR("900050", "用户名格式错误"),

    Ex_FILE_NAME_ERROR("900052", "文件格式错误"),

    Ex_FILE_SIZE_MAX_ERROR("900054", "文件超过最大限制"),

    /**
     * 邮箱格式错误
     */
    Ex_EMAIL_ERROR("900060", "邮箱格式错误"),

    /***
     * 电话式错误
     */
    Ex_MOBILE_ERROR("900070", "手机号码格式错误"),

    /**
     * 请重新登录
     */
    EX_LOGIN_ERROR("900090", "请重新登录"),

    /**
     * 用户未登录
     */
    EX_USER_NOT_LOGIN_ERROR("900171", "用户未登录，请重新登录。"),

    /**
     * 修改密码失败
     */
    EX_UPDATE_PASSWORD_ERROR("900100", "修改密码失败"),

    /**
     * 旧密码错误
     */
    EX_OLD_PASSWORD_ERROR("900110", "旧密码错误"),

    /**
     * 密码格式错误
     */
    EX_PASSWORD_FORMAT_ERROR("900120", "密码格式错误"),

    /**
     * 用户名存在
     */
    EX_LOGINNAME_EXIST("900130", "用户已存在"),

    /**
     * 尝试次数过多
     */
    EX_EXCESSIVE_ATTEMPTS("900140", "尝试次数过多"),


    /**
     * 短信没有失效
     */
    EX_MOB_EXCESSIVE_ATTEMPTS("900141", "短息已经发送，请注意查收"),

    /**
     * 用户被禁用
     */
    EX_USER_LOCKED("900150", "用户已被禁用"),

    /**
     * 用户不存在
     */
    EX_USER_NOT_EXIST("900160", "用户不存在"),

    /**
     * 用户登录失败
     */
    EX_USER_LOGIN_ERROR("900170", "用户登录失败"),
    /**
     * 参数不允许为空
     */
    EX_PARAM_NOT_NULL("900180", "参数非空"),
    /**
     * 余额不足
     */
    EX_WEALTH_LESS("900190","余额不足"),
    /**
     * 权限不足
     */
    EX_JURISDICTION_LESS("900191","权限不足"),
    /**
     * 操作失败
     */
    EX_OPERATION_FAIL("900192","操作失败"),
    /**
     * 非法请求
     */
    EX_ILLEGAL_REQUEST("900193","非法请求"),
    
    /**
     * 获取用户信息失败
     */
    EX_GET_USER_INFO_ERROR("900194","获取用户信息失败！"),


    EX_NOT_MAPPING_ERROR("900196","找不到路径"),


    /**
     * 验证码错误
     */
    EX_VERIFY_CODE_ERROR("900195","验证码错误"),


    EX_PAY_NOT_FINISHED("901001","正在处理支付订单，请稍后"),

    EX_SYS_BUSY_ERROR("901002","服务器繁忙"),

   ;

    private String code;

    private String msg;

    PublicExceptionCodeEnum(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public static String getMsg(String code) {
        for (PublicExceptionCodeEnum exEnum : PublicExceptionCodeEnum.values()) {
            if (exEnum.getCode().equals(code)) {
                return exEnum.getMsg();
            }
        }
        return "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
