package com.lzlk.dao.mybatis.payment.mapper;

import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDoExample;
import java.util.List;

import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentGoodsOrderDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentGoodsOrderMapper {
    long countByExample(PaymentGoodsOrderDoExample example);

    int deleteByExample(PaymentGoodsOrderDoExample example);

    int deleteByPrimaryKey(String id);

    int insert(PaymentGoodsOrderDo record);

    int insertSelective(PaymentGoodsOrderDo record);

    List<PaymentGoodsOrderDo> selectByExample(PaymentGoodsOrderDoExample example);

    PaymentGoodsOrderDo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PaymentGoodsOrderDo record, @Param("example") PaymentGoodsOrderDoExample example);

    int updateByExample(@Param("record") PaymentGoodsOrderDo record, @Param("example") PaymentGoodsOrderDoExample example);

    int updateByPrimaryKeySelective(PaymentGoodsOrderDo record);

    int updateByPrimaryKey(PaymentGoodsOrderDo record);
}