package com.lzlk.job.property;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/08/18 17:37
 * @Created by 湖南爱豆
 */
@Component("job")
@PropertySource(value={"file:${DemoConfig}/job.properties"})
public class PropertySourceConfig {
}
