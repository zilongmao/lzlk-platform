package com.lzlk.dao.mybatis.system.mapper;

import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDoExample;
import java.util.List;

import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemParameterConfigDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemParameterConfigMapper {
    long countByExample(SystemParameterConfigDoExample example);

    int deleteByExample(SystemParameterConfigDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemParameterConfigDo record);

    int insertSelective(SystemParameterConfigDo record);

    List<SystemParameterConfigDo> selectByExample(SystemParameterConfigDoExample example);

    SystemParameterConfigDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemParameterConfigDo record, @Param("example") SystemParameterConfigDoExample example);

    int updateByExample(@Param("record") SystemParameterConfigDo record, @Param("example") SystemParameterConfigDoExample example);

    int updateByPrimaryKeySelective(SystemParameterConfigDo record);

    int updateByPrimaryKey(SystemParameterConfigDo record);
}