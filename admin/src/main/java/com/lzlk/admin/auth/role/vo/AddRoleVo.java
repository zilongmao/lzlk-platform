package com.lzlk.admin.auth.role.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 20:46
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleVo implements Serializable {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 用户权重 1-99,不能高于用户最高权重
     */
    @NotNull(message = "权重不能为空")
    @Range(min = 1,max = 99,message = "权重不合法")
    private Integer roleWeight;
}
