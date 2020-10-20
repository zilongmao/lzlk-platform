package com.lzlk.base.utils.file;

/**
 * <pre>
 *  
 *
 * 【标题】: 文件工具类
 * 【描述】: 
 * 【版权】: 湖南爱豆
 * 【作者】: 邻座旅客
 * 【时间】: 2017年7月25日 上午10:42:21
 * </pre>
 */
public class FileUtils {
    /**
     * 通过名称获取扩展名
     * 
     * @Description
     * @author 邻座旅客
     * @param filename
     *            文件名称
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取不带扩展名的文件名
     * 
     * @Description
     * @author 邻座旅客
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
