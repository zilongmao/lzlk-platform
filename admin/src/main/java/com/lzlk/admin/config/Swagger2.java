package com.lzlk.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 邻座旅客
 * @Description Swagger2 配置
 * @Date 2020年3月26日 19:27:36
 * @Created 湖南爱豆
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${spring.boot.test.swagger}")
    private Boolean test;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(test)
                .groupName("admin")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lzlk.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("admin")
                .description("ProjectName - 后台管理系统")
                .version("1.0.0")
                .build();
    }
}
