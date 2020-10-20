package com.lzlk.main.business.sign.service;


import com.lzlk.base.result.Result;
import com.lzlk.main.business.sign.vo.WeChatLoginParamVo;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/3/31 16 48
 * @Author: 邻座旅客
 */
public interface SignService {


    /**
     * 创建登录token
     * @param userUinId
     * @param source
     * @return
     */
    String createSignToken(Long userUinId, String source);

    /**
     * 微信登录
     * @param weChatLoginParamVo
     * @return
     */
    Result<Object> weChatLogin(WeChatLoginParamVo weChatLoginParamVo);


    /**
     * 获取小程序登录信息
     * @param jsCode
     * @param encryptedData
     * @param iv
     * @return
     */
    Result<Object> getWeChatLoginData(String jsCode, String encryptedData, String iv);


    /**
     * 获取微信的跳转地址
     * @return
     */
    String oauth2buildAuthorizationUrl();


    /**
     * 获取用户信息
     * @param code
     * @return
     */
    Result<Object> getWeChatAccess(String code);

}
