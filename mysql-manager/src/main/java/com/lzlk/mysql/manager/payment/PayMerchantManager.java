package com.lzlk.mysql.manager.payment;


import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDo;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 28
 * @Author: 邻座旅客
 */
public interface PayMerchantManager {

    /**
     * 根据商户类型寻找有效商户
     * @param mchType
     * @return
     */
    PaymentMerchantInfoDo findMchByType(String mchType);
}
