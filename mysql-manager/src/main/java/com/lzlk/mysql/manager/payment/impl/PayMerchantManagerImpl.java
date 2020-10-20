package com.lzlk.mysql.manager.payment.impl;

import com.lzlk.base.enums.math.NumberEnums;
import com.lzlk.base.utils.collection.CollectionUtils;
import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDoExample;
import com.lzlk.dao.mybatis.payment.mapper.PaymentMerchantInfoMapper;
import com.lzlk.mysql.manager.payment.PayMerchantManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 30
 * @Author: 邻座旅客
 */
@Slf4j
@Service
public class PayMerchantManagerImpl implements PayMerchantManager {

    @Resource
    private PaymentMerchantInfoMapper paymentMerchantInfoMapper;

    @Override
    public PaymentMerchantInfoDo findMchByType(String mchType) {
        PaymentMerchantInfoDoExample example = new PaymentMerchantInfoDoExample();
        PaymentMerchantInfoDoExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(mchType).andStatusEqualTo(NumberEnums.ONE.getNumber());
        List<PaymentMerchantInfoDo> merchantInfoDos = paymentMerchantInfoMapper.selectByExample(example);
        return CollectionUtils.isEmpty(merchantInfoDos) ? null : merchantInfoDos.get(0);
    }
}
