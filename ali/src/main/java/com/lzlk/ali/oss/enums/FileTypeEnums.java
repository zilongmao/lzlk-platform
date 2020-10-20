package com.lzlk.ali.oss.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/4/9 21:17
 * @Created by 湖南达联
 **/
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FileTypeEnums {
    IMG("img","图片"),
    ;
    private String type;

    private String desc;
}
