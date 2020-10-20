package com.lzlk.main.business.user.service.impl;

import com.lzlk.base.result.Result;
import com.lzlk.main.business.user.service.UserInfoService;
import com.lzlk.mysql.manager.user.UserInfoManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/5 15:37
 * @Author: 邻座旅客
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoManager userInfoManager;


    @Override
    public Result<Object> findUserInfo(Long userId) {
        return null;
    }

}
