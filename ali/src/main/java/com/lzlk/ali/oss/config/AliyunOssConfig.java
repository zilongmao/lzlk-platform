package com.lzlk.ali.oss.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/15 14:36
 * @Created by 湖南达联
 */
@Component
@Getter
@Slf4j
public class AliyunOssConfig {


    @Resource
    private ApplicationContext applicationContext; //注入applicationContext

    /**
     * 阿里云ACCESS_ID
     */
    private String accessId;
    /**
     * 阿里云ACCESS_KEY
     */
    private String accessKeySecret;

    /**
     * 阿里云OSS_ENDPOINT  Url
     */
    private String ossEndpoint;

    /**
     * 阿里云BUCKET_NAME  OSS http://mianshibaoimg.oss-cn-hangzhou.aliyuncs.com/pc/2015-01-01/123.jpg
     */
    private String bucketName;


    private String basePath;//我们阿里云的basePath

    private String tmpDir;//临时文件夹

    private String formalDir;//正式文件夹

    public AliyunOssConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @PostConstruct
    public void run(){
        Environment environment =  applicationContext.getEnvironment();
        accessId = environment.getProperty("access.id");
        accessKeySecret = environment.getProperty("access.key");
        ossEndpoint = environment.getProperty("oss.endpoint");
        bucketName = environment.getProperty("bucket.name");
        basePath = environment.getProperty("base.path");
        tmpDir = environment.getProperty("tmp.dir");
        formalDir = environment.getProperty("formal.dir");
        log.info("================ OSSConfig 配置成功 ================");
    }

}
