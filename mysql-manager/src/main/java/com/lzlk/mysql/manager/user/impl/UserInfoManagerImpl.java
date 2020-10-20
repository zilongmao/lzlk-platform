package com.lzlk.mysql.manager.user.impl;

import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.dao.mybatis.user.bean.UserInfoDo;
import com.lzlk.dao.mybatis.user.bean.UserInfoDoExample;
import com.lzlk.dao.mybatis.user.mapper.UserInfoMapper;
import com.lzlk.mysql.manager.user.UserInfoManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/4 14 42
 * @Author: 邻座旅客
 */
@Service
public class UserInfoManagerImpl implements UserInfoManager {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean insert(UserInfoDo userInfoDo) {
        userInfoDo.setCreateTime(new Date());
        userInfoDo.setIsDelete(false);
        userInfoDo.setLastModifyTime(new Date());
        return userInfoMapper.insert(userInfoDo) > 0;
    }

    @Override
    public UserInfoDo queryById(Long id) {

        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean update(UserInfoDo userInfoDo) {
        userInfoDo.setLastModifyTime(new Date());

        return userInfoMapper.updateByPrimaryKey(userInfoDo) > 0;
    }

    @Override
    public UserInfoDo queryByWeChatUnionId(String weChatUnionId) {
        UserInfoDoExample example = new UserInfoDoExample();
        UserInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andWeChatUnionIdEqualTo(weChatUnionId);
        List<UserInfoDo> userInfoDoList = userInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userInfoDoList) ? null : userInfoDoList.get(0);
    }

    @Override
    public UserInfoDo queryByAppletsOpenId(String openId) {
        UserInfoDoExample example = new UserInfoDoExample();
        UserInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andAppletOpenIdEqualTo(openId);
        List<UserInfoDo> userInfoDoList = userInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userInfoDoList) ? null : userInfoDoList.get(0);
    }


    @Override
    public UserInfoDo queryByOfficialAccountOpenId(String officialAccountOpenId) {
        UserInfoDoExample example = new UserInfoDoExample();
        UserInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andOfficialAccountOpenIdEqualTo(officialAccountOpenId);
        List<UserInfoDo> userInfoDoList = userInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userInfoDoList) ? null : userInfoDoList.get(0);
    }

    @Override
    public Long selectUserAddCount(Long startTime, Long endTime) {
        UserInfoDoExample example = new UserInfoDoExample();
        UserInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        if (startTime != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(new Date(startTime));
        }
        if (endTime != null) {
            criteria.andCreateTimeLessThanOrEqualTo(new Date(endTime));
        }
        return userInfoMapper.countByExample(example);
    }

}
