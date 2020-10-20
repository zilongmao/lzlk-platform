package com.lzlk.base.utils.wechat;

import com.lzlk.base.utils.spring.SpringCglibBeanUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Classname 文本信息类 继承自基类：
 * @Created by 湖南达联
 * @Date 2019/9/2 15 56
 * @Description:
 * @Author: 资龙茂
 */

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage extends WxMessage {

    // 文本消息内容
    private String Content;

    //用来把基类的属性值复制给子类
    public static TextMessage adapt(WxMessage msg){
        TextMessage textMessage = SpringCglibBeanUtils.convert(msg, TextMessage.class);
        return textMessage;
    }

}
