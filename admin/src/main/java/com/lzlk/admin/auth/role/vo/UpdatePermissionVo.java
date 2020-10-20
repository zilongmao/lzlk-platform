package com.lzlk.admin.auth.role.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/7/4 17:52
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePermissionVo{

    private Long id;
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    private String permissionName;

    /**
     * 权限描述
     */
    private String description;


    private String webUrl;

    /**
     * 权限路径
     */
    private String permissionPath;

    /**
     * 权限类型，1 菜单 2 按钮
     */
    @NotNull(message = "权限类型不能为空")
    @Range(min = 1,max = 2,message = "权限类型不正确")
    private Integer permissionType;

    @NotNull(message = "父ID不能为空,如果是一级菜单为0")
    private Long pid;

    private Integer sortNum;

    @NotNull(message = "显示隐藏不能为空")
    @Range(min = 0,max = 1,message = "显示隐藏不能为空")
    private Integer isDelete;//显示隐藏
}
