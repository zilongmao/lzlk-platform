package com.lzlk.dao.mybatis.admin.mapper;

import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDoExample;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRoleInfoDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleInfoMapper {
    long countByExample(AdminRoleInfoDoExample example);

    int deleteByExample(AdminRoleInfoDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminRoleInfoDo record);

    int insertSelective(AdminRoleInfoDo record);

    List<AdminRoleInfoDo> selectByExample(AdminRoleInfoDoExample example);

    AdminRoleInfoDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminRoleInfoDo record, @Param("example") AdminRoleInfoDoExample example);

    int updateByExample(@Param("record") AdminRoleInfoDo record, @Param("example") AdminRoleInfoDoExample example);

    int updateByPrimaryKeySelective(AdminRoleInfoDo record);

    int updateByPrimaryKey(AdminRoleInfoDo record);
}