package com.lzlk.main.business.pay.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/4/6 16 04
 * @Author: 邻座旅客
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficialAccountPayDto {

    @ApiModelProperty("支付金额")
    private Long amount;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("用户openID")
    private String openId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("本地订单号")
    private String localOrderId;

    @ApiModelProperty("渠道ID")
    private Integer channelId;

    @ApiModelProperty("商户ID")
    private String mchId;

    @ApiModelProperty("商品数量")
    private Long goodsCount;
}
