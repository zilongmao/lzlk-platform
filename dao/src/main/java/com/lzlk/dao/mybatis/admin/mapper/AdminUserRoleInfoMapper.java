package com.lzlk.dao.mybatis.admin.mapper;

import com.lzlk.dao.mybatis.admin.bean.AdminUserRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserRoleInfoDoExample;
import com.lzlk.dao.mybatis.admin.bean.AdminUserRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminUserRoleInfoDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRoleInfoMapper {
    long countByExample(AdminUserRoleInfoDoExample example);

    int deleteByExample(AdminUserRoleInfoDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminUserRoleInfoDo record);

    int insertSelective(AdminUserRoleInfoDo record);

    List<AdminUserRoleInfoDo> selectByExample(AdminUserRoleInfoDoExample example);

    AdminUserRoleInfoDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminUserRoleInfoDo record, @Param("example") AdminUserRoleInfoDoExample example);

    int updateByExample(@Param("record") AdminUserRoleInfoDo record, @Param("example") AdminUserRoleInfoDoExample example);

    int updateByPrimaryKeySelective(AdminUserRoleInfoDo record);

    int updateByPrimaryKey(AdminUserRoleInfoDo record);
}