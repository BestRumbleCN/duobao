/*
 * Copyright (c) 2009-2013 上海通路快建网络外包服务有限公司 All rights reserved.
 * @(#) AbstractLogBuilder.java 2013-07-22 14:50
 */

package team.wuxie.crowdfunding.util.date;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 一个继承自{@link org.apache.commons.lang3.time.DateFormatUtils} 的日期格式化处理工具类。
 * <p />
 * Commons-Lang文档详见：<a href="http://commons.apache.org/lang/api-2.6/index.html">Apache
 * Commons Lang Javadoc</a>
 *
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {
    
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm";

    public static final String DEFAULT_DATETIME_PATTERN_DATE = "yyyy-MM-dd";
    
    public static final String DEFAULT_DATETIME_PATTERN2 = "yyyy-MM-dd HH:mm:ss";

    public static final String WX_DATE_FORMAT = "yyyyMMddHHmmss";
    
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
    /**
     * 用默认的语言环境、时区和模式格式化当前时间。默认的模式见 {@link #DEFAULT_DATETIME_PATTERN}。
     * 
     * @return 默认模式下格式化的当前时间字符串。
     */
    public static String format() {
        return format(DateUtils.now(), DEFAULT_DATETIME_PATTERN, TimeZone.getDefault(), null);
    }
    
    /**
     * 用默认的语言环境、时区和模式格式化给定的时间。默认的模式见 {@link #DEFAULT_DATETIME_PATTERN}。
     * 
     * @return 默认模式下格式化的给定的时间字符串。
     */
    public static String format(Date date) {
    	return format(date, DEFAULT_DATETIME_PATTERN, TimeZone.getDefault(), null);
    }

    /**
     * 用默认的语言环境、时区和模式格式化给定的时间。默认的模式见 {@link #WX_DATE_FORMAT}。
     * 
     * @return 默认模式下格式化的给定的时间字符串。
     */
    public static String wxTimeFormat(Date date) {
    	return format(date, WX_DATE_FORMAT, TimeZone.getDefault(), null);
    }
    /**
     * 用默认的语言环境、时区和模式格式化给定的时间。默认的模式见 {@link #DEFAULT_DATE_FORMAT}。
     * 
     * @return 默认模式下格式化的给定的时间字符串。
     */
    public static String dateFormat(Date date) {
    	return format(date, DEFAULT_DATE_FORMAT, TimeZone.getDefault(), null);
    }
    /**
     * 用默认的语言环境、时区和模式格式化当前时间。默认的模式见 {@link #DEFAULT_DATETIME_PATTERN}。
     *
     * @return 默认模式下格式化的当前时间字符串。
     */
    public static String format_date() {
        return format(DateUtils.now(), DEFAULT_DATETIME_PATTERN_DATE, TimeZone.getDefault(), null);
    }

    /**
     * 用默认的语言环境、时区和模式格式化给定的时间。默认的模式见 {@link #DEFAULT_DATETIME_PATTERN}。
     *
     * @return 默认模式下格式化的给定的时间字符串。
     */
    public static String format_date(Date date) {
        return format(date, DEFAULT_DATETIME_PATTERN_DATE, TimeZone.getDefault(), null);
    }
    
    /**
     * 用默认的语言环境、时区和指定的模式格式化当前时间。
     * 
     * @param pattern 给定的模式。
     * @return 给定模式下格式化的当前时间字符串。
     */
    public static String format(String pattern) {
        return format(DateUtils.now(), pattern, TimeZone.getDefault(), null);
    }
    
    /**
     * 用默认的语言环境、时区和模式格式化指定的时间毫秒数表示的日期。
     * 
     * @param time 指定的时间毫秒数。
     * @return 格式化指定的时间毫秒数表示的日期字符串。
     */
    public static String format(long time) {
        return format(time, DEFAULT_DATETIME_PATTERN, TimeZone.getDefault(), Locale.getDefault());
    }
    
    /**
     * 用默认的时区、模式和指定的语言环境格式化当前日期。
     * 
     * @param locale 指定的语言环境。
     * @return 默认的时区、模式和指定的语言环境下的当前日期字符串。
     */
    public static String format(Locale locale) {
        return format(DateUtils.now(), DEFAULT_DATETIME_PATTERN, locale);
    }
    
    /**
     * 用默认的语言环境、模式和指定的时区格式化当前日期。
     * 
     * @param timeZone 指定的时区。
     * @return 默认的语言环境、模式和指定的时区格式化的当前日期字符串。
     */
    public static String format(TimeZone timeZone) {
        return format(DateUtils.now(), DEFAULT_DATETIME_PATTERN, timeZone, null);
    }
    
    /**
     * 用默认的时区、指定的模式和语言环境格式化当前日期。
     * 
     * @param pattern 格式化模式。
     * @param locale 语言环境。
     * @return 默认的时区、指定的模式和语言环境格式化的当前日期字符串。
     */
    public static String format(String pattern, Locale locale) {
        return format(DateUtils.now(), pattern, TimeZone.getDefault(), locale);
    }
    
    /**
     * 用默认的语言环境、指定的模式和时区格式化当前日期。
     * 
     * @param pattern 格式化模式。
     * @param timeZone 时区。
     * @return 默认的语言环境、指定的模式和时区格式化的当前日期字符串。
     */
    public static String format(String pattern, TimeZone timeZone) {
        return format(DateUtils.now(), pattern, timeZone, null);
    }
    
    /**
     * 特殊的日期格式化。格式化使用以下规则：
     * <ul>
     * <li>指定的日期与当前日期为同一天，则返回格式是：HH:mm</li>
     * <li>指定的日期与当前日期在同一年内，则返回格式是：MM-dd HH:mm</li>
     * <li>指定的日期与当前日期不在同一年内，则返回格式是：yyyy-MM-dd HH:mm</li>
     * </ul>
     * 
     * @param millis 给定的日期/时间的毫秒数。
     * @return 返回特定规则下的日期格式化形式。
     */
    public static String formatSpecialis(long millis) {
        return formatSpecial(new Date(millis), null, null);
    }
    
    /**
     * 特殊的日期格式化。格式化使用以下规则：
     * <ul>
     * <li>指定的日期与当前日期为同一天，则返回格式是：HH:mm</li>
     * <li>指定的日期与当前日期在同一年内，则返回格式是：MM-dd HH:mm</li>
     * <li>指定的日期与当前日期不在同一年内，则返回格式是：yyyy-MM-dd HH:mm</li>
     * </ul>
     * 
     * @param date 给定的日期/时间。
     * @return 返回特定规则下的日期格式化形式。
     */
    public static String formatSpecial(Date date) {
        return formatSpecial(date, null, null);
    }
    
    /**
     * 特殊的日期格式化。如果格式化模式为{@code null} 或空字符串，则默认使用以下规则：
     * <ul>
     * <li>指定的日期与当前日期为同一天，则返回格式是：HH:mm</li>
     * <li>指定的日期与当前日期在同一年内，则返回格式是：MM-dd HH:mm</li>
     * <li>指定的日期与当前日期不在同一年内，则返回格式是：yyyy-MM-dd HH:mm</li>
     * </ul>
     * 
     * @param date 给定的日期/时间。
     * @param pattern 给定的格式化模式。
     * @param locale 语言环境。
     * @return 返回特定规则下的日期格式化形式。
     */
    public static String formatSpecial(Date date, String pattern, Locale locale) {
        if(StringUtils.isBlank(pattern)) {
            Date now = DateUtils.now();
            if(DateUtils.isSameDay(now, date)) {
                return format(date, "HH:mm");
            } else if(DateUtils.isSameYear(now, date)) {
                return format(date, "MM-dd HH:mm");
            } else {
                return format(date, "yyyy-MM-dd HH:mm");
            }
        } else {
            return format(date, pattern, locale);
        }
    }

    public static Date formatStringToDate(String strTime) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
            return sdf.parse(strTime);
        } catch (ParseException pe) {
            return  new Date();
        }
    }

    public static Date formatStringToDate_date(String strTime) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(DEFAULT_DATETIME_PATTERN_DATE);
            return sdf.parse(strTime);
        } catch (ParseException pe) {
            return  new Date();
        }
    }


    public static Date formatStringToDate(String strTime, String pattern) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat(pattern);
            return sdf.parse(strTime);
        } catch (ParseException pe) {
            return  new Date();
        }
    }


    /**
     * nowDate得到num天后的日期
     * @param num
     * @return
     */
    public static String getDate(String nowDate, int num) {
        long time = formatStringToDate_date(nowDate).getTime() + (1000L * 60 * 60 * 24 * num);
        String pattern = "yyyy-MM-dd";
        Date date = new Date();
        if (time > 0) {
            date.setTime(time);
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
