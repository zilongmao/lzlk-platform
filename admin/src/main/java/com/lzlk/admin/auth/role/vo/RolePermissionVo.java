package com.lzlk.admin.auth.role.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/24 14:51
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionVo implements Serializable {

    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @NotNull(message = "权限id不能为空")
    private List<Long> permissionIdList;
}
