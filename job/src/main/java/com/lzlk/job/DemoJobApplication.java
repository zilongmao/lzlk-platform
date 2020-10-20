package com.lzlk.job;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/08/18 16:25
 * @Created by 湖南爱豆
 */
@Slf4j
@EnableScheduling
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
@ComponentScans({
        @ComponentScan("com.lzlk.mysql"),
        @ComponentScan("com.lzlk.nosql"),
        @ComponentScan("com.lzlk.base"),
})
@MapperScan(basePackages = "com.lzlk.dao.mybatis.*.mapper")
@EnableAsync
@PropertySource(value={"file:${DemoConfig}/job.properties"}, ignoreResourceNotFound= true)
public class DemoJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJobApplication.class,args);
        log.info("============================ ProjectName - 定时任务 启动成功 ============================");
    }
}
