package com.lzlk.base.utils.reflect;/*
package com.hndl.cn.base.utils.reflect;

import com.fasterxml.jackson.databind.util.ClassUtil;
import com.hndl.cn.base.utils.cache.SimpleCache;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

*/
/**
 * @program:hndl-platform
 * @description:TODO 反射工具类
 * @author: 邻座旅客
 * @create: 2019/9/4 15:52
 * @Created by湖南达联
 **//*

public class ReflectUtil {
     */
/** 构造对象缓存 *//*

    private static final SimpleCache<Class<?>, Constructor<?>[]> CONSTRUCTORS_CACHE = new SimpleCache<>();
    */
/** 字段缓存 *//*

    private static final SimpleCache<Class<?>, Field[]> FIELDS_CACHE = new SimpleCache<>();
    */
/** 方法缓存 *//*

    private static final SimpleCache<Class<?>, Method[]> METHODS_CACHE = new SimpleCache<>();

    */
/**
     * 查找类中的指定参数的构造方法，如果找到构造方法，会自动设置可访问为true
     *
     * @param <T> 对象类型
     * @param clazz 类
     * @param parameterTypes 参数类型，只要任何一个参数是指定参数的父类或接口或相等即可，此参数可以不传
     * @return 构造方法，如果未找到返回null
     *//*

    @SuppressWarnings("unchecked")
    public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... parameterTypes) {
        if (null == clazz) {
            return null;
        }

        final Constructor<?>[] constructors = getConstructors(clazz);
        Class<?>[] pts;
        for (Constructor<?> constructor : constructors) {
            pts = constructor.getParameterTypes();
            if (ClassUtil.isAllAssignableFrom(pts, parameterTypes)) {
                // 构造可访问
                constructor.setAccessible(true);
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }

    */
/**
     * 获得一个类中所有构造列表
     *
     * @param <T> 构造的对象类型
     * @param beanClass 类
     * @return 字段列表
     * @throws SecurityException 安全检查异常
     *//*

    @SuppressWarnings("unchecked")
    public static <T> Constructor<T>[] getConstructors(Class<T> beanClass) throws SecurityException {
        Assert.notNull(beanClass);
        Constructor<?>[] constructors = CONSTRUCTORS_CACHE.get(beanClass);
        if (null != constructors) {
            return (Constructor<T>[]) constructors;
        }

        constructors = getConstructorsDirectly(beanClass);
        return (Constructor<T>[]) CONSTRUCTORS_CACHE.put(beanClass, constructors);
    }

}
*/
