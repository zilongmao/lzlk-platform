package com.lzlk.base.utils.spring;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <pre>
 *  
 *
 * 【标题】: bean对象之间copy工具类
 * 【描述】: 
 *    注： 
 *     1：对象之间的转换，只转换相同名称属性的值
 *     2：属性set/get不规范的，可能转换不成功
 * 【版权】: 湖南达联
 * 【作者】: 邻座旅客
 * 【时间】: 2017年7月18日 上午10:08:27
 * </pre>
 */
public class SpringCglibBeanUtils {
    /**
     * the beanCopierMap
     */
    private static final ConcurrentMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();

    /**
     * 两个类对象之间转换<br>
     * 1:例如 VO转DTO
     * 
     * @description
     * @param source
     *            源
     * @param targetClass
     *            目标
     * @return
     * @return T
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        T ret = null;
        if (source != null) {
            try {
                ret = targetClass.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("create class[" + targetClass.getName() + "] instance error", e);
            }
            BeanCopier beanCopier = getBeanCopier(source.getClass(), targetClass);
            beanCopier.copy(source, ret, null);
        }
        return ret;
    }

    /**
     * 两个类对象之间转换<br>
     * 1：将源转成目标并返回目标
     * 
     * @Description
     * @author 邻座旅客
     * @param source
     * @param targetObj
     * @param targetClass
     * @return
     */
    public static <T> T convert(Object source, T targetObj, Class<T> targetClass) {
        if (targetObj != null && source != null) {
            BeanCopier beanCopier = getBeanCopier(source.getClass(), targetClass);
            beanCopier.copy(source, targetObj, null);
        }
        return targetObj;
    }

    /**
     * 两个类对象之间转换<br/>
     * 1:例如 List<VO>转List<DTO>
     * 
     * @Description
     * @author 邻座旅客
     * @param <E>
     * @param sourceList
     *            源list数据
     * @param targetClass
     *            目标
     * @return
     */
    public static <T, E> List<T> convertByList(List<E> sourceList, Class<T> targetClass) {
        if(CollectionUtils.isEmpty(sourceList)){
            return new ArrayList<>();
        }
        
        List<T> convertData = new ArrayList<>(sourceList.size());
        for (E source : sourceList) {
            convertData.add(convert(source, targetClass));
        }
        return convertData;
    }

    /**
     * @description 获取BeanCopier
     * @param source
     * @param targetClass
     * @return
     * @return BeanCopier
     */
    private static BeanCopier getBeanCopier(Class<?> source, Class<?> targetClass) {
        return BeanCopier.create(source, targetClass, false);
    }

    /**
     * @description 生成两个类的key
     * @param source
     * @param targetClass
     * @return
     * @return String
     */
    private static String generateBeanKey(Class<?> source, Class<?> targetClass) {
        return source.getName() + "@" + targetClass.getName();
    }
}
