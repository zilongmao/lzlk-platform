package com.lzlk.main.business.service.config;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/8/17 10 43
 * @Author: 邻座旅客
 */
public interface SystemConfigService {

    /**
     * 根据key查找对应的值（String）
     * @param key
     * @return
     */
    String getStrConfigByKey(String key);

    /**
     * 根据key查找对应的值（int）
     * @param key
     * @return
     */
    Integer getIntegerConfigByKey(String key);
}
