package com.lzlk.base.utils.sign;

import com.lzlk.base.utils.md5.MD5Utils;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.base.utils.md5.MD5Utils;
import com.lzlk.base.utils.string.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/9 01 46
 * @Author: 邻座旅客
 */
public class SginUtils {
    /**
     * 得到加密值
     *
     * @param map
     * @return
     */
    public static String getSign(Map<String, String> map, String appSecret) {
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuffer reqStr = new StringBuffer();
        for (String key : keys) {
            if (key.equals("sign")) {
                continue;
            }
            String v = map.get(key);
            if (v != null && !v.equals("")) {
                reqStr.append(key).append("=").append(v).append("&");
            }
        }
        reqStr.append("key").append("=").append(appSecret);
        //MD5加密
        return MD5Utils.encode(reqStr.toString()).toUpperCase();
    }

    public static boolean isSignatureValid(Map<String, String> map, String secret) {
        String sign = map.get("sign");
        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        return getSign(map, secret).equals(sign);
    }
}
