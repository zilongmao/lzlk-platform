package com.lzlk.admin.auth.user.controller;

import com.lzlk.admin.auth.user.service.AdminUserInfoService;
import com.lzlk.admin.auth.user.vo.AddUserInfoVo;
import com.lzlk.admin.common.annotation.AdminLog;
import com.lzlk.admin.common.annotation.AuthPower;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.admin.exception.AdminException;
import com.lzlk.admin.exception.AdminExceptionEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 17:03
 * @Created by 湖南达联
 */
@RestController
@RequestMapping("/admin/user")
@Api(tags = "后台用户管理")
public class AdminUserController {

    @Resource
    private AdminUserInfoService adminUserInfoService;


    @AuthPower
    @GetMapping("/findUserInfoPage")
    @ApiOperation("查询用户信息")
    @AdminLog
    public Result<Object> findUserInfoPage(Long userId, String loginName, @RequestParam Integer pageNo, Integer isDelete){
        if(isDelete!= null ){
            throw new AdminException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }
        return adminUserInfoService.findUserInfoPage(userId, loginName, pageNo,isDelete);
    }

    @AuthPower
    @AdminLog
    @PostMapping("/addUserInfo")
    @ApiOperation("增加用户信息")
    public Result<Object> addUserInfo(@RequestBody @Valid AddUserInfoVo addUserInfoVo){
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return adminUserInfoService.addAdminUserInfo(addUserInfoVo,loginUser.getId());
    }

    @AuthPower
    @AdminLog
    @GetMapping("/updateAdminUserInfoState")
    @ApiOperation("修改用户状态（删除用户）")
    public Result<Object> updateAdminUserInfoState(@RequestParam String loginName, @RequestParam Integer isDelete){
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();

        if(isDelete!= null ){
            throw new AdminException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }

        if(loginUser.getLoginName().equals(loginName)){
            throw new AdminException(AdminExceptionEnums.ADMIN_USER_NOT_DEL_ME_EXCEPTION.getCode());
        }
        return adminUserInfoService.updateAdminUserInfoState(loginName,loginUser.getId(),isDelete);
    }

    @AuthPower
    @AdminLog
    @GetMapping("/refreshUserPassWord")
    @ApiOperation("重置用户密码")
    public Result<Object> refreshUserPassWord(@RequestParam String loginName){
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();

        if(loginUser.getLoginName().equals(loginName)){
              throw new AdminException(AdminExceptionEnums.ADMIN_USER_NOT_RE_ME_EXCEPTION.getCode());
        }
        return adminUserInfoService.refreshUserPassWord(loginName,loginUser.getId());
    }

    @AuthPower
    @AdminLog
    @GetMapping("/checkLoginName")
    @ApiOperation("检查用户名")
    public Result<Object> checkLoginName(@RequestParam String loginName){
        return adminUserInfoService.checkLoginName(loginName);
    }

}
