package com.lzlk.base.utils.date;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author 邻座旅客
 * @Description LocalDate 工具类
 * @Date 2019/4/25 20:51
 * @Created by 湖南达联
 */
public class LocalDateUtil {

    //默认使用系统当前时区
    private static final ZoneId ZONE = ZoneId.systemDefault();

    /**
     * 判断是否是今天
     * @param localDate
     * @return
     */
    public static boolean isNowDate(LocalDate localDate){
        return LocalDate.now().isEqual(localDate);
    }


    /**
     * 明天时间
     * @return
     */
    public static LocalDate nextDay(){
         return addDay(1L);
    }

    /**
     * 下周时间
     * @return
     */
    public static LocalDate nextWeek(){
        return addWeek(1L);
    }

    /**
     * 下月时间
     * @return
     */
    public static LocalDate nextMonth(){
        return addMonth(1L);
    }

    /**
     * 明年时间
     * @return
     */
    public static LocalDate nextYears(){
        return addYears(1L);
    }


    /**
     *  加N天
     * @param day
     * @return
     */
    public static LocalDate addDay(Long day){
        return LocalDate.now().plusDays(day);
    }

    public static boolean isBeforeNowDay(LocalDate localDate,Integer day){
        return localDate.plusDays(day).isBefore(LocalDate.now());
    }

    public static boolean isBeforeNowWeek(LocalDate localDate,Integer week){
        return localDate.plusWeeks(week).isBefore(LocalDateUtil.firstDayOfWeek());
    }

    public static boolean isBeforeNowMonth(LocalDate localDate,Integer month){
        return localDate.plusMonths(month).isBefore(LocalDateUtil.firstDayOfMonth());
    }


    /**
     * 加N周
     * @param week
     * @return
     */
    public static LocalDate addWeek(Long week){
        return LocalDate.now().plusWeeks(week);
    }

    /**
     * 加N月
     * @param month
     * @return
     */
    public static LocalDate addMonth(Long month){
        return LocalDate.now().plusMonths(month);
    }

    /**
     * 加N年
     * @param years
     * @return
     */
    public static LocalDate addYears(Long years){
        return LocalDate.now().plusYears(years);
    }

    /**
     * 获取这个月的第一天
     * @return
     */
    public static LocalDate firstDayOfMonth(){
        return firstDayOfMonth(LocalDate.now());
    }


    /**
     * 获取这个月的第一天
     * @return
     */
    public static LocalDate firstDayOfMonth(LocalDate localDate){
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取上个月的第一天
     * @return
     */
    public static LocalDate firstDayOfPreMonth(){
      return   firstDayOfMonth(LocalDate.now().minusMonths(1));
    }

    /**
     * 获取这个月的最后一天
     * @return
     */
    public static LocalDate lastDayOfMonth(){
        return lastDayOfMonth(LocalDate.now());
    }

    /**
     * 获取上个月的最后一天
     * @return
     */
    public static LocalDate lastDayOfPreMonth(){
      return   lastDayOfMonth(LocalDate.now().minusMonths(1));
    }

    /**
     * 获取这个月的最后一天
     * @return
     */
    public static LocalDate lastDayOfMonth(LocalDate localDate){
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取这周的第一天
     * @return
     */
    public static LocalDate firstDayOfWeek(){
        return LocalDate.now().with(DayOfWeek.MONDAY);
    }

    /**
     * 获取这周的第一天
     * @return
     */
    public static LocalDate firstDayOfWeek(LocalDate localDate){
        return localDate.with(DayOfWeek.MONDAY);
    }

    /**
     * 获取这周的最后一天
     * @return
     */
    public static LocalDate lastDayOfWeek(){
        return LocalDate.now().with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取这周的最后一天
     * @return
     */
    public static LocalDate lastDayOfWeek(LocalDate localDate){
        return localDate.with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取今年的第一天
     * @return
     */
    public static LocalDate firstDayOfYear(){
        return LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 获取今年的最后一天
     * @return
     */
    public static LocalDate lastDayOfYear(){
        return LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
    }


    /**
     * 是否是星期天
     * @return
     */
    public static boolean isSUNDAY(){
        return LocalDate.now().getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    /**
     * 是否是星期天
     * @return
     */
    public static boolean isSUNDAY(LocalDate localDate){
        return localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    /**
     * 是否是星期一
     * @return
     */
    public static boolean isMONDAY(){
        return LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY);
    }

    /**
     * 是否是星期一
     * @return
     */
    public static boolean isMONDAY(LocalDate localDate){
        return localDate.getDayOfWeek().equals(DayOfWeek.MONDAY);
    }

    /**
     * 是否是这个月的第一天
     * @return
     */
    public static boolean isFirstDayOfMonth(){
        return LocalDate.now().isEqual(firstDayOfMonth());
    }

    /**
     * 是否是这个月的第一天
     * @return
     */
    public static boolean isFirstDayOfMonth(LocalDate localDate){
        return localDate.isEqual(firstDayOfMonth(localDate));
    }


    /**
     * 是否是这个月的最后一天
     * @return
     */
    public static boolean isLastDayOfMonth(){
        return LocalDate.now().isEqual(lastDayOfMonth());
    }

    /**
     * 是否是这个月的最后一天
     * @return
     */
    public static boolean isLastDayOfMonth(LocalDate localDate){
        return localDate.isEqual(lastDayOfMonth(localDate));
    }


    /**
     * 是否是今年的第一天
     * @return
     */
    public static boolean isFirstDayOfNextYear(){
        return LocalDate.now().isEqual(firstDayOfYear());
    }

    /**
     * 是否今年的最后一天
     * @return
     */
    public static boolean isLirstDayOfNextYear(){
        return LocalDate.now().equals(lastDayOfYear());
    }

    /**
     * 转换成秒数
     * @param localDate
     * @return
     */
    public static Long localDateToSecond(LocalDate localDate){
        return LocalDateTime.of(localDate,LocalTime.MIN).toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 转换毫秒数
     * @param localDate
     * @return
     */
    public static Long localDateToMilliSecond (LocalDate localDate){
        return LocalDateTime.of(localDate,LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将LocalDate转换成Date
     *
     * @param localDate
     * @return date
     */
    public static Date localDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZONE).toInstant();
        return Date.from(instant);
    }


    /**
     * 将date转换成localDate
     * @param date
     * @return
     */
    public static LocalDate DateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 将毫秒数转为LocalDate
     * @param milliSecond
     * @return
     */
    public static LocalDate milliSecondToLocalDate(Long milliSecond) {
        return DateToLocalDate(new Date(milliSecond));
    }

    /**
     * 将秒数转为LocalDate
     * @param second
     * @return
     */
    public static LocalDate secondToLocalDate(Long second) {
        return DateToLocalDate(new Date(second * 1000));
    }


    /**
     * 获取某年的开始日期
     *
     * @param offset 0今年，1明年，-1去年，依次类推
     * @return
     */
    public static LocalDate yearStart(int offset) {
        return         LocalDate.now().plusYears(offset).with(TemporalAdjusters.firstDayOfYear());
    }

    public static void main(String[] args) {
        System.out.println(lastDayOfMonth());
        System.out.println(lastDayOfPreMonth());
        System.out.println(firstDayOfMonth());
        System.out.println(firstDayOfPreMonth());
    }
}
