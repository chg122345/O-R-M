package gxf.orm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 日期转换工具
 * @author Chen_9g
 * @date 2018年1月16日下午1:51:16
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class DateUtil {

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


    /**
     * 日期与时间转字符串
     */
    public static String formatDatetime(Date datetime) {
        return datetimeFormat.format(datetime);
    }

    /**
     * 日期转字符串
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 时间转字符串
     */
    public static String formatTime(Date time) {
        return timeFormat.format(time);
    }

    /**
     * 格式化日期与时间
     */
    public static String formatDatetime(long timestamp) {
        return datetimeFormat.format(new Date(timestamp));
    }

    /**
     * 格式化日期
     */
    public static String formatDate(long timestamp) {
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * 格式化时间
     */
    public static String formatTime(long timestamp) {
        return timeFormat.format(new Date(timestamp));
    }

    /**
     * 获取当前日期与时间
     */
    public static String getCurrentDatetime() {
        return datetimeFormat.format(new Date());
    }

    /**
     * 获取当前日期
     */
    public static String getCurrentDate() {
        return dateFormat.format(new Date());
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        return timeFormat.format(new Date());
    }

    /**
     * 解析日期与时间
     */
    public static Date parseDatetime(String str) {
        Date date = null;
        try {
            date = datetimeFormat.parse(str);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 解析日期
     */
    public static Date parseDate(String str) {
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 解析时间
     */
    public static Date parseTime(String str) {
        Date date = null;
        try {
            date = timeFormat.parse(str);
        } catch (ParseException e) {
        }
        return date;
    }
}
