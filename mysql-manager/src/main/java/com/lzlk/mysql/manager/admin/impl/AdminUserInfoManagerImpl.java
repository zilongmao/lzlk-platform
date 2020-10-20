package com.lzlk.mysql.manager.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserInfoDoExample;
import com.lzlk.dao.mybatis.admin.mapper.AdminUserInfoMapper;
import com.lzlk.mysql.manager.admin.AdminUserInfoManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/18 16:27
 * @Created by 湖南达联
 */
@Service
public class AdminUserInfoManagerImpl implements AdminUserInfoManager {

    @Resource
    private AdminUserInfoMapper adminUserInfoMapper;

    @Override
    public AdminUserInfoDo findUserByLoginName(String loginName) {
        AdminUserInfoDoExample example = new AdminUserInfoDoExample();
        example.createCriteria().andLoginNameEqualTo(loginName);
        List<AdminUserInfoDo> adminUserInfoDos = adminUserInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(adminUserInfoDos) ? null : adminUserInfoDos.get(0);
    }

    @Override
    public AdminUserInfoDo findUserById(Long userId) {
        return adminUserInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUserWeight(Long userId,Long loginUserId, Integer weight) {
        AdminUserInfoDo adminUserInfoDo = new AdminUserInfoDo();
        adminUserInfoDo.setUpdateTime(new Date());
        adminUserInfoDo.setUpdateUserId(loginUserId);
        adminUserInfoDo.setMaxWeight(weight);
        adminUserInfoMapper.updateByPrimaryKeySelective(adminUserInfoDo);
    }

    @Override
    public boolean updateUserLoginIp(String loginName, String ip) {
        AdminUserInfoDoExample example = new AdminUserInfoDoExample();
        example.createCriteria().andLoginNameEqualTo(loginName);
        AdminUserInfoDo userInfoDo = new AdminUserInfoDo();
        userInfoDo.setLoginTime(new Date());
        userInfoDo.setLoginIp(ip);
        return adminUserInfoMapper.updateByExampleSelective(userInfoDo,example) > 0;
    }

    @Override
    public void addUserInfo(AdminUserInfoDo adminUserInfoDo) {
        Date date = new Date();
        adminUserInfoDo.setCreateTime(date);
        adminUserInfoDo.setUpdateTime(date);
        adminUserInfoDo.setIsDelete(0);
        adminUserInfoMapper.insert(adminUserInfoDo);
    }

    @Override
    public void removeAdminUserInfo(String loginName,Long loginUserId,Integer isDelete) {
        AdminUserInfoDoExample example = new AdminUserInfoDoExample();
        example.createCriteria().andLoginNameEqualTo(loginName);
        AdminUserInfoDo userInfoDo = new AdminUserInfoDo();
        userInfoDo.setIsDelete(isDelete);
        userInfoDo.setUpdateUserId(loginUserId);
        userInfoDo.setUpdateTime(new Date());
        adminUserInfoMapper.updateByExampleSelective(userInfoDo,example);
    }

    @Override
    public void updateAdminUserInfoById(AdminUserInfoDo adminUserInfoDo) {
        adminUserInfoDo.setIsDelete(null);
        adminUserInfoMapper.updateByPrimaryKeySelective(adminUserInfoDo);
    }

    @Override
    public PageInfo<AdminUserInfoDo> findAdminUserPage(Long userId, String loginName, Integer maxWeight, Integer  pageNo, Integer isDelete) {
        AdminUserInfoDoExample example = new AdminUserInfoDoExample();
        AdminUserInfoDoExample.Criteria criteria = example.createCriteria();
        if(userId != null){
            criteria.andIdEqualTo(userId);
        }
        if(StringUtils.isNotBlank(loginName)){
            criteria.andLoginNameEqualTo(loginName);
        }
        if(isDelete != null){
            criteria.andIsDeleteEqualTo(isDelete);
        }
        criteria.andMaxWeightLessThan(maxWeight == null ? 0 : maxWeight);
        example.setOrderByClause("id ASC");
        PageHelper.startPage(pageNo, BaseConstants.DEFAULT_PAGE_LIMIT);
        List<AdminUserInfoDo> adminUserInfoDos = adminUserInfoMapper.selectByExample(example);
        return new PageInfo(adminUserInfoDos);
    }

}
