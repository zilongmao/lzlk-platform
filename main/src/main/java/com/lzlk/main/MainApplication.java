package com.lzlk.main;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * TODO:
 *
 * @Created by 湖南爱豆
 * @Date 2020/3/18 01 29
 * @Author: 邻座旅客
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
@PropertySource(value={"file:${DemoConfig}/main.properties"}, ignoreResourceNotFound= true)
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
        log.info("============================ ProjectName - 主站 启动成功 ============================");
    }

}
