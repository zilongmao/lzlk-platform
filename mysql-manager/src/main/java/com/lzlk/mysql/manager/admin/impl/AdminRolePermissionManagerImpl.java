package com.lzlk.mysql.manager.admin.impl;


import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDoExample;
import com.lzlk.dao.mybatis.admin.mapper.AdminRolePermissionInfoMapper;
import com.lzlk.mysql.manager.admin.AdminRolePermissionManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/24 11:26
 * @Created by 湖南达联
 */
@Service
public class AdminRolePermissionManagerImpl implements AdminRolePermissionManager {

    @Resource
    private AdminRolePermissionInfoMapper adminRolePermissionInfoMapper;

    @Override
    public void addRolePermission(Long roleId, Long permissionId, Long loginUserId) {
        AdminRolePermissionInfoDo infoDo = new AdminRolePermissionInfoDo();
        Date now = new Date();
        infoDo.setAdminRoleId(roleId);
        infoDo.setAdminPermissionId(permissionId);
        infoDo.setCreateUserId(loginUserId);
        infoDo.setCreateTime(now);
        infoDo.setUpdateUserId(loginUserId);
        infoDo.setUpdateTime(now);
        infoDo.setIsDelete(0);
        adminRolePermissionInfoMapper.insert(infoDo);
    }

    @Override
    public void removeRolePermission(Long roleId, Long permissionId, Long loginUserId) {
        AdminRolePermissionInfoDoExample example = new AdminRolePermissionInfoDoExample();
        example.createCriteria().andAdminRoleIdEqualTo(roleId).andAdminPermissionIdEqualTo(permissionId)
                .andIsDeleteEqualTo(0);

        AdminRolePermissionInfoDo infoDo = new AdminRolePermissionInfoDo();
        infoDo.setIsDelete(1);
        infoDo.setUpdateTime(new Date());
        infoDo.setUpdateUserId(loginUserId);
        adminRolePermissionInfoMapper.updateByExampleSelective(infoDo,example);
    }

    @Override
    public List<AdminRolePermissionInfoDo> findRolePermissionByRoleIdList(List<Long> roleIdList) {
        AdminRolePermissionInfoDoExample example = new AdminRolePermissionInfoDoExample();
        example.createCriteria().andAdminRoleIdIn(roleIdList)
                .andIsDeleteEqualTo(0);
        return adminRolePermissionInfoMapper.selectByExample(example);
    }

    @Override
    public List<AdminRolePermissionInfoDo> findRolePermissionByRoleId(Long roleId) {
        AdminRolePermissionInfoDoExample example = new AdminRolePermissionInfoDoExample();
        example.createCriteria().andAdminRoleIdEqualTo(roleId)
                .andIsDeleteEqualTo(0);
        return adminRolePermissionInfoMapper.selectByExample(example);
    }

    @Override
    public boolean checkRolePermission(Long roleId, Long permissionId) {
        AdminRolePermissionInfoDoExample example = new AdminRolePermissionInfoDoExample();
        example.createCriteria().andAdminRoleIdEqualTo(roleId)
                .andAdminPermissionIdEqualTo(permissionId)
                .andIsDeleteEqualTo(0);
        return adminRolePermissionInfoMapper.countByExample(example) > 0;
    }
}
