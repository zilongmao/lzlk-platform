package com.lzlk.base.enums.pay;

/**
 * TODO: 支付订单状态枚举
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 18
 * @Author: 邻座旅客
 */
public enum PayOrderStatusEnums {

    CREATE(0, "订单创建"),

    PAY_SUCCESS(1, "支付成功"),

    FINISHED(2, "处理完成"),
    ;

    private Integer status;

    private String describe;

    PayOrderStatusEnums(Integer status, String describe) {
        this.status = status;
        this.describe = describe;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescribe() {
        return describe;
    }

    public static boolean checkVal(String describe) {
        if (describe != null) {
            for (PayOrderStatusEnums enums : PayOrderStatusEnums.values()) {
                if (enums.getDescribe().equals(describe)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static PayOrderStatusEnums eval(Integer status) {
        for (PayOrderStatusEnums enums : PayOrderStatusEnums.values()) {
            if (enums.getStatus().equals(status)) {
                return enums;
            }
        }
        return null;
    }
}
