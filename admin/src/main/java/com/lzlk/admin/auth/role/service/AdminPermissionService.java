package com.lzlk.admin.auth.role.service;


import com.lzlk.admin.auth.role.vo.AddPermissionVo;
import com.lzlk.admin.auth.role.vo.MenuVo;
import com.lzlk.admin.auth.role.vo.RolePermissionVo;
import com.lzlk.admin.auth.role.vo.UpdatePermissionVo;
import com.lzlk.base.result.Result;

import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/21 15:28
 * @Created by 湖南达联
 */
public interface AdminPermissionService {


    /**
     * 查询所有的权限，分页
     * @param pageNo
     * @param pid
     * @return
     */
    Result<Object> findPermissionByPage(Integer pageNo, Long pid);

    /**
     * 添加权限
     * @param vo
     * @param loginUserId
     * @return
     */
    Result<Object> addPermission(AddPermissionVo vo, Long loginUserId);

    /**
     * 查询用户所有的一级菜单和二级菜单
     * @param loginUserId
     * @return
     */
    Result<Object> findLoginUserMenu(Long loginUserId);

    /**
     * 查询用户所有的一级菜单和二级菜单
     * @param loginUserId
     * @return
     */
    List<MenuVo> getLoginUserMenu(Long loginUserId);


    /**
     * 批量给角色添加权限
     * @param vo
     * @param loginUserId
     * @return
     */
    Result<Object> addRolePermission(RolePermissionVo vo, Long loginUserId);


    /**
     * 修改菜单的显示隐藏
     * @param vo
     * @return
     */
    Result<Object> updatePermission(UpdatePermissionVo vo);



    /**
     * 获取系统请求url
     * @return
     */
    List<String> findAllRequestMappingUrl();


    /**
     * 获取系统的url
     * @return
     */
    Result<Object> findPermissionUrL();

    /**
     * 查询角色下的权限
     * @param roleId
     * @return
     */
    Result<Object> findRolePermissionByRoleId(Long roleId);


}
