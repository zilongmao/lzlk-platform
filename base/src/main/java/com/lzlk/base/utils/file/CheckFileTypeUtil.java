package com.lzlk.base.utils.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author : 邻座旅客
 * @title :
 * @description :
 * @copyright :湖南达联
 * @date : 2018/6/19 15:54
 */
public class CheckFileTypeUtil {
    /**
     * // 缓存文件头信息-文件头信息
     */
    public static final HashMap<String, String> M_FILE_TYPES = new HashMap<String, String>();
    static {
        // images
        M_FILE_TYPES.put("FFD8FF", "jpg");
        M_FILE_TYPES.put("89504E47", "png");
        M_FILE_TYPES.put("47494638", "gif");
        M_FILE_TYPES.put("49492A00", "tif");
        M_FILE_TYPES.put("424D", "bmp");
        M_FILE_TYPES.put("00000100", "icon");
    }

    /**
     * @author guoxk
     *
     * 方法描述：根据文件路径获取文件头信息
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public static String getFileType(String filePath,byte[] b) {
        return M_FILE_TYPES.get(getFileHeader(filePath,b));
    }

    /**
     * @author guoxk
     *
     * 方法描述：根据文件路径获取文件头信息
     * @param in 文件流
     * @return 文件头信息
     */
    public static String getFileType(InputStream in,byte[] b) {
        return M_FILE_TYPES.get(getFileHeader(in,b));
    }

    /**
     * @author guoxk
     *
     * 方法描述：根据文件路径获取文件头信息
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public static String getFileHeader(String filePath,byte[] b) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    /**
     * @author guoxk
     *
     * 方法描述：根据文件路径获取文件头信息
     * @param in 文件流
     * @return 文件头信息
     */
    public static String getFileHeader(InputStream in,byte[] b) {
        String value = null;
        try {
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            in.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    /**
     * @author guoxk
     *
     * 方法描述：根据文件路径获取文件头信息
     * @param bytes 文件流前四个
     * @return 文件头信息
     */
    public static String getFileHeader(byte[] bytes) {

        return bytesToHexString(bytes);
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制(基数 16)无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }


}
