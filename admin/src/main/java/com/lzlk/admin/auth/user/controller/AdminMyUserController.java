package com.lzlk.admin.auth.user.controller;

import com.lzlk.admin.auth.role.service.AdminPermissionService;
import com.lzlk.admin.auth.role.service.AdminRoleInfoService;
import com.lzlk.admin.auth.user.service.AdminUserInfoService;
import com.lzlk.admin.auth.user.vo.AdminUpdatePassWordVo;
import com.lzlk.admin.common.annotation.AdminLog;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/18 17:05
 * @Created by 湖南达联
 */
@RestController
@RequestMapping("/my")
@Api(tags = "后台用户资料")
public class AdminMyUserController {

    @Resource
    private AdminUserInfoService adminUserInfoService;

    @Resource
    private AdminRoleInfoService adminRoleInfoService;

    @Resource
    private AdminPermissionService adminPermissionService;


    @GetMapping("/loginOut")
    @ApiOperation("退出登录")
    @AdminLog
    public Result<Object> loginOut(){
        Subject subject = SecurityUtils.getSubject();

        if(subject.isAuthenticated()){
            subject.logout();
        }
        return ResultFactory.success();
    }


    @PostMapping("/updatePassWord")
    @ApiOperation("修改密码")
    @AdminLog
    public Result<Object> updatePassWord(@Valid @RequestBody AdminUpdatePassWordVo vo){
        return adminUserInfoService.updatePassWord(vo);
    }



    @GetMapping("/findRoleIsMy")
    @ApiOperation("查询用户关联的角色")
    @AdminLog
    public Result<Object> findRoleIsMy(){
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return ResultFactory.success(adminRoleInfoService.findRoleByUserId(loginUser.getId()));
    }

    @GetMapping("/findLoginUserMenu")
    @ApiOperation("查询登录用户可查看的菜单")
    @AdminLog
    public Result<Object> findLoginUserMenu(){
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return adminPermissionService.findLoginUserMenu(loginUser.getId());
    }

    @GetMapping("/findLoginUser")
    @ApiOperation("查询登录用户")
    @AdminLog
    public Result<Object> findLoginUser(){
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return ResultFactory.success(loginUser);
    }
}
