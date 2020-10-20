package com.lzlk.admin.auth.role.service;

import com.lzlk.admin.auth.role.vo.AddRoleVo;
import com.lzlk.base.result.Result;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;

import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 20:42
 * @Created by 湖南达联
 */
public interface AdminRoleInfoService {


    /**
     * 查询角色列表
     * @param pageNo
     * @return
     */
    Result<Object> findRoleByPage(Integer pageNo);


    /**
     * id查询角色
     * @param id
     * @return
     */
    AdminRoleInfoDo findRoleById(Long id);


    /**
     * 添加角色
     * @param vo
     * @param loginUserId
     * @return
     */
    Result<Object> addRole(AddRoleVo vo, Long loginUserId);


    /**
     * 检查角色名称
     * @param roleName
     * @return
     */
    boolean checkRoleName(String roleName);


    /**
     * 查询用户关联的角色
     * @param userId
     * @return
     */
    List<AdminRoleInfoDo> findRoleByUserId(Long userId);


    /**
     * 查询全部角色
     * @return
     */
    List<AdminRoleInfoDo> findAll();


    /**
     * 批量绑定角色关系
     * @param userId
     * @param roleIdList
     * @param loginUserId
     * @return
     */
    Result<Object> addUserRole(Long userId, List<Long> roleIdList, Long loginUserId);


    /**
     * 检查用户权重
     * @param loginUserId
     * @param operationUserId
     */
    void checkUserRoleWeight(Long loginUserId, Long operationUserId);






}
