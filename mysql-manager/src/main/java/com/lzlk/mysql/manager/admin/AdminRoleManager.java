package com.lzlk.mysql.manager.admin;

import com.github.pagehelper.PageInfo;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;

import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 20:30
 * @Created by 湖南达联
 */
public interface AdminRoleManager {

    /**
     * 添加角色
     * @param adminRoleInfoDo
     */
    void addRole(AdminRoleInfoDo adminRoleInfoDo);

    /**
     * 删除角色
     * @param id
     */
    void delRole(Long id, Long loginUserId);

    /**
     * 查询角色
     * @param maxLoginWeight
     * @param pageNo
     * @return
     */
    PageInfo<AdminRoleInfoDo> findRoleByPage(Integer maxLoginWeight, Integer pageNo);

    /**
     * 修改角色
     */
    void updateRole();

    /**
     * id查询角色
     * @param id
     * @return
     */
    AdminRoleInfoDo findRoleById(Long id);

    /**
     * 查询用户的所有角色
     * @param idList
     * @return
     */
    List<AdminRoleInfoDo> findRoleByIdList(List<Long> idList);

    /**
     * 查询全部角色
     * @return
     */
    List<AdminRoleInfoDo> findAll();

    /**
     * 检查角色名称
     * @param roleName
     * @return
     */
    boolean checkRoleName(String roleName);




}
