package com.lzlk.dao.mybatis.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO: 支付传输对象
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 04
 * @Author: 邻座旅客
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayNoticeDto implements Serializable{

    /** 支付用户id */
    private Long payUserId;

    /** 本地订单id */
    private String localTradeNo;

    /** 支付渠道 */
    private String payChannel;

    /** 支付订单号 */
    private String mchTradeNo;

    /** 支付时间 */
    private Long payTime;
}
