package com.lzlk.main.business.pay.service.impl;

import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.enums.math.NumberEnums;
import com.lzlk.base.enums.pay.PayChannelEnums;
import com.lzlk.base.enums.pay.PayGoodsOrderStatusEnums;
import com.lzlk.base.enums.pay.PayMerchantTypeEnums;
import com.lzlk.base.enums.pay.PayOrderStatusEnums;
import com.lzlk.base.exception.enums.PublicExceptionCodeEnum;
import com.lzlk.base.lock.RedisLockKey;
import com.lzlk.base.plugin.snowflakeid.service.ISnowflakeIdService;
import com.lzlk.base.redis.pay.RedisPayKey;
import com.lzlk.base.result.Result;
import com.lzlk.base.result.ResultFactory;
import com.lzlk.base.utils.request.RequestUtil;
import com.lzlk.base.utils.string.StringUtils;
import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDo;
import com.lzlk.dao.mybatis.payment.dto.PayNoticeDto;
import com.lzlk.main.business.pay.dto.OfficialAccountPayDto;
import com.lzlk.main.business.pay.service.WeChatPayService;
import com.lzlk.main.business.pay.util.WeChatPayUtil;
import com.lzlk.main.business.pay.vo.WeChatPayVo;
import com.lzlk.main.common.SystemConfigConstants;
import com.lzlk.main.exception.MainException;
import com.lzlk.main.exception.MainExceptionEnums;
import com.lzlk.mysql.manager.payment.PayMerchantManager;
import com.lzlk.mysql.manager.payment.PaymentGoodsOrderManager;
import com.lzlk.mysql.manager.payment.PaymentOrderManager;
import com.lzlk.nosql.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/9 11 55
 * @Author: 邻座旅客
 */
@Slf4j
@Service
public class WeChatPayServiceImpl implements WeChatPayService {

    @Value("${is.sandbox.server}")
    private String isSandboxServer;

    @Value("${we.chat.applet.app.id}")
    private String appletAppId;

    @Value("${we.chat.pay.pre.url}")
    private String preUrl;

    @Value("${we.chat.pay.callback.url}")
    private String callbackUrl;

    @Value("${we.chat.pay.business.notice.pay.url}")
    private String noticeUrl;

    @Value("${we.chat.pay.sign}")
    private String sign;

    @Resource
    private PayMerchantManager payMerchantManager;

    @Resource
    private ISnowflakeIdService iSnowflakeIdService;

    @Resource
    private PaymentGoodsOrderManager goodsOrderManager;

    @Resource
    private PaymentOrderManager payOrderManager;

    @Resource
    private RedisService redisService;

    @Override
    public Result<Object> payByWeChatApplets(Long userId, WeChatPayVo weChatPayVo, HttpServletRequest request) {
        // 校验是否存在有效商户
        PaymentMerchantInfoDo mch = payMerchantManager.findMchByType(PayMerchantTypeEnums.WE_CHAT_PAY.getDescribe());
        if (mch == null) {
            log.error("==================== 未找到有效商户: {} ====================", PayMerchantTypeEnums.WE_CHAT_PAY.getDescribe());
            throw new MainException(MainExceptionEnums.MAIN_MCH_NOT_FOUND.getCode());
        }
        OfficialAccountPayDto officialAccountPayDto = new OfficialAccountPayDto();
        officialAccountPayDto.setMchId(mch.getId());
        // 获取本地订单id
        Long orderId = iSnowflakeIdService.nextId();
        officialAccountPayDto.setUserId(userId);
        officialAccountPayDto.setLocalOrderId(orderId.toString());
        officialAccountPayDto.setGoodsCount(1L);
        officialAccountPayDto.setAmount(weChatPayVo.getMoney());
        officialAccountPayDto.setGoodsId(weChatPayVo.getPayId());
        officialAccountPayDto.setOpenId(weChatPayVo.getOpenId());
        // 渠道ID为公众号支付
        officialAccountPayDto.setChannelId(PayChannelEnums.WE_CHAT_APPLET.getId());
        // 校验是否为沙盒环境
        if (Boolean.valueOf(isSandboxServer)) {
            officialAccountPayDto.setAmount(1L);
        }
        // 生成支付订单
        Map<String, String> preMap = WeChatPayUtil.getPreIdByApplets(officialAccountPayDto,
                request, mch, noticeUrl, preUrl, sign, appletAppId);

        this.initWeChatPayOrder(preMap, request, weChatPayVo.getPayId(), officialAccountPayDto);
        return ResultFactory.success(preMap);
    }

