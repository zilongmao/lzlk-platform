package com.lzlk.base.utils.des;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class DES2 {

    private static final String TEST_KEY = "";

    private static final String FORMAL_KEY = "";

    public DES2() {
    }

    public static DES2 getInstance(String key) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return getInstance(key.getBytes("UTF-8"));

    }

    public static DES2 getInstance(byte key[]) throws NoSuchPaddingException, NoSuchAlgorithmException {
        DES2 des = new DES2();
        if (des.key == null) {
            SecretKeySpec spec = new SecretKeySpec(key, "DES");
            des.key = spec;
        }
        des.cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        return des;
    }

    public byte[] encrypt(byte b[]) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IllegalStateException {
        byte byteFina[] = null;
        cipher.init(1, key);
        byteFina = cipher.doFinal(b);
        return byteFina;
    }

    public byte[] decrypt(byte b[]) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IllegalStateException {
        byte byteFina[] = null;
        cipher.init(2, key);
        byteFina = cipher.doFinal(b);
        return byteFina;
    }

    private Key key;
    private Cipher cipher;

    /**
     * @interpret 进行base64加密操作
     * @param text
     * @param keyString
     * @return String
     */
    public static String encrypt(String text, String keyString) {
        String body = null;

        try {
            DES2 des = DES2.getInstance(keyString);

            byte[] b = des.encrypt(text.getBytes("UTF8"));
            body = new String(Base64.encodeBase64(b));
        }
        catch (Exception ex) {
             ex.printStackTrace();
        }
        return body;
    }

    /**
     * @interpret 进行base64进行解密
     * @param text
     * @param keyString
     * @return String
     */
    public static String decrypt(String text, String keyString) {
        String body = null;
        try {
            DES2 des = DES2.getInstance(keyString);
            Base64 base64 = new Base64();
            body = new String(des.decrypt(base64.decode(text)), "UTF8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     *
     * @param content 内容
     * @param operation 加密或解密
     * @param key 使用到的密钥:固定长度
     * @return
     */
    public static String authcode(String content, String operation, String key) {

        String encontent = null;
        if (operation != null && operation.equals("DECODE")) {
            encontent = encrypt(content, key);
        }
        else if (operation != null && operation.equals("ENCODE")) {
            encontent = decrypt(content, key);
        }
        return encontent;
    }

    public static void main(String[] args) throws EncoderException {
        String encodeString = DES2.encrypt("{\"mobilePhone\":\"13612345678\"}", "ldytest1");
        System.out.println("加密后密文：" + encodeString);
        System.out.println("解密出的明文：" + DES2.authcode(encodeString, "ENCODE", "ldytest1"));// 解密
    }
}
