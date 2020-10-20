package com.lzlk.dao.mybatis.user.mapper;

import com.lzlk.dao.mybatis.user.bean.UserInfoDo;
import com.lzlk.dao.mybatis.user.bean.UserInfoDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    long countByExample(UserInfoDoExample example);

    int deleteByExample(UserInfoDoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoDo record);

    int insertSelective(UserInfoDo record);

    List<UserInfoDo> selectByExample(UserInfoDoExample example);

    UserInfoDo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserInfoDo record, @Param("example") UserInfoDoExample example);

    int updateByExample(@Param("record") UserInfoDo record, @Param("example") UserInfoDoExample example);

    int updateByPrimaryKeySelective(UserInfoDo record);

    int updateByPrimaryKey(UserInfoDo record);

}