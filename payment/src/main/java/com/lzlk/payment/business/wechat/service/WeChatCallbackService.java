package com.lzlk.payment.business.wechat.service;

import java.util.Map;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/9 01 28
 * @Author: 邻座旅客
 */
public interface WeChatCallbackService {

    /**
     * 接收微信回调
     * @param map
     * @return
     */
    String callback(Map<String, String> map);
}
