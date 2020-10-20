package com.lzlk.base.utils.aes;


import net.iharder.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/4/23 14:53
 * @Created by 湖南达联
 */
public class AESUtil {
    public static final String VIPARA = "1269571569321021";
    public static final String bm = "utf-8";



    /**
     * 字节数组转化为大写16进制字符串
     *
     * @param b
     * @return
     */
    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }

            sb.append(s.toUpperCase());
        }

        return sb.toString();
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param s
     * @return
     */
    private static byte[] str2ByteArray(String s) {
        int byteArrayLength = s.length() / 2;
        byte[] b = new byte[byteArrayLength];
        for (int i = 0; i < byteArrayLength; i++) {
            byte b0 = (byte) Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16)
                    .intValue();
            b[i] = b0;
        }

        return b;
    }


    /**
     * AES 加密
     *
     * @param content
     *            明文
     * @param password
     *            生成秘钥的关键字
     * @return
     */

    public static String encrypt(String content, String password) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes(bm));

            return Base64.encodeBytes(encryptedData);
//			return byte2HexStr(encryptedData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }


    public static String getSecretKey(String secretKey){

        if(StringUtils.isBlank(secretKey)){
            throw new NullPointerException("secretKey is not null! secretKey : "+ secretKey);
        }

        if(secretKey.length() < 16){
            int length = 16 - secretKey.length();
            //补齐16位
            for(int i = 0 ; i <length ; i++ ){
                secretKey +=  "0";
            }
        }

        if(secretKey.length() > 16){
            secretKey = secretKey.substring(0,16);
        }
        return secretKey;
    }

    public static void main(String[] args) {
        String str = "111";
        System.out.println(getSecretKey(str).length());
        String str1 = "1111111111111111";
        System.out.println(getSecretKey(str1).length());

        String str2 = "111111111111111111";
        System.out.println(getSecretKey(str2).length());
    }

    /**
     * AES 解密
     *
     * @param content
     *            密文
     * @param password
     *            生成秘钥的关键字
     * @return
     */

    public static String decrypt(String content, String password) {
        try {
            byte[] byteMi = Base64.decode(content);
//			byte[] byteMi=	str2ByteArray(content);
            IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean initialized = false;

    /**
     * AES对称解密工具类
     *
     * @param content
     *            密文
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchProviderException
     */
    public static String decryptWecat(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        initialize();
        try {
            // java是没有
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return new String(result, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    // 生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
}
