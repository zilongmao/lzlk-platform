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
 * @Date 2019/6/20 16:37
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdatePassWordVo implements Serializable {

    @NotBlank(message = "老密码不能为空")
    @Size(min = 6,max = 20,message = "老密码长度不和法")
    private String oldPassWord;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 6,max = 20,message = "新密码长度不和法")
    private String newPassWord;
}
