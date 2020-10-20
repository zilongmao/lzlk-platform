package com.lzlk.admin.auth.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/19 14:46
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoVo implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4,max = 20,message = "用户名不合法")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4,max = 20,message = "密码不和法")
    private String passWord;
}
