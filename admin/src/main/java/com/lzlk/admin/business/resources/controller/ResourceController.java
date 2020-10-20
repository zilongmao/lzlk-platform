package com.lzlk.admin.business.resources.controller;


import com.lzlk.admin.business.resources.service.ResourceService;
import com.lzlk.admin.business.resources.vo.Base64Vo;
import com.lzlk.base.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 邻座旅客
 * @Description TODO 资源上传
 * @Date 2020/05/06 14:50
 * @Created
 */

@Api(tags = "资源上传")
@RequestMapping("/uploadImg")
@RestController
public class ResourceController  {

    @Resource
    private ResourceService resourceService;


    @PostMapping("/uploadBase64Img")
    @ApiOperation(value="base64图片上传", notes="base64图片上传")
    public Result<Object> uploadTemporaryBase64Img(@RequestBody @Valid Base64Vo base64Vo){
        return resourceService.uploadTemporaryBase64Img(base64Vo.getBaseImg());
    }

    @ApiOperation(value = "上传-图片", notes = "图片上传")
    @RequestMapping(value = {"/uploadImg"}, method = RequestMethod.POST)
    public Result<Object> uploadImg(@RequestParam("file") MultipartFile file){

        return resourceService.uploadTemporaryImg(file);
    }



}
