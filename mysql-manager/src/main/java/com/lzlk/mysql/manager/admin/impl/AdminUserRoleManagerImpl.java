package com.lzlk.mysql.manager.admin.impl;

import com.lzlk.dao.mybatis.admin.bean.AdminUserRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserRoleInfoDoExample;
import com.lzlk.dao.mybatis.admin.mapper.AdminUserRoleInfoMapper;
import com.lzlk.mysql.manager.admin.AdminUserRoleManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/21 10:55
 * @Created by 湖南达联
 */
@Service
public class AdminUserRoleManagerImpl implements AdminUserRoleManager {

    @Resource
    private AdminUserRoleInfoMapper userRoleInfoMapper;


    @Override
    public List<AdminUserRoleInfoDo> findUserRoleByUserId(Long userId) {
        AdminUserRoleInfoDoExample example = new AdminUserRoleInfoDoExample();
        AdminUserRoleInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andAdminUserIdEqualTo(userId);
        criteria.andIsDeleteEqualTo(0);
        return userRoleInfoMapper.selectByExample(example);
    }


    @Override
    public boolean checkUserRole(Long userId, Long roleId) {
        AdminUserRoleInfoDoExample example = new AdminUserRoleInfoDoExample();
        AdminUserRoleInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andAdminUserIdEqualTo(userId);
        criteria.andAdminRoleIdEqualTo(roleId);
        criteria.andIsDeleteEqualTo(0);
        return userRoleInfoMapper.countByExample(example) > 0;
    }

    @Override
    public void addUserRole(Long userId, Long roleId, Long loginUserId) {
        AdminUserRoleInfoDo userRoleInfoDo = new AdminUserRoleInfoDo();
        Date now = new Date();
        userRoleInfoDo.setAdminUserId(userId);
        userRoleInfoDo.setAdminRoleId(roleId);
        userRoleInfoDo.setIsDelete(0);
        userRoleInfoDo.setCreateUserId(loginUserId);
        userRoleInfoDo.setCreateTime(now);
        userRoleInfoDo.setUpdateUserId(loginUserId);
        userRoleInfoDo.setUpdateTime(now);
        userRoleInfoMapper.insert(userRoleInfoDo);
    }

    @Override
    public void removeUserRole(Long userId, Long roleId, Long loginUserId) {
        AdminUserRoleInfoDoExample example = new AdminUserRoleInfoDoExample();
        AdminUserRoleInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andAdminUserIdEqualTo(userId);
        criteria.andAdminRoleIdEqualTo(roleId);
        AdminUserRoleInfoDo userRoleInfoDo = new AdminUserRoleInfoDo();
        userRoleInfoDo.setIsDelete(1);
        userRoleInfoDo.setUpdateUserId(loginUserId);
        userRoleInfoDo.setUpdateTime(new Date());
        userRoleInfoMapper.updateByExampleSelective(userRoleInfoDo,example);
    }


}
