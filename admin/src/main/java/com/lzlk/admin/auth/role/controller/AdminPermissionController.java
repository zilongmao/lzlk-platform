package com.lzlk.admin.auth.role.controller;

import com.lzlk.admin.auth.role.service.AdminPermissionService;
import com.lzlk.admin.auth.role.vo.AddPermissionVo;
import com.lzlk.admin.auth.role.vo.RolePermissionVo;
import com.lzlk.admin.auth.role.vo.UpdatePermissionVo;
import com.lzlk.admin.common.annotation.AdminLog;
import com.lzlk.admin.common.annotation.AuthPower;
import com.lzlk.admin.common.util.AdminUtil;
import com.lzlk.admin.exception.AdminException;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.result.Result;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/24 20:37
 * @Created by 湖南达联
 */
@RestController
@RequestMapping("/admin/permission")
@Api(tags = "权限管理")
public class AdminPermissionController {


    @Resource
    private AdminPermissionService adminPermissionService;


    @PostMapping("/addPermission")
    @AuthPower
    @AdminLog
    @ApiOperation("添加权限")
    public Result<Object> addRole(@RequestBody @Valid AddPermissionVo vo, HttpServletRequest request) {
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return adminPermissionService.addPermission(vo, loginUser.getId());
    }

    @PostMapping("/updatePermission")
    @AuthPower
    @AdminLog
    @ApiOperation("修改菜单的显示隐藏")
    public Result<Object> updatePermission(@RequestBody @Valid UpdatePermissionVo vo, HttpServletRequest request) {
        if (vo.getId() == null) {
            throw new AdminException(PublicExceptionCodeEnum.Ex_PARAM_ERROR.getCode());
        }
        return adminPermissionService.updatePermission(vo);
    }

    @PostMapping("/addRolePermission")
    @AuthPower
    @AdminLog
    @ApiOperation("批量给角色添加权限")
    public Result<Object> addRolePermission(@RequestBody @Valid RolePermissionVo vo, HttpServletRequest request) {
        AdminUserInfoDo loginUser = AdminUtil.getLoginUser();
        return adminPermissionService.addRolePermission(vo, loginUser.getId());
    }

    @GetMapping("/findPermissionByPage")
    @AuthPower
    @AdminLog
    @ApiOperation("查询所有的权限")
    public Result<Object> findPermissionByPage(@RequestParam Integer pageNo,
                                               @RequestParam(required = false) Long pid, HttpServletRequest request) {
        return adminPermissionService.findPermissionByPage(pageNo, pid);
    }

    @GetMapping("/findPermissionUrL")
    @AuthPower
    @AdminLog
    @ApiOperation("获取系统的url")
    public Result<Object> findPermissionUrL(HttpServletRequest request) {
        return adminPermissionService.findPermissionUrL();
    }


    @GetMapping("/findRolePermissionByRoleId")
    @AuthPower
    @AdminLog
    @ApiOperation("查询角色下的权限")
    public Result<Object> findRolePermissionByRoleId(HttpServletRequest request, @RequestParam Long roleId) {
        return adminPermissionService.findRolePermissionByRoleId(roleId);
    }


}
