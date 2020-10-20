package com.lzlk.mysql.manager.payment;

import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDo;

import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/09/04 10:41
 * @Created by 湖南爱豆
 */
public interface PaymentGoodsOrderManager {

    /**
     * 新增订单(下单)
     * @param payGoodsOrderDo
     * @return
     */
    boolean insert(PaymentGoodsOrderDo payGoodsOrderDo);

    /**
     * 修改订单状态
     * @param payGoodsOrderDo
     * @return
     */
    boolean update(PaymentGoodsOrderDo payGoodsOrderDo);

    /**
     * 根据本地订单号查询订单
     * @param id
     * @return
     */
    PaymentGoodsOrderDo findById(String id);

    /**
     * 查询订单是否已被消费
     * @param id
     * @return
     */
    boolean checkIsConsume(String id);

    /**
     * 获取某个时间段的成交信息
     * @param starTime
     * @param endTime
     * @return
     */
    List<PaymentGoodsOrderDo> findPaymentGoodsOrderByDay(Long starTime, Long endTime, Integer status);

    /**
     * 查询充值详细记录
     * @param pagerNo  页码
     * @param userId   用户id
     * @param payChannel 商家类型
     * @param payStatus  交易状态
     * @param starTime  开始时间
     * @param endTime   结束时间
     * @return
     */
    List<PaymentGoodsOrderDo>  findPaymentGoodsAll(Integer pagerNo,Long userId,Integer payChannel,Integer payStatus,Long starTime,Long endTime);

    /**
     * @param userId
     * @return 统计当前充值总金额
     */
    Long  getTransactionSumAmount(Integer payChannel, Integer payStatus,Long userId,Long starTime, Long endTime);
}
