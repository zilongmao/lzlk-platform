package com.lzlk.base.utils.date;


import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类.
 * <p>
 * 在不方便使用joda-time时，使用本类降低Date处理的复杂度与性能消耗, 封装Common Lang及移植Jodd的最常用日期方法
 *
 * @author calvin
 */
public class DateUtil {

    public static final long MILLIS_PER_SECOND = 1000; // Number of milliseconds in a quartz second.

    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND; // Number of milliseconds in a quartz minute.

    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE; // Number of milliseconds in a quartz hour.

    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR; // Number of milliseconds in a quartz day.

    public static final long MILLIS_PER_WEEK = 7 * MILLIS_PER_DAY;

    private static final int[] MONTH_LENGTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    /**
     * 根据日期枚举类型返回正确的日期开始时间
     * code： 1
     * date： 2019年11月25日 14:22:35
     * 返回 ：2019年11月25日 14:00:00
     * @param statisticTimeCode
     * @param date
     * @return
     */
    public static Date formatStatisticStartDate(Integer statisticTimeCode, Date date) {
        switch (statisticTimeCode) {
            case 1 :
                date = DateUtil.beginOfHour(addHours(date, -1));
                break;
            case 2 :
                date = DateUtil.beginOfDate(addDays(date, -1));
                break;
            case 3 :
                date = DateUtil.beginOfWeek(addWeeks(date, -1));
                break;
            case 4 :
                date = DateUtil.beginOfMonth(addMonths(date, -1));
                break;
            case 5 :
                date = DateUtil.beginOfQuarter(addMonths(date, -3));
                break;
            case 6 :
                date = DateUtil.beginOfYear(addMonths(date, -12));
                break;
            default:
                date = null;
        }
        return date;
    }

    /**
     * 根据日期枚举类型返回正确的日期结束时间
     * code： 2
     * date： 2019年11月25日 14:22:35
     * 返回 ：2019年11月25日 23:59:59
     * @param statisticTimeCode
     * @param date
     * @return
     */
    public static Date formatStatisticEndDate(Integer statisticTimeCode, Date date) {
        switch (statisticTimeCode) {
            case 1 :
                date = DateUtil.endOfHour(addHours(date, -1));
                break;
            case 2 :
                date = DateUtil.endOfDate(addDays(date, -1));
                break;
            case 3 :
                date = DateUtil.endOfWeek(addWeeks(date, -1));
                break;
            case 4 :
                date = DateUtil.endOfMonth(addMonths(date, -1));
                break;
            case 5 :
                date = DateUtil.endOfQuarter(addMonths(date, -3));
                break;
            case 6 :
                date = DateUtil.endOfYear(addMonths(date, -12));
                break;
            default:
                date = null;
        }
        return date;
    }

