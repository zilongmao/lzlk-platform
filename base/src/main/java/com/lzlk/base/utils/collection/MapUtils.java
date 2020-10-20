package com.lzlk.base.utils.collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * <pre>
 *
 *
 * 【标题】: map工具类
 * 【描述】:
 * 【版权】: 湖南爱豆
 * 【作者】: 邻座旅客
 * 【时间】: 2017年7月10日 下午2:13:56
 * </pre>
 */
public class MapUtils extends org.apache.commons.collections.MapUtils{
    /**
     * map转对象
     *
     * @Description
     * @author 邻座旅客
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null)
            return null;
        T obj = beanClass.newInstance();
        BeanUtils.populate(obj, map);
        return obj;
    }

    /**
     * 对象转MAP
     *
     * @Description
     * @author 邻座旅客
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> objectToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        BeanMap beanMap = null;
        if (bean == null) {
            return null;
        }
        beanMap = new BeanMap(bean);
        Iterator<Entry<Object, Object>> entries = beanMap.entrySet().iterator();
        while (entries.hasNext()) {
            Entry<Object, Object> entry = entries.next();
            String key = String.valueOf(entry.getKey());
            if ("class".equals(key)) {
                continue;
            }
            Object value = entry.getValue();
            map.put(key, value);
        }
        return map;
    }


    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = objectToMap(bean);
                map.remove("class");
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToObject(map, clazz);
                list.add(bean);
            }
        }
        return list;
    }

}
