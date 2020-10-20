package com.lzlk.mysql.manager.payment.impl;

import com.lzlk.base.enums.pay.PayOrderStatusEnums;
import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDoExample;
import com.lzlk.dao.mybatis.payment.mapper.PaymentOrderMapper;
import com.lzlk.mysql.manager.payment.PaymentOrderManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/09/05 17:46
 * @Created by 湖南爱豆
 */
@Service
public class PaymentOrderManagerImpl implements PaymentOrderManager {

    @Resource
    private PaymentOrderMapper paymentOrderMapper;

    @Override
    public boolean insert(PaymentOrderDo payOrderDo) {
        payOrderDo.setStatus(PayOrderStatusEnums.CREATE.getStatus());
        payOrderDo.setCreateTime(new Date());
        payOrderDo.setLastModifyTime(new Date());
        return paymentOrderMapper.insert(payOrderDo) > 0;
    }

    @Override
    public boolean update(PaymentOrderDo payOrderDo) {
        payOrderDo.setLastModifyTime(new Date());
        return paymentOrderMapper.updateByPrimaryKey(payOrderDo) > 0;
    }

    @Override
    public PaymentOrderDo findById(String id) {
        return paymentOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean checkIsConsume(String id) {
        PaymentOrderDoExample example = new PaymentOrderDoExample();
        PaymentOrderDoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andStatusEqualTo(PayOrderStatusEnums.FINISHED.getStatus());
        return paymentOrderMapper.countByExample(example) > 0;
    }
}
