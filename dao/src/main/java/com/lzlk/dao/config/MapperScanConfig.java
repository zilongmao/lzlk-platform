package com.lzlk.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 01
 * @Author: 邻座旅客
 */
@Component
@MapperScan(basePackages = "com.lzlk.dao.mybatis.*.mapper")
public class MapperScanConfig {
}
