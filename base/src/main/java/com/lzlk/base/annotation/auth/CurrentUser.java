package com.lzlk.base.annotation.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的User对象
 *
 * @Created by 湖南爱豆
 * @Date 2020/7/22 15 07
 * @Author: 邻座旅客
 */
@Target(ElementType.PARAMETER)          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
public @interface CurrentUser {
}
