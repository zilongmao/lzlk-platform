package com.lzlk.base.utils.clazz;


import com.lzlk.base.utils.collection.ArrayUtil;

/**
 * @TODO 类工具
 * @author yiche
 */
public class ClassUtil {


    /**
     * {@code null}安全的获取对象类型
     *
     * @param <T> 对象类型
     * @param obj 对象，如果为 null返回 null
     * @return 对象类型，提供对象如果为null 返回null
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T obj) {
        return ((null == obj) ? null : (Class<T>) obj.getClass());
    }

    /**
     * 获得外围类<br>
     * 返回定义此类或匿名类所在的类，如果类本身是在包中定义的，返回null
     *
     * @param clazz 类
     * @return 外围类
     * @since 4.5.7
     */
    public static Class<?> getEnclosingClass(Class<?> clazz) {
        return null == clazz ? null : clazz.getEnclosingClass();
    }

    /**
     * 是否为顶层类，既定义在包中的类，而非定义在类中的内部类
     * @param clazz 类
     * @return 是否为顶层类
     * @since 4.5.7
     */
    public static boolean isTopLevelClass(Class<?> clazz) {
        if(null == clazz) {
            return false;
        }
        return null == getEnclosingClass(clazz);
    }

    /**
     * 是否为基本类型（包括包装类和原始类）
     *
     * @param clazz 类
     * @return 是否为基本类型
     */
    public static boolean isBasicType(Class<?> clazz) {
        if (null == clazz) {
            return false;
        }
        return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
    }


    /**
     * 是否为包装类型
     *
     * @param clazz 类
     * @return 是否为包装类型
     */
    public static boolean isPrimitiveWrapper(Class<?> clazz) {
        if (null == clazz) {
            return false;
        }
      //待完善
        return false;
    }

    /**
     * 比较判断types1和types2两组类，如果types1中所有的类都与types2对应位置的类相同，或者是其父类或接口，则返回<code>true</code>
     *
     * @param types1 类组1
     * @param types2 类组2
     * @return 是否相同、父类或接口
     */
    public static boolean isAllAssignableFrom(Class<?>[] types1, Class<?>[] types2) {
        if (ArrayUtil.isEmpty(types1) && ArrayUtil.isEmpty(types2)) {
            return true;
        }
        if (null == types1 || null == types2) {
            // 任何一个为null不相等（之前已判断两个都为null的情况）
            return false;
        }
        if (types1.length != types2.length) {
            return false;
        }

        Class<?> type1;
        Class<?> type2;
        for (int i = 0; i < types1.length; i++) {
            type1 = types1[i];
            type2 = types2[i];
            if (isBasicType(type1) && isBasicType(type2)) {
                // 原始类型和包装类型存在不一致情况
               //待完善
                    return false;

            } else if (false == type1.isAssignableFrom(type2)) {
                return false;
            }
        }
        return true;
    }
}
