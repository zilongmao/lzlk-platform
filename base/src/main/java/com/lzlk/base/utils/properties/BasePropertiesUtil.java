package com.lzlk.base.utils.properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/3/18 01 42
 * @Author: 邻座旅客
 */
public class BasePropertiesUtil extends PropertyPlaceholderConfigurer {

    private static Properties props; // 存取properties配置文件key-value结果

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        BasePropertiesUtil.props = props;
    }

    /**
     * 获取value
     *
     * @param key
     * @return
     * @Description
     * @author 张国栋
     */
    public static String getPropertyValue(String key) {
        return props.getProperty(key);
    }

}
