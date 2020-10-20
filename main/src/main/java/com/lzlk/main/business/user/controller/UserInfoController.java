package com.lzlk.main.business.user.controller;

import com.lzlk.base.annotation.auth.AuthPassport;
import com.lzlk.base.annotation.auth.CurrentUser;
import com.lzlk.base.result.Result;
import com.lzlk.dao.mybatis.user.bean.UserInfoDo;
import com.lzlk.main.business.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/5 15:34
 * @Author: 邻座旅客
 */
@Api(tags = "用户个人信息相关接口")
@RestController
@RequestMapping("/{source}/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @AuthPassport
    @ApiOperation("查询用户个人信息")
    @GetMapping("/findUserInfo")
    public Result<Object> findUserInfo(@CurrentUser @ApiIgnore UserInfoDo userInfo) {
        return userInfoService.findUserInfo(userInfo.getId());
    }
}
