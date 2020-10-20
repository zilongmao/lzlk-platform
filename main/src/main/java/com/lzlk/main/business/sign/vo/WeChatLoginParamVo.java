package com.lzlk.main.business.sign.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/4/23 16:05
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeChatLoginParamVo implements Serializable {

    @NotBlank(message = "appType参数错误")
    private String appType;//客户端类型  ios/android/h5/等

    @NotBlank(message = "deviceNumber参数错误")
    private String deviceNumber;//手机设备号

    @NotBlank(message = "loginType参数错误")
    private String loginType;//登陆方式

    @NotBlank(message = "nickname参数错误")
    private String nickname;//昵称

    @NotBlank(message = "phoneName参数错误")
    private String phoneName;//手机型号

    @NotBlank(message = "userImgUrl参数错误")
    private String userImgUrl;//用户头像

    @NotBlank(message = "weChatOpenId参数错误")
    private String weChatOpenId;//小程序OpenId

//    @NotBlank(message = "参数错误")
//    private String weChatUnionId;//微信UnionId

    @NotBlank(message = "umengChannel参数错误")
    private String umengChannel;//用户的渠道标识 如果没有，默认为klfsh



}
