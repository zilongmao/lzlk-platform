package com.lzlk.admin.business.resources.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/05/06 14:52
 * @Created by 湖南爱豆
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Base64Vo")
public class Base64Vo implements Serializable {

    @NotNull(message = "资源不能为空!")
    @Length(message = "图片大小有误!")
    private String baseImg;
}
