package com.lzlk.admin.business.resources.service;


import com.lzlk.base.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/05/06 14:40
 * @Created by 湖南爱豆
 */
public interface ResourceService {

    /**
     * 上传临时资源
     * @param base64
     * @return
     */
    Result<Object> uploadTemporaryBase64Img(String base64);

    /**
     * 文件上传
     * @param file           文件
     * @return  返回文件上传地址
     */
    Result<Object> uploadTemporaryImg(MultipartFile file);

}
