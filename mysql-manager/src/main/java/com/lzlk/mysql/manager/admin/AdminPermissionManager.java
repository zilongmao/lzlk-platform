package com.lzlk.mysql.manager.admin;



import com.github.pagehelper.PageInfo;
import com.lzlk.dao.mybatis.admin.bean.AdminPermissionInfoDo;

import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/21 15:03
 * @Created by 湖南达联
 */
public interface AdminPermissionManager {

    /**
     * 增加权限
     * @param adminPermissionInfoDo
     */
    void addPermission(AdminPermissionInfoDo adminPermissionInfoDo);

    /**
     * 删除权限
     * @param permissionId
     * @param loginUserId
     * @param isValidEnums
     */
    void delPermission(Long permissionId, Long loginUserId, Integer isValidEnums);

    /**
     * 修改权限
     * @param adminPermissionInfoDo
     */
    void updatePermission(AdminPermissionInfoDo adminPermissionInfoDo);

    /**
     * 查询权限菜单
     * @param pid
     * @param pageNo
     * @param isSort
     * @return
     */
    PageInfo<AdminPermissionInfoDo> findPermissionByPage(Long pid, Integer pageNo, boolean isSort);

    /**
     * 查询排序值
     * @param pid
     * @return
     */
    Integer getPermissionSortNum(Long pid);

    /**
     * 修改排序值
     * @param id
     * @param sort
     */
    void updateSotrNum(Long id, Integer sort);


    /**
     * 查询权限菜单
     * @param permissionIdList
     * @return
     */
    List<AdminPermissionInfoDo> findPermissionByIdList(List<Long> permissionIdList, Long pid);


    /**
     * 校验是否存在
     * @param id
     * @return
     */
    boolean checkPermission(Long id);
}
