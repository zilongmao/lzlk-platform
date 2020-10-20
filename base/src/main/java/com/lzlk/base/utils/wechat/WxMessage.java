package com.lzlk.base.utils.wechat;

import lombok.*;

/**
 * @Classname 微信公众号消息的基类
 * @Created by 湖南达联
 * @Date 2019/9/2 15 55
 * @Description:
 * @Author: 资龙茂
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxMessage {

    /** 开发者微信号 */
    private String ToUserName;

    /** 发送方帐号（一个OpenID）*/
    private String FromUserName;

    /** 消息创建时间 （整型） */
    private long CreateTime;

    /** 消息类型（text/image/location/link） */
    private String MsgType;
}
