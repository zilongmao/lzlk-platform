package com.lzlk.base.utils.function;

/**
 * @program:hndl-platform
 * @description:TODO 无参数的函数对象
 * 一个函数接口代表一个一个函数，用于包装一个函数为对象<br>
 *     http://actframework.org/
 * @author: 邻座旅客
 * @create: 2019/9/4 18:56
 * @Created by湖南达联
 **/
public interface Func<R> {

    /* 执行函数
	 *
     * @return 函数执行结果
	 * @throws Exception 自定义异常
	 */
    R call() throws Exception;
}
