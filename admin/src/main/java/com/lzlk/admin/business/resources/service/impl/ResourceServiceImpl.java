package com.lzlk.admin.business.resources.service.impl;


import com.lzlk.admin.business.resources.service.ResourceService;
import com.lzlk.admin.exception.AdminException;
import com.lzlk.admin.exception.AdminExceptionEnums;
import com.lzlk.ali.oss.upload.AliyunOSSUploadService;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.plugin.snowflakeid.service.ISnowflakeIdService;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.file.CheckFileTypeUtil;
import com.lzlk.base.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/05/06 14:41
 * @Created by 湖南爱豆
 */
@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private AliyunOSSUploadService aliyunOssUploadService;

    @Resource
    private ISnowflakeIdService snowflakeIdService;

    @Override
    public Result<Object> uploadTemporaryBase64Img(String base64) {
        try{

            String[] d =base64.split("base64,");
            String imgData;

            if (d != null && d.length == 2) {
                imgData = d[1];
            } else {
                throw new AdminException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
            }

            String suffix = "";
            byte[] bs = Base64.decodeBase64(imgData);
            for (int i = 2; i < 5; i++) {
                byte[] b = new byte[i];
                suffix = CheckFileTypeUtil.getFileType(new ByteArrayInputStream(bs), b);
                if (null != suffix) {
                    break;
                }
            }
            if (null == suffix) {
                throw new AdminException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
            }
            String fileName = LocalDate.now().toString()+"/" +  snowflakeIdService.nextId()  + "." + suffix;
            ByteArrayInputStream in = new ByteArrayInputStream(bs);

            return ResultFactory.success(aliyunOssUploadService.putObject(fileName, in));
        }catch (Exception e){
            //防止图片上传异常(比如传文件不被压缩支持,比如文件后缀为jpg，但实际文件头为riff)
            throw new AdminException(AdminExceptionEnums.ADMIN_PIC_UPLOAD_FAIL_EXCEPTION.getCode());
        }
    }

    @Override
    public Result<Object> uploadTemporaryImg(MultipartFile file) {
        try{
            if(file == null){
                throw new AdminException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
            }

            String fileExtensionName = FileUtils.getExtensionName(file.getOriginalFilename());

            if (file.getSize() > 10485760L) {
                throw new AdminException(PublicExceptionCodeEnum.Ex_FILE_SIZE_MAX_ERROR.getCode());
            }
            InputStream inputStream = file.getInputStream();

            //获取文件的md5值
            String md5 = getFileMD5(transferToFile(file));

            String s = aliyunOssUploadService.putObject(md5+"." + fileExtensionName, inputStream);

            return ResultFactory.success( s);
        }catch (Exception e){
            log.error("图片上传失败：{}",ExceptionUtils.getStackTrace(e));
            throw new AdminException(AdminExceptionEnums.ADMIN_PIC_UPLOAD_FAIL_EXCEPTION.getCode());
        }

    }

    /**
     * 转换 file
     * @param multipartFile
     * @return
     */
    private File transferToFile(MultipartFile multipartFile) {
//        选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");

            String prefix = "file"+filename[0];
            String suffix = filename[1];
            file=File.createTempFile(prefix, suffix);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取单个文件的MD5值！
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file)
    {

        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1)
            {
                digest.update(buffer, 0, len);
            }
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}
