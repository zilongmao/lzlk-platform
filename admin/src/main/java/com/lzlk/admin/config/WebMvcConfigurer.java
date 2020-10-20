package com.lzlk.admin.config;

import com.lzlk.admin.shiro.AuthPowerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * TODO: 注册登陆拦截器
 * @Author 邻座旅客
 * @Date 2019/3/14 14:36
 * @Created by 湖南达联
 */
@Slf4j
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    @Resource
    private AuthPowerInterceptor authPowerInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authPowerInterceptor).addPathPatterns("/**");
        log.info("========================authPowerInterceptor注册成功===================================");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }







}
