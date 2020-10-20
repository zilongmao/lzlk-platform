package com.lzlk.base.annotation.auth;

import java.lang.annotation.*;

/**
 * TODO: 用于验证用户是否登录注解
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 07
 * @Author: 邻座旅客
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {

    boolean validate() default true;
}
