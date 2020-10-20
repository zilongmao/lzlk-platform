package com.lzlk.base.utils.page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description 分页工具类
 * @Date 2019/5/6 21:09
 * @Created by 湖南达联
 */
public class PageUtils {


    /**
     * 检查mongoId
     *
     * @param id
     * @return
     */
    public static boolean checkMongoDbObjId(String id) {
        if (id == null) {
            return false;
        }

        int len = id.length();
        if (len != 24) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char c = id.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'f') {
                continue;
            }
            if (c >= 'A' && c <= 'F') {
                continue;
            }

            return false;
        }

        return true;
    }

    public static <T> List<T> listPaging(List<T> list, Integer pageNo, Integer pageSize) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        if (pageNo == null) {
            pageNo = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        int totalitems = list.size();
        List<T> pagingList = new ArrayList<T>();
        int totalNum = ((pageNo - 1) * pageSize) + pageSize > totalitems ? totalitems : ((pageNo - 1) * pageSize) + pageSize;
        for (int i = (pageNo - 1) * pageSize; i < totalNum; i++) {
            pagingList.add(list.get(i));
        }
        return pagingList;
    }


}