    private void initWeChatPayOrder(Map<String, String> preMap, HttpServletRequest request, Long goodsId,
                                    OfficialAccountPayDto officialAccountPayDto) {
        if (StringUtils.isBlank(preMap.get("preId"))) {
            log.error("==================== 订单生成失败：preId == {} ====================", preMap.get("preId"));
            throw new MainException(MainExceptionEnums.EX_WE_CHAT_PRE_ORDER_ERROR.getCode());
        }
        // 系统内生成预下单订单(商品订单表)
        PaymentGoodsOrderDo payGoodsOrderDo = new PaymentGoodsOrderDo();
        payGoodsOrderDo.setId(officialAccountPayDto.getLocalOrderId());
        payGoodsOrderDo.setGoodsId(goodsId.toString());
        payGoodsOrderDo.setGoodsName(goodsId.toString());
        payGoodsOrderDo.setAmount(officialAccountPayDto.getAmount());
        payGoodsOrderDo.setUserId(officialAccountPayDto.getUserId());
        payGoodsOrderDo.setPayOrderId(preMap.get("preId"));
        payGoodsOrderDo.setChannelId(PayChannelEnums.WE_CHAT_APPLET.getId().toString());
        payGoodsOrderDo.setChannelUserId(officialAccountPayDto.getOpenId());
        payGoodsOrderDo.setGoodsCount(1L);
        boolean goodsOrderFlag = goodsOrderManager.insert(payGoodsOrderDo);
        if (!goodsOrderFlag) {
            log.error("==================== 订单生成失败：localTradeNo == {} ======================", payGoodsOrderDo.getId());
            throw new MainException(MainExceptionEnums.EX_WE_CHAT_PRE_ORDER_ERROR.getCode());
        }
        // 系统内生成预下单订单(支付订单表)
        PaymentOrderDo payOrderDo = new PaymentOrderDo();
        payOrderDo.setId(preMap.get("preId"));
        payOrderDo.setMerchantId(officialAccountPayDto.getMchId());
        payOrderDo.setMerchantOrderNumber(officialAccountPayDto.getLocalOrderId());
        payOrderDo.setChannelId(officialAccountPayDto.getChannelId().toString());
        payOrderDo.setAmount(officialAccountPayDto.getAmount());
        payOrderDo.setGoodsName(goodsId.toString());
        payOrderDo.setGoodsInfo(goodsId.toString());
        payOrderDo.setClientIp(RequestUtil.getIPAddress(request));
        log.info("ip = {}", payOrderDo.getClientIp());
        payOrderDo.setDevice(RequestUtil.getMobileDevice(request));
        payOrderDo.setNotifyCount(NumberEnums.ZERO.getNumber());
        boolean payOrderFlag = payOrderManager.insert(payOrderDo);
        if (!payOrderFlag) {
            log.error("==================== 订单生成失败：mchTradeNo == {} ======================", payOrderDo.getId());
            throw new MainException(MainExceptionEnums.EX_WE_CHAT_PRE_ORDER_ERROR.getCode());
        }

        log.info("==================== 用户 {} 下单成功 ==================== ", officialAccountPayDto.getUserId());
        log.info("==================== localTradeNo: {} ====================", officialAccountPayDto.getLocalOrderId());
        log.info("==================== mchTradeNo: {} ====================", payOrderDo.getId());
    }

    @Override
    public Result<Object> respondPayNotice(Integer payChannel, String localTradeNo) {
        // 获取redis key
        String payNoticeDtoKey = RedisPayKey.getPayNoticeRedisKey(payChannel, Long.valueOf(localTradeNo));
        String payLockKey = null;
        try {
            PayNoticeDto payNoticeDto = redisService.get(payNoticeDtoKey);
            //如果没有到通知，则是否是支付系统异常或者是 有异常调用
            if (!RequestUtil.isFieldNull(payNoticeDto)) {
                log.error("================== 发现异常调用 localTradeNo: {}==================", localTradeNo);
                throw new MainException(PublicExceptionCodeEnum.EX_OPERATION_FAIL.getCode());
            }
            if (payOrderManager.checkIsConsume(payNoticeDto.getMchTradeNo())) {
                log.error("================== 订单已被消费 mchTradeNo: {}==================", payNoticeDto.getMchTradeNo());
                throw new MainException(PublicExceptionCodeEnum.EX_EXCESSIVE_ATTEMPTS.getCode());
            }
            PaymentGoodsOrderDo goodsOrderDo = goodsOrderManager.findById(localTradeNo);
            PaymentOrderDo payOrderDo = payOrderManager.findById(goodsOrderDo.getPayOrderId());
            payLockKey = RedisLockKey.builderRedisKey(BaseConstants.PAY_NOTICE_LOCK_PACKAGE_KEY, payNoticeDto.getPayChannel(), localTradeNo);

            // 这里加个分布式锁防止并发内被穿透
            boolean isLock = redisService.setNX(payLockKey, RedisLockKey.DEFAULT_VALUE, SystemConfigConstants.PAY_SYS_NOTICE_LOCK_TIME);
            if (!isLock) {
                log.error("==================== payLockKey: {} , isLock : ==================== ", payLockKey, isLock );
                throw new MainException(PublicExceptionCodeEnum.EX_EXCESSIVE_ATTEMPTS.getCode());
            }

            // ----------------- 这里写业务代码 -----------------

            // 订单处理完毕
            goodsOrderDo.setStatus(PayGoodsOrderStatusEnums.FINISHED.getStatus());
            boolean goodsOrderIsOver = goodsOrderManager.update(goodsOrderDo);
            if (!goodsOrderIsOver) {
                log.error("==================== 商品订单结束异常：{} ====================", goodsOrderDo.getId());
            }
            payOrderDo.setStatus(PayOrderStatusEnums.FINISHED.getStatus());
            boolean payOrderIsOver = payOrderManager.update(payOrderDo);
            if (!payOrderIsOver) {
                log.error("==================== 支付订单结束异常：{} ====================", payOrderDo.getId());
            }
        } finally {
            redisService.del(payNoticeDtoKey);
            if (StringUtils.isNotBlank(payLockKey)) {
                boolean flag = redisService.del(payLockKey);
                log.info("==================== payLockKey 删除：{} ====================", flag);
            }
        }
        return ResultFactory.success();
    }

}
