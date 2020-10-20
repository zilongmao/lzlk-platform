package com.lzlk.admin.auth.role.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program:hndl-platform
 * @description:TODO 无法命名的类
 * @author: 邻座旅客
 * @create: 2019/12/20 14:21
 * @Created by湖南达联
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionVos extends PermissionVo {
    /**
     * 自己包自己
     */
    private PermissionVos permissionVos;
}
