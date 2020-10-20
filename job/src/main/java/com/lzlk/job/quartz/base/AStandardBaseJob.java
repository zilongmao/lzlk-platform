package com.lzlk.job.quartz.base;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 【标题】: 标准job的父类
 * 【描述】:
 * 【版权】: 湖南达联
 * 【作者】: 邻座旅客
 * 【时间】: 2018-03-19 17:19
 */
public abstract class AStandardBaseJob extends QuartzJobBean {

    /**
     * 初始化appid
     *
     * @Description
     * @author 邻座旅客
     * @return appId
     */
    public abstract String initAppId();

    /**
     * 初始化Cron表达式key
     *
     * @Description
     * @author 邻座旅客
     * @return cronKey
     */
    public abstract String initCronKey();

    /**
     * 初始化Cron表达式值
     *
     * @Description
     * @author 邻座旅客
     * @return 表达式值
     */
    public abstract String initCronValue();

    /**
     * 初始化有效标志
     *
     * @Description
     * @author 邻座旅客
     * @return 表达式值
     */
    public abstract int initValidSign();
}
