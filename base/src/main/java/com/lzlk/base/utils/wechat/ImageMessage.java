package com.lzlk.base.utils.wechat;

import com.lzlk.base.utils.spring.SpringCglibBeanUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 邻座旅客
 * @Description TODO 图片
 * @Date 2019/12/10 15:20
 * @Created by 湖南达联
 */
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageMessage extends WxMessage {

    private Media image;

    public static class Media {
        private String mediaId;
        public Media(String mediaId) {
            this.mediaId = mediaId;
        }

    }
    public static ImageMessage adapt(WxMessage msg) {
        ImageMessage imageMessage = SpringCglibBeanUtils.convert(msg, ImageMessage.class);
        return imageMessage;
    }
}
