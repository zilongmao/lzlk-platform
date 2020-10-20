package com.lzlk.ali.oss.enums;


/**
 * @Classname 邻座旅客
 * @Description 阿里云文件夹枚举
 * @Date 2019/3/15 14:53
 * @Created by 湖南达联
 */

public enum AliyunOssDirEnums {

    HEAD_IMG_DIR_NAME("personPhoto/face_img", "用户头像文件夹"),;


    private String dirName;


    private String description;

    AliyunOssDirEnums(String dirName, String description) {
        this.dirName = dirName;
        this.description = description;
    }

    public String getDirName() {
        return dirName;
    }

    public String getDescription() {
        return description;
    }

}
