package com.lzlk.mysql.manager.payment;


import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDo;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/09/05 17:45
 * @Created by 湖南爱豆
 */
public interface PaymentOrderManager {

    /**
     * 新增订单(下单)
     * @param payOrderDo
     * @return
     */
    boolean insert(PaymentOrderDo payOrderDo);

    /**
     * 修改订单状态
     * @param payOrderDo
     * @return
     */
    boolean update(PaymentOrderDo payOrderDo);

    /**
     * 根据商户订单号查询订单
     * @param id
     * @return
     */
    PaymentOrderDo findById(String id);

    /**
     * 查询订单是否已被消费
     * @param id
     * @return
     */
    boolean checkIsConsume(String id);
}
