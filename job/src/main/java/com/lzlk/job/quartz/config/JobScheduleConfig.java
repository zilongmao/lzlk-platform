package com.lzlk.job.quartz.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lzlk.job.quartz.factory.AutoWiringSpringBeanJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * 调度配置
 * @author 邻座旅客
 *
 */
@Configuration
public class JobScheduleConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        //设置线程池
        factory.setTaskExecutor(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2,
                new ThreadFactoryBuilder().setNameFormat("schedule-factory-thread-%d").build()));
        // factory.setQuartzProperties(quartzProperties());
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        factory.setJobFactory(jobFactory);
        return factory;
    }

    // @Bean
    // public Properties quartzProperties() throws IOException {
    // PropertiesFactoryBean propertiesFactoryBean = new
    // PropertiesFactoryBean();
    // propertiesFactoryBean.setLocation(new
    // ClassPathResource("/quartz.properties"));
    // propertiesFactoryBean.afterPropertiesSet();
    // return propertiesFactoryBean.getObject();
    // }
}