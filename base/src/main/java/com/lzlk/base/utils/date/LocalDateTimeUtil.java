package com.lzlk.base.utils.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/31 15:51
 * @Created by 湖南达联
 */
public class LocalDateTimeUtil {

    /**
     * 获取今天的初始时间
     * @return
     */
    public static Long getNowMinTimeMilli(){
        return  DateUtil.beginOfDate(new Date()).getTime();
    }

    /**
     * 获取今天的结束时间
     * @return
     */
    public static Long getNowMaxTimeMilli(){
        return  DateUtil.endOfDate(new Date()).getTime();
    }


    /**
     * 获取昨天的初始时间
     * @return
     */
    public static  Long getYesterdayMinTimeMilli(){
        return  DateUtil.beginOfDate(new Date(System.currentTimeMillis()-1000*60*60*24)).getTime();
    }

    /**
     * 获取昨天的结束时间
     * @return
     */
    public static  Long getYesterdayMaxTimeMilli(){
        return DateUtil.endOfDate(new Date(System.currentTimeMillis()-1000*60*60*24)).getTime();
    }

    /**
     * 获得上周第一天起始毫秒值
     */
    public static Long getWeekStartDayTimeMillis() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,2);
        c.add(Calendar.DATE, -7);
        return DateUtil.beginOfDate(c.getTime()).getTime();
    }

    /**
     * 获得上周最后一天结束毫秒值
     */
    public static Long getWeekEndDayTimeMillis() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,2);
        c.add(Calendar.DATE, -1);
        return DateUtil.endOfDate(c.getTime()).getTime();
    }

    public static Long localDateToSecond(LocalDateTime localDateTime){
        return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Long localDateToMilliSecond(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static LocalDateTime milliSecondToLocalDateTime(Long milliSecond){
         return Instant.ofEpochMilli(milliSecond).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    public static void main(String[] args) {
        System.out.println(getNowMinTimeMilli());
        System.out.println(getNowMaxTimeMilli());
        System.out.println(getWeekStartDayTimeMillis());
        System.out.println(getWeekEndDayTimeMillis());
    }

}
