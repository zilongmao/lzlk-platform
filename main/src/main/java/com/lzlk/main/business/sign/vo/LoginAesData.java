package com.lzlk.main.business.sign.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/5/10 17:31
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "三方登陆加密数据")
public class LoginAesData {

    @NotBlank(message = "数据不能为空")
    @ApiModelProperty(value = "加密后的数据",required = true)
    private String data;//加密后的数据
}
