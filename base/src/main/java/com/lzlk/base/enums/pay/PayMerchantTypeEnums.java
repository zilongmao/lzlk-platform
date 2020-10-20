package com.lzlk.base.enums.pay;

/**
 * TODO: 商户类型枚举
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 18
 * @Author: 邻座旅客
 */
public enum PayMerchantTypeEnums {

    WE_CHAT_PAY("wechat"),

    ALI_PAY("alipay"),
    ;

    private String describe;

    PayMerchantTypeEnums(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public static boolean checkVal(String describe) {
        if (describe != null) {
            for (PayMerchantTypeEnums enums : PayMerchantTypeEnums.values()) {
                if (enums.getDescribe().equals(describe)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static PayMerchantTypeEnums eval(String describe) {
        for (PayMerchantTypeEnums enums : PayMerchantTypeEnums.values()) {
            if (enums.getDescribe().equals(describe)) {
                return enums;
            }
        }
        return null;
    }
}
