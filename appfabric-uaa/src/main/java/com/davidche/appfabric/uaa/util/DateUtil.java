package com.davidche.appfabric.uaa.util;

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
        if (MyStringUtils.isBlank(df))
            return getDateFormat().format(System.currentTimeMillis());
        else
            return getDateFormat(df).format(System.currentTimeMillis());
    }

    /**
     * 获得当前指定时间
     */
    public static String nowDate(String df, Date date) {
        if (MyStringUtils.isBlank(df))
            return getDateFormat().format(date);
        else
            return getDateFormat(df).format(date);
    }

    /**
     * 获得当前指定时间
     */
    public static String nowDate(String df, long currentTimeMillis) {
        if (MyStringUtils.isBlank(df))
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
    /**
     * 获取过去的天数
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(24*60*60*1000);
    }

    /**
     * 获取过去的小时
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*60*1000);
    }

    /**
     * 获取过去的分钟
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(60*1000);
    }
//    private static String[] parsePatterns = {
//            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
//            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
//            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return format(pattern,new Date());
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = format(pattern[0].toString(),date);
        } else {
            formatDate = format(YYYY_MM_DD,date);
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
//    public static Date parseDate(Object str) {
//        if (str == null){
//            return null;
//        }
//        try {
//            return parseDate(str.toString(), parsePatterns);
//        } catch (ParseException e) {
//            return null;
//        }
//    }
    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }
    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis){
        long day = timeMillis/(24*60*60*1000);
        long hour = (timeMillis/(60*60*1000)-day*24);
        long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
        long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
        long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
        return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
    }
}