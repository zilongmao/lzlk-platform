package com.lzlk.dao.mybatis.system.mapper;

import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDoExample;
import java.util.List;

import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDo;
import com.lzlk.dao.mybatis.system.bean.SystemJobConfigDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemJobConfigMapper {
    long countByExample(SystemJobConfigDoExample example);

    int deleteByExample(SystemJobConfigDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemJobConfigDo record);

    int insertSelective(SystemJobConfigDo record);

    List<SystemJobConfigDo> selectByExample(SystemJobConfigDoExample example);

    SystemJobConfigDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemJobConfigDo record, @Param("example") SystemJobConfigDoExample example);

    int updateByExample(@Param("record") SystemJobConfigDo record, @Param("example") SystemJobConfigDoExample example);

    int updateByPrimaryKeySelective(SystemJobConfigDo record);

    int updateByPrimaryKey(SystemJobConfigDo record);
}