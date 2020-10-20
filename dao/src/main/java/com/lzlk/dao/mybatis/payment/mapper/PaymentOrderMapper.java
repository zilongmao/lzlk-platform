package com.lzlk.dao.mybatis.payment.mapper;

import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDoExample;
import java.util.List;

import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentOrderDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderMapper {
    long countByExample(PaymentOrderDoExample example);

    int deleteByExample(PaymentOrderDoExample example);

    int deleteByPrimaryKey(String id);

    int insert(PaymentOrderDo record);

    int insertSelective(PaymentOrderDo record);

    List<PaymentOrderDo> selectByExample(PaymentOrderDoExample example);

    PaymentOrderDo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PaymentOrderDo record, @Param("example") PaymentOrderDoExample example);

    int updateByExample(@Param("record") PaymentOrderDo record, @Param("example") PaymentOrderDoExample example);

    int updateByPrimaryKeySelective(PaymentOrderDo record);

    int updateByPrimaryKey(PaymentOrderDo record);
}