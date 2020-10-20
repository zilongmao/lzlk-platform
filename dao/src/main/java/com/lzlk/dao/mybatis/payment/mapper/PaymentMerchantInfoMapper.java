package com.lzlk.dao.mybatis.payment.mapper;

import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDoExample;
import java.util.List;

import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDo;
import com.lzlk.dao.mybatis.payment.bean.PaymentMerchantInfoDoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMerchantInfoMapper {
    long countByExample(PaymentMerchantInfoDoExample example);

    int deleteByExample(PaymentMerchantInfoDoExample example);

    int deleteByPrimaryKey(String id);

    int insert(PaymentMerchantInfoDo record);

    int insertSelective(PaymentMerchantInfoDo record);

    List<PaymentMerchantInfoDo> selectByExample(PaymentMerchantInfoDoExample example);

    PaymentMerchantInfoDo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PaymentMerchantInfoDo record, @Param("example") PaymentMerchantInfoDoExample example);

    int updateByExample(@Param("record") PaymentMerchantInfoDo record, @Param("example") PaymentMerchantInfoDoExample example);

    int updateByPrimaryKeySelective(PaymentMerchantInfoDo record);

    int updateByPrimaryKey(PaymentMerchantInfoDo record);
}