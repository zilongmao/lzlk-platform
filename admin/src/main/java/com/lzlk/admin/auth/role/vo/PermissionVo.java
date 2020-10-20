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
 * @Date 2019/6/21 15:41
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVo implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限路径
     */
    private String permissionPath;

    /**
     * 权限类型，1 菜单 2 按钮
     */
    private Integer permissionType;

    /**
     * 排序值
     */
    private Integer sortNum;

    /**
     * 是否删除
     */
    private Integer isDelete;

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


    private String webUrl;
    

    /**
     * 父id，如果是第一级，id为0
     */
    private Long pid;
}
