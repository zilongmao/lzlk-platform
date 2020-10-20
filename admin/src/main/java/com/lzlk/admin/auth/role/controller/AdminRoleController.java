package com.lzlk.admin.auth.role.controller;

import com.lzlk.admin.auth.role.service.AdminRoleInfoService;
import com.lzlk.admin.auth.role.vo.AddRoleVo;
import com.lzlk.admin.auth.role.vo.AdminRoleVo;
import com.lzlk.admin.auth.role.vo.UserRoleVo;
import com.lzlk.admin.common.annotation.AdminLog;
import com.lzlk.admin.common.annotation.AuthPower;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.spring.SpringCglibBeanUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/21 10:30
 * @Created by 湖南达联
 */
@RestController
@RequestMapping("/admin/role")
@Api(tags = "角色管理")
public class AdminRoleController {


    @Resource
    private AdminRoleInfoService adminRoleInfoService;

    @PostMapping("/addRole")
    @AuthPower
    @AdminLog
    @ApiOperation("添加角色")
    public Result<Object> addRole(@RequestBody @Valid AddRoleVo vo)  {
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return adminRoleInfoService.addRole(vo,loginUser.getId());
    }

    @GetMapping("/findRoleByPage")
    @AuthPower
    @AdminLog
    @ApiOperation("查询角色列表")
    public Result<Object> findRoleByPage(@RequestParam Integer pageNo)  {
        return adminRoleInfoService.findRoleByPage(pageNo);
    }

    @GetMapping("/checkRoleName")
    @AuthPower
    @AdminLog
    @ApiOperation("检查角色名称")
    public Result<Object> checkRoleName(@RequestParam String roleName)  {
        return ResultFactory.success(adminRoleInfoService.checkRoleName(roleName));
    }

    @GetMapping("/findRoleByUserId")
    @AuthPower
    @AdminLog
    @ApiOperation("查询角色下用户信息")
    public Result<Object> findRoleByUserId(@RequestParam Long userId)  {
        return ResultFactory.success(SpringCglibBeanUtils.convertByList(adminRoleInfoService.findRoleByUserId(userId), AdminRoleVo.class));
    }

    @PostMapping("/addUserRole")
    @AuthPower
    @AdminLog
    @ApiOperation("增加用户角色")
    public Result<Object> addUserRole(@RequestBody @Valid UserRoleVo vo)  {
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return adminRoleInfoService.addUserRole(vo.getUserId(),vo.getRoleIdList(),loginUser.getId());
    }



}
