package com.lzlk.ali.oss.upload;

import com.lzlk.ali.oss.enums.FileTypeEnums;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname 邻座旅客
 * @Description 阿里云文件上传
 * @Date 2019/3/15 11:47
 * @Created by 湖南达联
 */
public interface AliyunOSSUploadService {

    /**
     * 上传至临时文件
     * @param objectName 文件的名称 包括全路径
     * @param in 输入流
     * @return  返回临时文件地址
     */
    String putObject(String objectName, InputStream in) throws IOException;


    /**
     * 上传资源
     * @param objectName 文件的名称(建议使用吗md5对文件进行命名)
     * @param in 输入流
     * @param FileType   文件类型
     * @return  返回上传至oss资源地址
     */
    String putObject(String objectName, InputStream in ,FileTypeEnums FileType) throws IOException;



    /**
     * 将临时地址图片注册正式路径下
     * @param userImgUrl  图片url
     * @param folderName  路径
     * @return 返回注册成功的图片
     */
    String registerUserImgUrl(String userImgUrl,String folderName);
}
