package com.lzlk.dao.mybatis.admin.mapper;

import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDoExample;
import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRolePermissionInfoDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRolePermissionInfoMapper {
    long countByExample(AdminRolePermissionInfoDoExample example);

    int deleteByExample(AdminRolePermissionInfoDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminRolePermissionInfoDo record);

    int insertSelective(AdminRolePermissionInfoDo record);

    List<AdminRolePermissionInfoDo> selectByExample(AdminRolePermissionInfoDoExample example);

    AdminRolePermissionInfoDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminRolePermissionInfoDo record, @Param("example") AdminRolePermissionInfoDoExample example);

    int updateByExample(@Param("record") AdminRolePermissionInfoDo record, @Param("example") AdminRolePermissionInfoDoExample example);

    int updateByPrimaryKeySelective(AdminRolePermissionInfoDo record);

    int updateByPrimaryKey(AdminRolePermissionInfoDo record);
}