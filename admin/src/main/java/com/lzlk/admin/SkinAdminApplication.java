package com.lzlk.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;


/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2020/08/18 17:57
 * @Created by 湖南爱豆
 */
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
@ComponentScans({
        @ComponentScan("com.lzlk.base"),
        @ComponentScan("com.lzlk.dao"),
        @ComponentScan("com.lzlk.mysql"),
        @ComponentScan("com.lzlk.ali"),
        @ComponentScan("com.lzlk.nosql"),
})
@Slf4j
@PropertySource(value={"file:${DemoConfig}/admin.properties"}, ignoreResourceNotFound= true)
@MapperScan(basePackages = "com.lzlk.dao.mybatis.*.mapper")
public class SkinAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkinAdminApplication.class, args);
        log.info("============================ ProjectName - 后台管理 启动成功 ============================");
    }


}
