package com.lzlk.nosql.properties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 02
 * @Author: 邻座旅客
 */
@Component("nosql")
@PropertySource(value={"file:${DemoConfig}/db.properties"},encoding = "UTF-8")
public class PropertiesSourceConfig {
}
