package com.lzlk.admin.auth.role.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 20:58
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRoleVo implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户id
     */
    private Long createUserId;

    /**
     * 最后一次修改时间
     */
    private Date updateTime;

    /**
     * 最后一次修改用户id
     */
    private Long updateUserId;

    /**
     * 用户权重 1-99
     */
    private Integer roleWeight;

    /**
     * 是否删除
     */
    private Integer isDelete;
}
