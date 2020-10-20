package com.lzlk.admin.auth.role.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/21 14:20
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleVo {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotNull(message = "角色id不能为空")
    private List<Long> roleIdList;
}
