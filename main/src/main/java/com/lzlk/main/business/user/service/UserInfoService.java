package com.lzlk.main.business.user.service;

import com.lzlk.base.result.Result;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/5 15:36
 * @Author: 邻座旅客
 */
public interface UserInfoService {

    /**
     * 查询用户个人信息
     * @param userId
     * @return
     */
    Result<Object> findUserInfo(Long userId);

}
