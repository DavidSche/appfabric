package com.davidche.workflow.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final String DEFAULT_DATE_FORMAT = getDefaultDateFormat();
    //日期格式
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDSS = "yyyyMMddss";
    public static final String HH_MM = "HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_MMM = "yyyy-MM-dd HH:mm:ss:mmm";

    /**
     * 当前时间加上N天
     */
    public static Date Ds(int days) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        return calendar.getTime();
    }

    /**
     * 当前时间增加N月
     */
    public static Date MonthAdd(int days) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, day + days);
        return calendar.getTime();
    }

    /**
     * 获得系统当前时间
     */
    public static String nowDate() {
        return getDateFormat().format(System.currentTimeMillis());
    }

    /**
     * 获得系统当前时间
     */
    public static String nowDate(String df) {
        if (Validator.isBlank(df))
            return getDateFormat().format(System.currentTimeMillis());
        else
            return getDateFormat(df).format(System.currentTimeMillis());
    }

    /**
     * 获得当前指定时间
     */
    public static String nowDate(String df, Date date) {
        if (Validator.isBlank(df))
            return getDateFormat().format(date);
        else
            return getDateFormat(df).format(date);
    }

    /**
     * 获得当前指定时间
     */
    public static String nowDate(String df, long currentTimeMillis) {
        if (Validator.isBlank(df))
            return getDateFormat().format(currentTimeMillis);
        else
            return getDateFormat(df).format(currentTimeMillis);
    }


    /**
     * 时间差计算
     */
    public static String costTime(long time1, long time2) {
        long sub = time1 - time2;
        // yyyy-MM-dd HH:mm:ss
        String time = "";
        // 多少小时
        long remainder = sub % (3600 * 1000);
        long result = sub / (3600 * 1000);
        if (result < 10) {
            time += "00" + result;
        } else if (result < 100) {
            time += "0" + result;
        } else {
            time += "" + result;
        }
        // 多少分钟
        sub = remainder;
        remainder = sub % (60 * 1000);
        result = sub / (60 * 1000);
        if (result < 10) {
            time += ":0" + result;
        } else {
            time += ":" + result;
        }
        // 多少秒
        sub = remainder;
        result = sub / (1000);
        if (result < 10) {
            time += ":0" + result;
        } else {
            time += ":" + result;
        }

        return time;
    }

    /**
     * 时间差计算
     *
     * @param startTime 开始时间
     * @param minute    限制时间
     * @return 剩余毫秒数
     */
    public static long costTime(String startTime, String minute) throws ParseException {
        Date date = getDateFormat(YYYY_MM_DD_HH_MM_SS).parse(startTime);
        long originalTimeMillis = date.getTime();
        long currentTimeMillis = System.currentTimeMillis();
        long minuteTimeMillis = Long.parseLong(minute) * 60 * 1000;

        return minuteTimeMillis - (currentTimeMillis - originalTimeMillis);
    }

    /**
     * 时间差计算
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param minute    限制时间
     * @return 剩余毫秒数
     */
    public static long costTime(String startTime, String endTime, String minute) throws Exception {
        long originalTimeMillis = convertTimeMillis(startTime);
        long currentTimeMillis = convertTimeMillis(endTime);
        long minuteTimeMillis = Long.parseLong(minute) * 60 * 1000;
        return minuteTimeMillis - (currentTimeMillis - originalTimeMillis);
    }

    /**
     * 将指定时间转换为毫秒数
     *
     * @param time 指定时间
     */
    public static long convertTimeMillis(String time) throws Exception {
        try {
            return getDateFormat(YYYY_MM_DD_HH_MM_SS).parse(time).getTime();
        } catch (Exception e) {
            return getDateFormat(getDefaultDateFormat()).parse(time).getTime();
        }
    }

    /**
     * 默认日期格式
     */
    protected static String getDefaultDateFormat() {
        return YYYY_MM_DD_HH_MM;
    }

    /**
     * 获得默认日期格式
     */
    protected static DateFormat getDateFormat() {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }

    /**
     * 获得指定日期格式
     */
    protected static DateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 根据日期格式格式化时间
     */
    protected static String format(String format, Date date) {
        return getDateFormat(format).format(date);
    }

}