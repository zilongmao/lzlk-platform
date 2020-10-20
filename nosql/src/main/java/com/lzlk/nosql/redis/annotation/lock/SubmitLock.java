package com.lzlk.nosql.redis.annotation.lock;


import com.lzlk.base.exception.enums.ExceptionSysMarkEnum;

import java.lang.annotation.*;

/**
 * @author 邻座旅客
 * @Description 加入此注解在方法上，可以防止分布式情况下的重复提交。
 * 保证每一次提交的原子性,但是不是绝对的,如果数据库反映太慢线程卡死，会有锁失效的情况，请注意。
 * @Date 2019/8/1 10:15
 * @Created by 湖南达联
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubmitLock {


    /**
     * 获取方法上的值做为KEY
     * @return
     */
    String[] keys();


    /**
     * 过期时间
     * @return
     */
    long timeOut() default 60L;


    /**
     * 系统标识
     * @return
     */
    ExceptionSysMarkEnum sysMarkEnum();

 }
