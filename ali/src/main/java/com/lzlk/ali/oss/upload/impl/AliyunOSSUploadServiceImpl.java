package com.lzlk.ali.oss.upload.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.lzlk.ali.oss.config.AliyunOssConfig;
import com.lzlk.ali.oss.upload.AliyunOSSUploadService;
import com.lzlk.ali.oss.enums.FileTypeEnums;
import com.lzlk.ali.oss.util.AliyunFileNameUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/15 11:55
 * @Created by 湖南达联
 */
@Service
@Slf4j
public class AliyunOSSUploadServiceImpl implements AliyunOSSUploadService {

    @Resource
    private AliyunOssConfig aliyunOssConfig;

    /**
     *
     * @param objectName 文件的名称(建议使用吗md5对文件进行命名)
     * @param in 输入流
     * @param FileType
     * @return
     * @throws IOException
     */
    @Override
    public String putObject(String objectName, InputStream in, FileTypeEnums FileType) throws IOException {

        String ossEndpoint = aliyunOssConfig.getOssEndpoint();
        String accessId = aliyunOssConfig.getAccessId();
        String secret = aliyunOssConfig.getAccessKeySecret();
        OSSClient client = new OSSClient(ossEndpoint, accessId, secret);

        //临时文件名字/object
        objectName = aliyunOssConfig.getTmpDir() + FileType.getType() + objectName;
        try {

            client.putObject(aliyunOssConfig.getBucketName(), objectName, in);

        } finally {
            if (in != null) {
                in.close();
            }
            client.shutdown();
        }
        return aliyunOssConfig.getBasePath() + "/" + objectName;
    }

    @Override
    public String registerUserImgUrl(String userImgUrl, String folderName) {
        return copyObject(userImgUrl,folderName);
    }


    @Override
    public String putObject(String objectName, InputStream in) throws IOException {
        String minName ;
        OSSClient client = new OSSClient(aliyunOssConfig.getOssEndpoint(),
                aliyunOssConfig.getAccessId(), aliyunOssConfig.getAccessKeySecret());

        ObjectMetadata metadata = new ObjectMetadata();
        //设置客户端响应缓存20s
        metadata.setCacheControl("max-age=20");
        try {
            objectName = "temp/max/" + objectName;

            client.putObject(aliyunOssConfig.getBucketName(), objectName, in,metadata);
            minName = objectName.replace("max", "min");
            String maxUrl = aliyunOssConfig.getBasePath() + "/" + objectName;
            client.putObject(aliyunOssConfig.getBucketName(), minName, AliyunFileNameUtils.converter(maxUrl),metadata);
        }  finally {
            if (in != null) {
                in.close();
            }
            client.shutdown();
        }
        return aliyunOssConfig.getBasePath() + "/" + minName;
    }

    /**
     * 复制临时url到正式的地方
     * exp:tmpUrl =http://mghd.oss-cn-hangzhou.aliyuncs.com/temp/min_img/2019-06-02/253396258381824000.png *
     * dirName = star/1235567345
     * 复制后：http://mghd.oss-cn-hangzhou.aliyuncs.com/fromal/star/1235567345/min_img/2019-06-02/253396258381824000.png
     *
     * @param tmpUrl  临时完整地址
     * @param dirName 目录名<br>
     * @return 复制后的地址
     * @throws Exception
     */
    private String copyObject(String tmpUrl, String dirName) {
        //临时地址替换
        String replace = tmpUrl.replace(aliyunOssConfig.getBasePath() + "/", "");
        String source = replace.replaceAll("temp/(min|max)", dirName);
        //1，创建OSS对象
        OSSClient client = new OSSClient(aliyunOssConfig.getOssEndpoint(), aliyunOssConfig.getAccessId(), aliyunOssConfig.getAccessKeySecret());

        try {
            client.copyObject(aliyunOssConfig.getBucketName(), replace, aliyunOssConfig.getBucketName(), source);
        } finally {
            client.shutdown();
        }

        return aliyunOssConfig.getBasePath() + "/" + source;
    }


}
