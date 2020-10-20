package com.lzlk.admin.common.util;


import com.lzlk.base.utils.string.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 17:40
 * @Created by 湖南达联
 */
public class ShiroMD5Utils {

    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static String encryptPassword(String loginName, String password) {

        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return null;
        }

        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(loginName),
                hashIterations).toHex();

        return newPassword;
    }

}
