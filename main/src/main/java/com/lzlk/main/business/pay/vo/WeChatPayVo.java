package com.lzlk.main.business.pay.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/9/9 11 52
 * @Author: 邻座旅客
 */
@ApiModel("微信小程序支付Vo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeChatPayVo {

    @NotNull
    @Min(1)
    @ApiModelProperty("价格")
    private Long money;

    @NotNull
    @ApiModelProperty("支付类型")
    private Long payId;

    @NotBlank
    @ApiModelProperty("用户openId")
    private String openId;

}
