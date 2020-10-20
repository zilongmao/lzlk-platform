package com.lzlk.base.utils.collection;


import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/15 10:03
 * @Created by 湖南达联
 */
public class CollectionUtils {

    /**
     * 判断是否为空.
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null) || collection.isEmpty();
    }

    /**
     * 判断是否不为空.
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return (collection != null) && !(collection.isEmpty());
    }

    /**
     * Iterable是否为空
     *
     * @param iterable Iterable对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return null != iterable && iterable.iterator().hasNext();
    }

    /*
     * author:wsx
     */
    public static List getSubStringByRandom(List list, int count) {
        List backList = new ArrayList();

        Random random = new Random();
        int backSum = 0;
        if (list.size() >= count) {
            backSum = count;
        } else {
            backSum = list.size();
        }
        for (int i = 0; i < backSum; i++) {
//			随机数的范围为0-list.size()-1
            int target = random.nextInt(list.size());
            backList.add(list.get(target));
            list.remove(target);
        }
        return backList;
    }


    /**
     * 非法string集合
     *
     * @param collection
     * @return
     */
    public static boolean illegalStringCollection(Collection<String> collection) {
        if (isEmpty(collection)) {
            return true;
        }
        for (String str : collection) {
            boolean flag = StringUtils.isBlank(str);
            if (flag) {
                return true;
            }
        }
        return true;
    }

    /**
     * 包含Null
     *
     * @param collection
     * @return
     */
    public static boolean hasNull(Collection<?> collection) {
        return collection.contains(null);
    }

    /**
     * 不包含null
     *
     * @param collection 集合
     * @return
     */
    public static boolean notHasNull(Collection<?> collection) {
        return !hasNull(collection);
    }


    /**
     * 新建一个HashSet
     *
     * @param <T> 集合元素类型
     * @param ts  元素数组
     * @return HashSet对象
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        return newHashSet(false, ts);
    }

    /**
     * 新建一个HashSet
     *
     * @param <T>      集合元素类型
     * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回 {@link HashSet}
     * @param ts       元素数组
     * @return HashSet对象
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(boolean isSorted, T... ts) {
        if (null == ts) {
            return isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
        }
        int initialCapacity = Math.max((int) (ts.length / .75f) + 1, 16);
        HashSet<T> set = isSorted ? new LinkedHashSet<T>(initialCapacity) : new HashSet<T>(initialCapacity);
        for (T t : ts) {
            set.add(t);
        }
        return set;
    }

    /**
     * 反转list集合
     *
     * @param list
     */
    public static List reverse(List<?> list) {
        if (isNotEmpty(list)) {
            Collections.reverse(list);
        }
        return list;
    }

    /**
     * 移除重复 保证前面的在前面
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T>  removeDuplicate(List<T> list) {
        if (isEmpty(list)) {
            return  list;
        }
        //保证有序
        List<T> ts = new ArrayList();
        for (T t : list) {
            if (!ts.contains(t)) {
                ts.add(t);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();
        list.add(null);
        list.add("张三");
        list.add("李四");

        list.add("尼古拉斯");
        list.add("尼古拉斯");
        list.add("李四");
        System.out.println(CollectionUtils.removeDuplicate(list).getClass());
    }

}
