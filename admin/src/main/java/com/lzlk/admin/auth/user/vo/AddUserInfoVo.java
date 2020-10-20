package com.lzlk.admin.auth.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 邻座旅客
 * @Description 添加用户VO
 * @Date 2019/6/20 15:08
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserInfoVo implements Serializable {

    /**
     * 用户账号
     */
    @Length(min = 4)
    @NotNull
    private String loginName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String password;
}