    /**
     * 获取当前小时 24小时制
     */
    public static Integer getNowHour() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        return Integer.valueOf(sdf.format(new Date()));
    }

    /**
     * 获取当前分钟
     */
    public static Integer getNowMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        return Integer.valueOf(sdf.format(new Date()));
    }

    //////// 日期比较 ///////////

    /**
     * 是否同一天.
     *
     * @see DateUtils#isSameDay(Date, Date)
     */
    public static boolean isSameDay(final Date date1, final Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * 是否同一时刻.
     */
    public static boolean isSameTime(final Date date1, final Date date2) {
        // date.getMillisOf() 比date.getTime()快
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断日期是否在范围内，包含相等的日期
     */
    public static boolean isBetween(final Date date, final Date start, final Date end) {
        if (date == null || start == null || end == null || start.after(end)) {
            return false;
        }
        return !date.before(start) && !date.after(end);
    }

    //////////// 往前往后滚动时间//////////////

    /**
     * 加一月
     */
    public static Date addMonths(final Date date, int amount) {
        return DateUtils.addMonths(date, amount);
    }

    /**
     * 减一月
     */
    public static Date subMonths(final Date date, int amount) {
        return DateUtils.addMonths(date, -amount);
    }

    /**
     * 加一周
     */
    public static Date addWeeks(final Date date, int amount) {
        return DateUtils.addWeeks(date, amount);
    }

    /**
     * 减一周
     */
    public static Date subWeeks(final Date date, int amount) {
        return DateUtils.addWeeks(date, -amount);
    }

    /**
     * 加一天
     */
    public static Date addDays(final Date date, final int amount) {
        return DateUtils.addDays(date, amount);
    }

    /**
     * 减一天
     */
    public static Date subDays(final Date date, int amount) {
        return DateUtils.addDays(date, -amount);
    }

    /**
     * 加一小时
     */
    public static Date addHours(final Date date, int amount) {
        return DateUtils.addHours(date, amount);
    }

    /**
     * 减一小时
     */
    public static Date subHours(final Date date, int amount) {
        return DateUtils.addHours(date, -amount);
    }

    /**
     * 加一分钟
     */
    public static Date addMinutes(final Date date, int amount) {
        return DateUtils.addMinutes(date, amount);
    }

    /**
     * 减一分钟
     */
    public static Date subMinutes(final Date date, int amount) {
        return DateUtils.addMinutes(date, -amount);
    }

    /**
     * 终于到了，续一秒.
     */
    public static Date addSeconds(final Date date, int amount) {
        return DateUtils.addSeconds(date, amount);
    }

    /**
     * 减一秒.
     */
    public static Date subSeconds(final Date date, int amount) {
        return DateUtils.addSeconds(date, -amount);
    }

    //////////// 直接设置时间//////////////

    /**
     * 设置年份, 公元纪年.
     */
    public static Date setYears(final Date date, int amount) {
        return DateUtils.setYears(date, amount);
    }

    /**
     * 设置月份, 0-11.
     */
    public static Date setMonths(final Date date, int amount) {
        return DateUtils.setMonths(date, amount);
    }

    /**
     * 设置日期, 1-31.
     */
    public static Date setDays(final Date date, int amount) {
        return DateUtils.setDays(date, amount);
    }

    /**
     * 设置小时, 0-23.
     */
    public static Date setHours(final Date date, int amount) {
        return DateUtils.setHours(date, amount);
    }

    /**
     * 设置分钟, 0-59.
     */
    public static Date setMinutes(final Date date, int amount) {
        return DateUtils.setMinutes(date, amount);
    }

    /**
     * 设置秒, 0-59.
     */
    public static Date setSeconds(final Date date, int amount) {
        return DateUtils.setSeconds(date, amount);
    }

    /**
     * 设置毫秒.
     */
    public static Date setMilliseconds(final Date date, int amount) {
        return DateUtils.setMilliseconds(date, amount);
    }

    ///// 获取日期的位置//////

    /**
     * 获得日期是一周的第几天. 已改为中国习惯，1 是Monday，而不是Sundays.
     */
    public static int getDayOfWeek(final Date date) {
        int result = get(date, Calendar.DAY_OF_WEEK);
        return result == 1 ? 7 : result - 1;
    }

    /**
     * 获得日期是一年的第几天，返回值从1开始
     */
    public static int getDayOfYear(final Date date) {
        return get(date, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得日期是一月的第几周，返回值从1开始.
     * <p>
     * 开始的一周，只要有一天在那个月里都算. 已改为中国习惯，1 是Monday，而不是Sunday
     */
    public static int getWeekOfMonth(final Date date) {
        return getWithMondayFirst(date, Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获得日期是一年的第几周，返回值从1开始.
     * <p>
     * 开始的一周，只要有一天在那一年里都算.已改为中国习惯，1 是Monday，而不是Sunday
     */
    public static int getWeekOfYear(final Date date) {
        return getWithMondayFirst(date, Calendar.WEEK_OF_YEAR);
    }

    private static int get(final Date date, int field) {
        Validate.notNull(date, "The date must not be null");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);

        return cal.get(field);
    }

    private static int getWithMondayFirst(final Date date, int field) {
        Validate.notNull(date, "The date must not be null");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        return cal.get(field);
    }

    ///// 获得往前往后的日期//////

    /**
     * 2016-11-10 07:33:23, 则返回2016-1-1 00:00:00
     */
    public static Date beginOfYear(final Date date) {
        return DateUtils.truncate(date, Calendar.YEAR);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-12-31 23:59:59.999
     */
    public static Date endOfYear(final Date date) {
        return new Date(nextYear(date).getTime() - 1);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2017-1-1 00:00:00
     */
    public static Date nextYear(final Date date) {
        return DateUtils.ceiling(date, Calendar.YEAR);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-1 00:00:00
     */
    public static Date beginOfMonth(final Date date) {
        return DateUtils.truncate(date, Calendar.MONTH);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-30 23:59:59.999
     */
    public static Date endOfMonth(final Date date) {
        return new Date(nextMonth(date).getTime() - 1);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-12-1 00:00:00
     */
    public static Date nextMonth(final Date date) {
        return DateUtils.ceiling(date, Calendar.MONTH);
    }

    /**
     * 2017-1-20 07:33:23, 则返回2017-1-16 00:00:00
     */
    public static Date beginOfWeek(final Date date) {
        return DateUtils.truncate(DateUtil.subDays(date, DateUtil.getDayOfWeek(date) - 1), Calendar.DATE);
    }

    /**
     * 2017-1-20 07:33:23, 则返回2017-1-22 23:59:59.999
     */
    public static Date endOfWeek(final Date date) {
        return new Date(nextWeek(date).getTime() - 1);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-22 00:00:00
     */
    public static Date nextWeek(final Date date) {
        return DateUtils.truncate(DateUtil.addDays(date, 8 - DateUtil.getDayOfWeek(date)), Calendar.DATE);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-10 00:00:00
     */
    public static Date beginOfDate(final Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-23 23:59:59.999
     */
    public static Date endOfDate(final Date date) {
        return new Date(nextDate(date).getTime() - 1);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-11 00:00:00
     */
    public static Date nextDate(final Date date) {
        return DateUtils.ceiling(date, Calendar.DATE);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 07:00:00
     */
    public static Date beginOfHour(final Date date) {
        return DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-23 07:59:59.999
     */
    public static Date endOfHour(final Date date) {
        return new Date(nextHour(date).getTime() - 1);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 08:00:00
     */
    public static Date nextHour(final Date date) {
        return DateUtils.ceiling(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 07:33:00
     */
    public static Date beginOfMinute(final Date date) {
        return DateUtils.truncate(date, Calendar.MINUTE);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-23 07:33:59.999
     */
    public static Date endOfMinute(final Date date) {
        return new Date(nextMinute(date).getTime() - 1);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 07:34:00
     */
    public static Date nextMinute(final Date date) {
        return DateUtils.ceiling(date, Calendar.MINUTE);
    }

    /**
     * 2019-10-11 17:27:00, 则返回2019-10-11 17:00:00
     * 2019-10-11 17:00:00, 则返回2019-10-11 16:30:00
     */
    public static Long roundNumberMinute(Long milliSecond) {
        Date date = new Date(milliSecond);
        if (date.getMinutes() / 30 == 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, new Date(milliSecond).getHours() - 1);
            calendar.set(Calendar.MINUTE, 30);
            return calendar.getTimeInMillis();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTimeInMillis();
    }
    ////// 闰年及每月天数///////

    /**
     * 是否闰年.
     */
    public static boolean isLeapYear(final Date date) {
        return isLeapYear(get(date, Calendar.YEAR));
    }

    /**
     * 是否闰年，移植Jodd Core的TimeUtil
     * <p>
     * 参数是公元计数, 如2016
     */
    public static boolean isLeapYear(int y) {
        boolean result = false;

        if (((y % 4) == 0) && // must be divisible by 4...
                ((y < 1582) || // and either before reform year...
                        ((y % 100) != 0) || // or not a century...
                        ((y % 400) == 0))) { // or a multiple of 400...
            result = true; // for leap year.
        }
        return result;
    }

    /**
     * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
     */
    public static int getMonthLength(final Date date) {
        int year = get(date, Calendar.YEAR);
        int month = get(date, Calendar.MONTH);
        return getMonthLength(year, month);
    }

    /**
     * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
     */
    public static int getMonthLength(int year, int month) {

        if ((month < 1) || (month > 12)) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }

        return MONTH_LENGTH[month];
    }

    public static Date getFirstDayOfMonth(Long time) {
        if (null == time) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return dates;
    }

    /**
     * 当前季度的开始时间，即2012-01-1 00:00:00
     *
     * @return
     */
    public static Date beginOfQuarter(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3) {
            calendar.set(Calendar.MONTH, 0);
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            calendar.set(Calendar.MONTH, 3);
        }
        else if (currentMonth >= 7 && currentMonth <= 9) {
            calendar.set(Calendar.MONTH, 6);
        }
        else if (currentMonth >= 10 && currentMonth <= 12) {
            calendar.set(Calendar.MONTH, 9);
        }
        calendar.set(Calendar.DATE, 1);
        return beginOfDate(calendar.getTime());
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     *
     * @return
     */
    public static Date endOfQuarter(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3) {
            calendar.set(Calendar.MONTH, 2);
            calendar.set(Calendar.DATE, 31);
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            calendar.set(Calendar.MONTH, 5);
            calendar.set(Calendar.DATE, 30);
        } else if (currentMonth >= 7 && currentMonth <= 9) {
            calendar.set(Calendar.MONTH, 8);
            calendar.set(Calendar.DATE, 30);
        } else if (currentMonth >= 10 && currentMonth <= 12) {
            calendar.set(Calendar.MONTH, 11);
            calendar.set(Calendar.DATE, 31);
        }
        return endOfMonth(calendar.getTime());
    }

    /**
     * 月的最后一天
     *
     * @param time
     * @return
     * @throws IllegalArgumentException
     */
    public static Date getLastDayOfMonth(Long time) {
        if (null == time) {
            throw new IllegalArgumentException("this time must not to be null! ");
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        Date dates = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        return dates;
    }

    /**
     * 将时间转换为yyyy.MM.dd格式
     * 如：Thu May 16 19:36:24 CST 2019 转 2019-05-16
     */
    public static String getformatToStringDateEn(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(date);
    }

    /**
     * 将时间转换为yyyy-MM-dd HH:mm:ss格式
     * 如：Thu May 16 19:36:24 CST 2019 转 2019年8月19日 16:49:12
     */
    public static String getformatToStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /***
     * 获取相距多少周
     * @return
     */
    public static int getApartWeek(Date startDate, Date endDate) {
        return Math.abs((int) ((endDate.getTime() - startDate.getTime()) / MILLIS_PER_WEEK));
    }
    /***
     * 获取相距多少天
     * @return
     */
    public static int getApartDay(Date startDate, Date endDate) {
        return Math.abs((int) ((endDate.getTime() - startDate.getTime()) / MILLIS_PER_DAY));
    }

    public static void main(String[] args) {
//        System.out.println(getApartWeek(new Date(), new Date()));
        System.out.println(endOfQuarter(new Date()));
        System.out.println(endOfQuarter(addMonths(new Date(), 3)).getTime());
        System.out.println(endOfQuarter(addMonths(new Date(), 4)).getTime());
        System.out.println(endOfQuarter(addMonths(new Date(), 5)).getTime());
        System.out.println(endOfQuarter(addMonths(new Date(), 7)).getTime());
        System.out.println(endOfQuarter(addMonths(new Date(), 9)).getTime());
        System.out.println(endOfQuarter(addMonths(new Date(), 11)).getTime());
    }

}
