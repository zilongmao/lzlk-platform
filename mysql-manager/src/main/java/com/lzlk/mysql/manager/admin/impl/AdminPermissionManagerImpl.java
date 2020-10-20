package com.lzlk.mysql.manager.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.dao.mybatis.admin.bean.AdminPermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminPermissionInfoDoExample;
import com.lzlk.dao.mybatis.admin.mapper.AdminPermissionInfoMapper;
import com.lzlk.mysql.manager.admin.AdminPermissionManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/21 15:10
 * @Created by 湖南达联
 */
@Service
public class AdminPermissionManagerImpl implements AdminPermissionManager {

    @Resource
    private AdminPermissionInfoMapper adminPermissionInfoMapper;


    @Override
    public void addPermission(AdminPermissionInfoDo adminPermissionInfoDo) {
        Date now = new Date();
        adminPermissionInfoDo.setCreateTime(now);
        adminPermissionInfoDo.setUpdateTime(now);
        adminPermissionInfoDo.setIsDelete(0);
        adminPermissionInfoMapper.insert(adminPermissionInfoDo);
    }

    @Override
    public void delPermission(Long permissionId,Long loginUserId,Integer isValidEnums) {
        AdminPermissionInfoDo adminPermissionInfoDo = new AdminPermissionInfoDo();
        adminPermissionInfoDo.setId(permissionId);
        adminPermissionInfoDo.setIsDelete(isValidEnums);
        adminPermissionInfoDo.setUpdateTime(new Date());
        adminPermissionInfoDo.setUpdateUserId(loginUserId);
        adminPermissionInfoMapper.updateByPrimaryKeySelective(adminPermissionInfoDo);
    }

    @Override
    public void updatePermission(AdminPermissionInfoDo adminPermissionInfoDo) {
        adminPermissionInfoMapper.updateByPrimaryKeySelective(adminPermissionInfoDo);

    }

    @Override
    public PageInfo<AdminPermissionInfoDo> findPermissionByPage(Long pid, Integer pageNo, boolean isSort) {
        AdminPermissionInfoDoExample example = new AdminPermissionInfoDoExample();
        AdminPermissionInfoDoExample example1 = new AdminPermissionInfoDoExample();
        AdminPermissionInfoDoExample.Criteria criteria = example.createCriteria();
        AdminPermissionInfoDoExample.Criteria criteria1 = example1.createCriteria();
        if(pid != null){
            criteria.andPidEqualTo(pid);
            criteria1.andPidEqualTo(pid);
        }

        if(isSort){
            example.setOrderByClause(" sort_num ASC ");
        }
        long size = adminPermissionInfoMapper.countByExample(example1);
        if ((pid == 0 || size <20) &&pageNo !=1 ){
            return  new PageInfo<>();
        }
        PageHelper.startPage(pageNo, BaseConstants.DEFAULT_PAGE_LIMIT);
        List<AdminPermissionInfoDo> adminPermissionInfoDos = adminPermissionInfoMapper.selectByExample(example);
        PageInfo<AdminPermissionInfoDo> pageInfo = new PageInfo<>(adminPermissionInfoDos);
        return pageInfo == null ? new PageInfo<>() : pageInfo;
    }

    @Override
    public Integer getPermissionSortNum(Long pid) {
        AdminPermissionInfoDoExample example = new AdminPermissionInfoDoExample();
        example.setOrderByClause(" sort_num DESC ");
        example.setLimit(1);
        List<AdminPermissionInfoDo> adminPermissionInfoDos = adminPermissionInfoMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(adminPermissionInfoDos)){
           return adminPermissionInfoDos.get(0).getSortNum() + 1;
        }else {
           return 1;
        }
    }

    @Override
    public void updateSotrNum(Long id, Integer sort) {

    }

    @Override
    public List<AdminPermissionInfoDo> findPermissionByIdList(List<Long> permissionIdList, Long pid) {
        AdminPermissionInfoDoExample example = new AdminPermissionInfoDoExample();
        AdminPermissionInfoDoExample.Criteria criteria = example.createCriteria().
                andIdIn(permissionIdList).andIsDeleteEqualTo(0);
        if(pid != null){
            criteria.andPidNotEqualTo(pid);
         }

        return adminPermissionInfoMapper.selectByExample(example);
    }

    @Override
    public boolean checkPermission(Long id) {
        return  adminPermissionInfoMapper.selectByPrimaryKey(id) != null  ;
    }
}
