package com.lzlk.admin.auth.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/20 15:05
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserVo implements Serializable {


    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户账号
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String nickName;

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
     * 最后一次修改人id
     */
    private Long updateUserId;


    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 上一次登陆时间
     */
    private Date loginTime;

    /**
     * 最后一次登陆的IP
     */
    private String loginIp;
}
