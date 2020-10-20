package com.lzlk.admin.auth.user.controller;


import com.lzlk.admin.auth.user.service.AdminUserInfoService;
import com.lzlk.admin.auth.user.vo.LoginInfoVo;
import com.lzlk.admin.common.annotation.AdminLog;
import com.lzlk.admin.exception.AdminException;
import com.lzlk.admin.exception.AdminExceptionEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.request.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/18 17:06
 * @Created by 湖南达联
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "登录")
public class AdminLoginController {

    @Resource
    private AdminUserInfoService adminUserInfoService;


    @PostMapping("/login")
    @ApiOperation("登录")
    @AdminLog
    public Result<Object> login(@RequestBody @Valid LoginInfoVo loginInfoVo, HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();

        if(subject.isAuthenticated()){
            throw new AdminException(AdminExceptionEnums.ADMIN_LOGIN_EX.getCode());
        }
        UsernamePasswordToken token = new UsernamePasswordToken(loginInfoVo.getLoginName(), loginInfoVo.getPassWord());
        subject.login(token);
        return adminUserInfoService.loginSuccess(loginInfoVo.getLoginName(), RequestUtil.getClientIP(request));

    }

    @GetMapping("/error")
    @ApiOperation("登录失效，提示")
    public Result<Object> error() throws Exception {
        return ResultFactory.failure(PublicExceptionCodeEnum.EX_USER_NOT_LOGIN_ERROR.getCode(),
                PublicExceptionCodeEnum.EX_USER_NOT_LOGIN_ERROR.getMsg());

    }



}
