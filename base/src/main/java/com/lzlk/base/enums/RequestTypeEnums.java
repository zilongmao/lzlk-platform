package com.lzlk.base.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 邻座旅客
 * @Description 请求来源
 * @Date 2019/4/18 16:48
 * @Created by 湖南达联
 */
public enum RequestTypeEnums {

     IOS("ios"),//ios

     ANDROID("android"),//安卓

     WECHAT_PROGRAM("wechat"),//小程序

     SERVER("server"),

     BAIDU("baidu"),

     H5("h5"),

     QQ("qq"),
    ;

     private String value;

    RequestTypeEnums(String value){
            this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean checkVal(String value) {
        if (value != null) {
            for (RequestTypeEnums c : RequestTypeEnums.values()) {
                if (c.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static Map<String, RequestTypeEnums> getApp() {
        Map<String, RequestTypeEnums> map = new HashMap<>();
        map.put(RequestTypeEnums.IOS.value, RequestTypeEnums.IOS);
        map.put(RequestTypeEnums.ANDROID.value, RequestTypeEnums.ANDROID);
        return map;
    }

    public static boolean isApp(String type) {
        // include：包含
        boolean include = false;
        for (RequestTypeEnums enums : getApp().values()) {
            include = enums.getValue().equals(type);
            if (include) {
                break;
            }
        }
        return include;
    }

}
