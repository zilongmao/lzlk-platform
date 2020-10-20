package com.lzlk.main.business.sign.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO: 登录返回值VO
 *
 * @Created by 湖南爱豆
 * @Date 2020/3/31 16 50
 * @Author: 邻座旅客
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登陆返回数据")
public class SignDataVo implements Serializable {

    @ApiModelProperty("返回的token")
    private String token;

    @ApiModelProperty("是否是新用户")
    private boolean isNewUser;
}
