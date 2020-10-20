package com.lzlk.mysql.manager.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDoExample;
import com.lzlk.dao.mybatis.admin.mapper.AdminRoleInfoMapper;
import com.lzlk.mysql.manager.admin.AdminRoleManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 20:34
 * @Created by 湖南达联
 */
@Service
public class AdminRoleManagerImpl implements AdminRoleManager {

    @Resource
    private AdminRoleInfoMapper adminRoleInfoMapper;

    @Override
    public void addRole(AdminRoleInfoDo adminRoleInfoDo) {
        adminRoleInfoDo.setIsDelete(0);
        adminRoleInfoDo.setCreateTime(new Date());
        adminRoleInfoDo.setUpdateTime(new Date());
        adminRoleInfoMapper.insert(adminRoleInfoDo);
    }

    @Override
    public void delRole(Long id,Long loginUserId) {
        AdminRoleInfoDo adminRoleInfoDo = new AdminRoleInfoDo();
        adminRoleInfoDo.setId(id);
        adminRoleInfoDo.setUpdateUserId(loginUserId);
        adminRoleInfoDo.setUpdateTime(new Date());
        adminRoleInfoDo.setIsDelete(1);
        adminRoleInfoMapper.updateByPrimaryKeySelective(adminRoleInfoDo);
    }

    @Override
    public PageInfo<AdminRoleInfoDo> findRoleByPage(Integer maxLoginWeight, Integer pageNo) {
        AdminRoleInfoDoExample example = new AdminRoleInfoDoExample();
        example.createCriteria().andRoleWeightLessThan(maxLoginWeight);
        example.setOrderByClause("id ASC");
        PageHelper.startPage(pageNo, BaseConstants.DEFAULT_PAGE_LIMIT);
        List<AdminRoleInfoDo> adminRoleInfoDos = adminRoleInfoMapper.selectByExample(example);
        return new PageInfo(adminRoleInfoDos);
    }

    @Override
    public void updateRole() {

    }

    @Override
    public AdminRoleInfoDo findRoleById(Long id) {
        AdminRoleInfoDoExample example = new AdminRoleInfoDoExample();
        AdminRoleInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andIsDeleteEqualTo(0);
        List<AdminRoleInfoDo> adminRoleInfoDos = adminRoleInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(adminRoleInfoDos) ? null : adminRoleInfoDos.get(0);
    }

    @Override
    public List<AdminRoleInfoDo> findRoleByIdList(List<Long> idList) {
        AdminRoleInfoDoExample example = new AdminRoleInfoDoExample();
        AdminRoleInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        criteria.andIsDeleteEqualTo(0);
        example.setOrderByClause("id ASC");
        return adminRoleInfoMapper.selectByExample(example);
    }

    @Override
    public List<AdminRoleInfoDo> findAll() {
        return adminRoleInfoMapper.selectByExample(new AdminRoleInfoDoExample());
    }

    @Override
    public boolean checkRoleName(String roleName) {
        AdminRoleInfoDoExample example = new AdminRoleInfoDoExample();
        AdminRoleInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        return adminRoleInfoMapper.countByExample(example) > 0;
    }


}
