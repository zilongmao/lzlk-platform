package com.lzlk.payment.business.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.lzlk.base.enums.pay.PayChannelEnums;
import com.lzlk.base.enums.pay.PayGoodsOrderStatusEnums;
import com.lzlk.base.enums.pay.PayOrderStatusEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.redis.pay.RedisPayKey;
import com.lzlk.base.result.Result;
import com.lzlk.base.utils.http.HttpClientUtil;
import com.lzlk.base.utils.sign.SginUtils;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.mysql.manager.payment.PaymentGoodsOrderManager;
import com.lzlk.mysql.manager.payment.PaymentOrderManager;
import com.lzlk.nosql.redis.service.RedisService;
import com.lzlk.payment.common.PaymentSysConstants;
import com.lzlk.payment.exception.PaymentException;
import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDo;
import com.lzlk.dao.mybatis.payment.dto.PayNoticeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/9 01 30
 * @Author: 邻座旅客
 */
@Slf4j
@Service
public class WeChatCallbackServiceImpl implements WeChatCallbackService {

    @Resource
    private PaymentGoodsOrderManager payGoodsOrderManager;

    @Resource
    private PaymentOrderManager payOrderManager;

    @Resource
    private RedisService redisService;

    @Value("${we.chat.pay.business.notice.url}")
    private String noticePayUrl;

    @Value("${we.chat.official.account.app.id}")
    private String officialAccountAppId;

    @Value("${we.chat.pay.sign}")
    private String sign;


    @Override
    public String callback(Map<String, String> map) {
        String payNoticeDtoKey = "";
        log.info(" ------------> 微信回调 result_data = {} ", map.toString());
        if (!map.get("result_code").equals(PaymentSysConstants.WE_CHAT_PAY_RESPONSE_SUCCESS)) {
            return PaymentSysConstants.WE_CHAT_PAY_RESPONSE_FAIL;
        }
        String appId = map.get("appid");

        log.info("appid : " + appId);

        if (StringUtils.isEmpty(appId)) {
            throw new PaymentException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        String secret = null;
        if (appId.equals(officialAccountAppId)) {
            secret = sign;
        }
        if (StringUtils.isEmpty(secret)) {
            throw new PaymentException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        if (!SginUtils.isSignatureValid(map, secret)) {
            throw new PaymentException(PublicExceptionCodeEnum.EX_ILLEGAL_REQUEST.getCode());
        }
        String localTradeNo = map.get("out_trade_no");
        PaymentGoodsOrderDo goodsOrderDo = payGoodsOrderManager.findById(localTradeNo);
        if (goodsOrderDo == null) {
            return PaymentSysConstants.WE_CHAT_PAY_RESPONSE_FAIL;
        }
        PaymentOrderDo payOrderDo = payOrderManager.findById(goodsOrderDo.getPayOrderId());
        if (payOrderDo == null) {
            return PaymentSysConstants.WE_CHAT_PAY_RESPONSE_FAIL;
        }
        // 只要双方订单不是"已创建",则返回"支付成功"
        if (!PayGoodsOrderStatusEnums.CREATE.getStatus().equals(goodsOrderDo.getStatus()) && !PayOrderStatusEnums.CREATE.getStatus().equals(payOrderDo.getStatus())) {
            log.info("---------- 返回支付成功！ ----------");
            return PaymentSysConstants.WE_CHAT_PAY_RESPONSE_SUCCESS;
        }
        // 修改商户订单状态
        payOrderDo.setTransactionId(map.get("transaction_id"));
        payOrderDo.setStatus(PayOrderStatusEnums.PAY_SUCCESS.getStatus());
        payOrderDo.setNotifyCount(payOrderDo.getNotifyCount() + 1);
        payOrderDo.setLastNotifyTime(new Date());
        payOrderDo.setSuccessTime(new Date());
        payOrderManager.update(payOrderDo);
        // 修改商品订单状态
        goodsOrderDo.setStatus(PayGoodsOrderStatusEnums.PAY_SUCCESS.getStatus());
        payGoodsOrderManager.update(goodsOrderDo);
        payNoticeDtoKey = RedisPayKey.getPayNoticeRedisKey(PayChannelEnums.WE_CHAT_H5.getId(), Long.valueOf(localTradeNo));
        log.info("============= 用户 {} 支付成功 ============= ", goodsOrderDo.getUserId());
        log.info("============= 微信订单号 {} ============= ", goodsOrderDo.getPayOrderId());
        log.info("============= 本地订单号 {} ============= ", localTradeNo);
        PayNoticeDto payNoticeDto = PayNoticeDto.builder().payUserId(goodsOrderDo.getUserId()).payChannel(goodsOrderDo.getChannelId())
                .payTime(System.currentTimeMillis()).mchTradeNo(goodsOrderDo.getPayOrderId())
                .localTradeNo(goodsOrderDo.getId()).build();
        // 把支付订单数存入redis
        redisService.set(payNoticeDtoKey, payNoticeDto, RedisPayKey.OUT_TIME);
        // 通知业务系统订单已经完成
        String businessResult = HttpClientUtil.doGet(noticePayUrl + "?payChannel=" + goodsOrderDo.getChannelId() + "&localTradeNo=" + localTradeNo);
        Result resultDo = JSONObject.parseObject(businessResult, Result.class);
        if (resultDo == null) {
            return PaymentSysConstants.WE_CHAT_PAY_RESPONSE_FAIL;
        }
        // 通知微信这个订单已经完成
        if (resultDo.getCode().equals(PublicExceptionCodeEnum.SUCCESS.getCode()) ||
                PublicExceptionCodeEnum.EX_EXCESSIVE_ATTEMPTS.getCode().equals(resultDo.getCode())) {
            return PaymentSysConstants.WE_CHAT_PAY_RESPONSE_SUCCESS;
        } else {
            // 修改商户订单状态
            payOrderDo.setNotifyCount(Objects.isNull(payOrderDo.getNotifyCount()) ? 1 : payOrderDo.getNotifyCount() + 1);
            payOrderDo.setLastNotifyTime(new Date());
            payOrderManager.update(payOrderDo);
            return PaymentSysConstants.WE_CHAT_PAY_RESPONSE_FAIL;
        }
    }

    public static void main(String[] args) {
        String key = "";
        key = RedisPayKey.getPayNoticeRedisKey(PayChannelEnums.WE_CHAT_OFFICIAL_ACCOUNT.getId(), 366626114996670464L);
        System.out.println(key);

    }
}
