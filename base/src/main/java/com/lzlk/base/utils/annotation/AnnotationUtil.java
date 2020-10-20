package com.lzlk.base.utils.annotation;/*
package com.hndl.cn.base.utils.annotation;
import com.hndl.cn.base.utils.reflect.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

*/
/**
 * @program:hndl-platform
 * @description:TODO 注解工具类
 *
 * @author: 邻座旅客
 * @create: 2019/9/4 15:36
 * @Created by湖南达联
 **//*

public class AnnotationUtil {

    */
/**
     * 将指定的被注解的元素转换为组合注解元素
     *
     * @param annotationEle 注解元素
     * @return 组合注解元素
     *//*

    public static CombinationAnnotationElement toCombination(AnnotatedElement annotationEle) {
        if(annotationEle instanceof CombinationAnnotationElement) {
            return (CombinationAnnotationElement)annotationEle;
        }
        return new CombinationAnnotationElement(annotationEle);
    }

    */
/**
     * 获取指定注解
     *
     * @param annotationEle {@link AnnotatedElement}，可以是Class、Method、Field、Constructor、ReflectPermission
     * @param isCombination 组合
     * @return 注解对象
     *//*

    public static Annotation[] getAnnotations(AnnotatedElement annotationEle, boolean isCombination) {
        return (null == annotationEle) ? null : (isCombination ? toCombination(annotationEle) : annotationEle).getAnnotations();
    }

    */
/**
     * 获取指定注解
     *
     * @param <A> 注解类型
     * @param annotationEle {@link AnnotatedElement}，可以是Class、Method、Field、Constructor、ReflectPermission
     * @param annotationType 注解类型
     * @return 注解对象
     *//*

    public static <A extends Annotation> A getAnnotation(AnnotatedElement annotationEle, Class<A> annotationType) {
        return (null == annotationEle) ? null : toCombination(annotationEle).getAnnotation(annotationType);
    }

    */
/**
     * 获取指定注解属性的值<br>
     * 如果无指定的属性方法返回null
     *
     * @param <T> 注解值类型
     * @param annotationEle {@link AccessibleObject}，可以是Class、Method、Field、Constructor、ReflectPermission
     * @param annotationType 注解类型
     * @param propertyName 属性名，例如注解中定义了name()方法，则 此处传入name
     * @return 注解对象
     * @throws RuntimeException 调用注解中的方法时执行异常
     *//*

    public static <T> T getAnnotationValue(AnnotatedElement annotationEle, Class<? extends Annotation> annotationType, String propertyName) throws RuntimeException {
        final Annotation annotation = getAnnotation(annotationEle, annotationType);
        if (null == annotation) {
            return null;
        }
        final Method method = ReflectUtil.getMethodOfObj(annotation, propertyName);
        if (null == method) {
            return null;
        }
        return ReflectUtil.invoke(annotation, method);
    }
}
*/
