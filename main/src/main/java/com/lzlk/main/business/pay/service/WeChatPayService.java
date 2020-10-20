package com.lzlk.main.business.pay.service;

import com.lzlk.base.result.Result;
import com.lzlk.main.business.pay.vo.WeChatPayVo;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/9 11 48
 * @Author: 邻座旅客
 */
public interface WeChatPayService {

    /**
     * 小程序下单
     * @param userId
     * @param weChatPayVo
     * @param request
     * @return
     */
    Result<Object> payByWeChatApplets(Long userId, WeChatPayVo weChatPayVo, HttpServletRequest request);


    /**
     * 接收支付站的回调
     * @param payChannel
     * @param localTradeNo
     * @return
     */
    Result<Object> respondPayNotice(Integer payChannel, String localTradeNo);
}
