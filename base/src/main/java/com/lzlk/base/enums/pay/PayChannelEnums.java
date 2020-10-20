package com.lzlk.base.enums.pay;

/**
 * TODO: 商品渠道枚举
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 18
 * @Author: 邻座旅客
 */
public enum PayChannelEnums {

    WE_CHAT_APPLET(101, "微信小程序"),

    WE_CHAT_OFFICIAL_ACCOUNT(102, "微信公众号"),

    WE_CHAT_H5(103, "微信H5支付"),


    ;

    private Integer id;

    private String describe;

    PayChannelEnums(Integer status, String describe) {
        this.id = status;
        this.describe = describe;
    }

    public Integer getId() {
        return id;
    }

    public String getDescribe() {
        return describe;
    }

    public static boolean checkVal(String describe) {
        if (describe != null) {
            for (PayChannelEnums enums : PayChannelEnums.values()) {
                if (enums.getDescribe().equals(describe)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static PayChannelEnums eval(Integer id) {
        for (PayChannelEnums enums : PayChannelEnums.values()) {
            if (enums.getId().equals(id)) {
                return enums;
            }
        }
        return null;
    }
}
