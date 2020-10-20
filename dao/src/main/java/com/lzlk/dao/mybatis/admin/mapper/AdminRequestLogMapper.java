package com.lzlk.dao.mybatis.admin.mapper;

import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDoExample;
import java.util.List;

import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDo;
import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRequestLogMapper {
    long countByExample(AdminRequestLogDoExample example);

    int deleteByExample(AdminRequestLogDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminRequestLogDo record);

    int insertSelective(AdminRequestLogDo record);

    List<AdminRequestLogDo> selectByExample(AdminRequestLogDoExample example);

    AdminRequestLogDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminRequestLogDo record, @Param("example") AdminRequestLogDoExample example);

    int updateByExample(@Param("record") AdminRequestLogDo record, @Param("example") AdminRequestLogDoExample example);

    int updateByPrimaryKeySelective(AdminRequestLogDo record);

    int updateByPrimaryKey(AdminRequestLogDo record);
}