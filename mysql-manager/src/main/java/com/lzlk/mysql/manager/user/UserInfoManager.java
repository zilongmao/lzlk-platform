package com.lzlk.mysql.manager.user;

import com.lzlk.dao.mybatis.user.bean.UserInfoDo;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/4 14 40
 * @Author: 邻座旅客
 */
public interface UserInfoManager {

    /**
     * 用户注册
     * @param userInfoDo
     * @return
     */
    boolean insert(UserInfoDo userInfoDo);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    UserInfoDo queryById(Long id);

    /**
     * 修改用户信息
     * @param userInfoDo
     * @return
     */
    boolean update(UserInfoDo userInfoDo);

    /**
     * 根据微信unionID查询用户信息
     * @param weChatUnionId
     * @return
     */
    UserInfoDo queryByWeChatUnionId(String weChatUnionId);

    /**
     * 根据小程序openID查询用户信息
     * @param openId
     * @return
     */
    UserInfoDo queryByAppletsOpenId(String openId);

    /**
     * 根据公众号openID查询用户信息
     * @param officialAccountOpenId
     * @return
     */
    UserInfoDo queryByOfficialAccountOpenId(String officialAccountOpenId);

    /**
     * 查询某个时间段新增用户数
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @return
     */
    Long selectUserAddCount(Long startTime, Long endTime);

}
