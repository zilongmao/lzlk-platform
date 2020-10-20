package com.lzlk.mysql.manager.payment.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzlk.base.constants.BaseConstants;
import com.lzlk.base.enums.pay.PayGoodsOrderStatusEnums;
import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDoExample;
import com.lzlk.dao.mybatis.payment.mapper.PaymentGoodsOrderMapper;
import com.lzlk.mysql.manager.payment.PaymentGoodsOrderManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/09/04 10:42
 * @Created by 湖南爱豆
 */
@Service
public class PaymentGoodsOrderManagerImpl implements PaymentGoodsOrderManager {

    @Resource
    private PaymentGoodsOrderMapper paymentGoodsOrderMapper;

    @Override
    public boolean insert(PaymentGoodsOrderDo payGoodsOrderDo) {
        payGoodsOrderDo.setCreateTime(new Date());
        payGoodsOrderDo.setLastModifyTime(new Date());
        payGoodsOrderDo.setStatus(PayGoodsOrderStatusEnums.CREATE.getStatus());
        return paymentGoodsOrderMapper.insert(payGoodsOrderDo) > 0;
    }

    @Override
    public boolean update(PaymentGoodsOrderDo payGoodsOrderDo) {
        payGoodsOrderDo.setLastModifyTime(new Date());
        return paymentGoodsOrderMapper.updateByPrimaryKey(payGoodsOrderDo) > 0;
    }

    @Override
    public PaymentGoodsOrderDo findById(String id) {
        return paymentGoodsOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean checkIsConsume(String id) {
        PaymentGoodsOrderDoExample example = new PaymentGoodsOrderDoExample();
        PaymentGoodsOrderDoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andStatusEqualTo(PayGoodsOrderStatusEnums.FINISHED.getStatus());
        return paymentGoodsOrderMapper.countByExample(example) > 0;
    }


    @Override
    public List<PaymentGoodsOrderDo> findPaymentGoodsOrderByDay(Long starTime, Long endTime,Integer status) {
        PaymentGoodsOrderDoExample example = new PaymentGoodsOrderDoExample();
        PaymentGoodsOrderDoExample.Criteria criteria = example.createCriteria();
        if (starTime!= null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(new Date(starTime));
        }
        if (endTime != null){
            criteria.andCreateTimeLessThanOrEqualTo(new Date(endTime));
        }
        criteria.andAmountIsNotNull();
        criteria.andStatusEqualTo(status);
        return paymentGoodsOrderMapper.selectByExample(example);
    }

    @Override
    public List<PaymentGoodsOrderDo> findPaymentGoodsAll(Integer pagerNo, Long userId, Integer payChannel, Integer payStatus, Long starTime, Long endTime) {
        PaymentGoodsOrderDoExample example = new PaymentGoodsOrderDoExample();
        PaymentGoodsOrderDoExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (payChannel != null){
            criteria .andChannelIdEqualTo(payChannel.toString());
        }
        if (payStatus != null){
            criteria .andStatusEqualTo(payStatus);
        }
        if (starTime != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(new Date(starTime));
        }
        if (endTime != null) {
            criteria.andCreateTimeLessThanOrEqualTo(new Date(endTime));
        }
        PageHelper.startPage(pagerNo, BaseConstants.DEFAULT_PAGE_LIMIT);
        List<PaymentGoodsOrderDo> payGoodsOrderDos = paymentGoodsOrderMapper.selectByExample(example);
        PageInfo<PaymentGoodsOrderDo> pageInfo = new PageInfo<>(payGoodsOrderDos);
        return pageInfo == null ? new ArrayList<>() : pageInfo.getList();
    }

    @Override
    public Long getTransactionSumAmount(Integer payChannel, Integer payStatus, Long userId, Long starTime, Long endTime) {
        Long sum = 0L;
        PaymentGoodsOrderDoExample example = new PaymentGoodsOrderDoExample();
        PaymentGoodsOrderDoExample.Criteria criteria = example.createCriteria();
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (payChannel != null){
            criteria .andChannelIdEqualTo(payChannel.toString());
        }
        if (payStatus != null){
            criteria .andStatusEqualTo(payStatus);
        }
        if (starTime != null) {
            criteria.andCreateTimeGreaterThanOrEqualTo(new Date(starTime));
        }
        if (endTime != null) {
            criteria.andCreateTimeLessThanOrEqualTo(new Date(endTime));
        }
        List<PaymentGoodsOrderDo> payGoodsOrderDos = paymentGoodsOrderMapper.selectByExample(example);
        if (payGoodsOrderDos.size() > 0) {
            for (PaymentGoodsOrderDo payGoodsOrderDo : payGoodsOrderDos) {
                sum += payGoodsOrderDo.getAmount();
            }
        }
        return sum;
    }


}
