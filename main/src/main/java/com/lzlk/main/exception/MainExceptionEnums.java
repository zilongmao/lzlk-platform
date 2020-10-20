package com.lzlk.main.exception;


import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;

/**
 * TODO: 主站系统异常枚举
 *
 * @Created by 湖南爱豆
 * @Date 2020/3/26 21 06
 * @Author: 邻座旅客
 */
public enum MainExceptionEnums {

    MAIN_EXCEPTION_ENUMS("10000000", "这是一个例子，请参考"),

    MAIN_ADD_USER_EXCEPTION("11000001", "创建用户失败"),

    /*****************用户********************/
    MAIN_USER_SIGNED_IN_INVALID("10010001","今天已经签过到"),
    MAIN_UPDATE_USER_NICKNAME_IS_EXIST_EXCEPTION("10010002","昵称已存在，换一个昵称吧"),
    MAIN_UPDATE_USER_NICKNAME_TOO_LONG_EXCEPTION("10010003","昵称过长，修改失败"),
    MAIN_RECEIVE_GIFT_ALREADY_RECEIVED("10030004","已经领取过礼包了~"),
    MAIN_PIC_DATA_EXCEPTION("10010005", "图片URL有误"),
    MAIN_UPDATE_USER_IMG_URL_FIND("10010006", "头像格式有误,暂只支持jpg/png图片"),

    /*************  支付  *************/
    MAIN_AMOUNT_NOT_VALID("10020001", "支付金额无效"),
    MAIN_MCH_NOT_FOUND("10020002", "未找到有效商户"),
    MAIN_GOODS_TYPE_NOT_FOUND("10020003", "商品类型无效"),
    MAIN_GOODS_NOT_FOUND("10020004", "未找到对应商品"),
    EX_WE_CHAT_PRE_ORDER_ERROR("10020005", "订单生成失败"),
    MAIN_USER_NOT_FOUND("10020006", "用户不存在"),

    ;

    private String code;

    private String msg;

    MainExceptionEnums(String code, String message) {
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
        MainExceptionEnums exceptionEnums = null;
        for (MainExceptionEnums enums : MainExceptionEnums.values()) {
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
