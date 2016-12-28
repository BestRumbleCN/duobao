package team.wuxie.crowdfunding.util.date;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static java.util.Calendar.*;

/**
 * 一个继承自{@link org.apache.commons.lang3.time.DateUtils} 的日期处理工具类。
 * <p />
 * Commons-Lang文档详见：<a href="http://commons.apache.org/lang/api-2.6/index.html">Apache
 * Commons Lang Javadoc</a>
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * Number of milliseconds in a standard week.
     */
    public static final long MILLIS_PER_WEEK = 7 * MILLIS_PER_DAY;

    /**
     * 返回系统的当前时间。
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 将指定的 {@code date} 转换成 {@code Calendar} 类型。
     * 
     * @param date 指定日期。
     * @return 返回指定日期的 {@code Calendar}。
     */
    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 返回给定日期的同一天的时间范围对，起始为同一天的 0点0分0秒， 结束为同一天的 23点59分59秒999毫秒。
     * 
     * <pre>
     * {@code Pair.left} - {@code date} 00:00:00 000
     * {@code Pair.right} - {@code date} 23:59:59 999
     * </pre>
     * 
     * @param date 指定的日期。
     * @return 给定日期的同一天的时间范围对。
     */
    public static Pair<Date, Date> getOneDayPair(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
        Date startDate = cal.getTime();
        cal.set(HOUR_OF_DAY, 23);
        cal.set(MINUTE, 59);
        cal.set(SECOND, 59);
        cal.set(MILLISECOND, 999);
        Date endDate = cal.getTime();
        return Pair.of(startDate, endDate);
    }

    /**
     * 返回指定日期的时间清零后的日期对象。
     * <p />
     * 如果给定的日期是 {@code 2011-03-08 23:14:19 978}，则返回 {@code 2011-03-08 00:00:00 000}。
     * 
     * @param date 指定的日期。
     * @return 指定日期的时间清零后的日期对象。
     */
    public static Date getClearTimeDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 返回指定日期前或后 {@code dayAmount} 天的按先后顺序排列的日期列表。
     * <p />
     * 如果 {@code containsDate == true}，则返回的日期列表会包含指定的日期； 如果 {@code containsDate == false}
     * ，则返回的日期列不会包含指定的日期。
     * 
     * <pre>
     * 如果指定的日期是：{@code 2011-03-08}，{@code dayAmount == 7}，则返回：
     * containsDate == true 时包含 [2011-03-08]
     * datelist 0 - 2011-03-09
     * datelist 1 - 2011-03-10
     * datelist 2 - 2011-03-11
     * datelist 3 - 2011-03-12
     * datelist 4 - 2011-03-13
     * datelist 5 - 2011-03-14
     * datelist 6 - 2011-03-15
     * </pre>
     * 
     * @param date 指定日期。
     * @param dayAmount 前（负数）或后（正数）的天数。
     * @param containsDate 返回的日期列表是否包含指定的日期。
     * @return 指定日期前或后 {@code dayAmount} 天的按先后顺序排列的日期列表。
     */
    public static List<Date> getAboutDateList(Date date, int dayAmount, boolean containsDate) {
        List<Date> dateList = new LinkedList<Date>();
        if (dayAmount == 0) {
            dateList.add(date);
            return dateList;
        }
        Date currentDate = null, endDate = null;
        if (dayAmount > 0) {
            currentDate = containsDate ? date : DateUtils.addDays(date, 1);
            ;
            endDate = DateUtils.addDays(date, dayAmount + 1);
        } else {
            currentDate = DateUtils.addDays(date, dayAmount);
            endDate = date;
        }
        while (!isSameDay(currentDate, endDate)) {
            dateList.add(currentDate);
            currentDate = addDays(currentDate, 1);
        }
        return dateList;
    }

    /**
     * 根据规则，为当前日期对象添加或减去指定的天数。例如，要从给定的时间减去 5 天， 可以通过调用以下方法做到这一点：
     * 
     * <pre>
     * java.util.Date date = DateUtil.addHour(date, -5); // date 比当前时间早 5 天
     * </pre>
     * 
     * @param amount 为日期添加的天数。
     * @return 返回添加或减去指定的天数后的新日期对象。
     */
    public static Date addDays(int amount) {
        return addDays(now(), amount);
    }

    /**
     * 设置 年、月、日、小时、分钟 和 秒 的值。保留其他字段以前的值。 并返回给定年、月、日、小时、分钟 和 秒的{@code java.util.Date} 日期对象。
     * 
     * @param year 用来设置年份的值。
     * @param month 用来设置月份的值。Month 值是基于 0 的。例如，0 表示 January。
     * @param day 用来设置天数的值。
     * @param hour 用来设置小时数的值。
     * @param minute 用来设置分钟数的值。
     * @param second 用来设置秒数的值。
     * @return 返回给定年、月、日、小时、分钟 和 秒的{@code java.util.Date} 日期对象。
     */
    public static Date createDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);
        return cal.getTime();
    }

    /**
     * 设置 年、月、日的值。保留其他字段以前的值。 并返回给定年、月、日的{@code java.util.Date}日期对象。
     * 
     * @param year 用来设置年份的值。
     * @param month 用来设置月份的值。Month 值是基于 0 的。例如，0 表示 January。
     * @param day 用来设置天数的值。
     * @return 返回给定年、月、日的{@code java.util.Date}日期对象。
     */
    public static Date createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }

    /**
     * 计算两个 {@code java.util.Date} 对象表示的时间相隔的毫秒数。
     * <ul>
     * <li>若 {@code date2.getTime() > date1.getTime()}，返回正值。</li>
     * <li>若 {@code date2.getTime() < date1.getTime()}，返回负值。</li>
     * <li>若 {@code date2.getTime() == date1.getTime()}，返回 {@code 0}。</li>
     * </ul>
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 给定两个 {@code java.util.Date} 对象示的时间相隔的毫秒数。
     */
    public static long timespace(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime());
    }

    /**
     * 计算两个 {@code java.util.Date} 对象表示的时间相隔的秒数。
     * <ul>
     * <li>若 {@code date2.getTime() > date1.getTime()}，返回正值。</li>
     * <li>若 {@code date2.getTime() < date1.getTime()}，返回负值。</li>
     * <li>若 {@code date2.getTime() == date1.getTime()}，返回 {@code 0}。</li>
     * </ul>
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 给定两个 {@code java.util.Date} 对象示的时间相隔的秒数。
     */
    public static long timespaceOfSeconds(Date date1, Date date2) {
        return timespace(date1, date2) / MILLIS_PER_SECOND;
    }

    /**
     * 计算两个 {@code java.util.Date} 对象表示的时间相隔的分钟数。
     * <ul>
     * <li>若 {@code date2.getTime() > date1.getTime()}，返回正值。</li>
     * <li>若 {@code date2.getTime() < date1.getTime()}，返回负值。</li>
     * <li>若 {@code date2.getTime() == date1.getTime()}，返回 {@code 0}。</li>
     * </ul>
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 给定两个 {@code java.util.Date} 对象示的时间相隔的分钟数。
     */
    public static BigDecimal timespaceOfMinutes(Date date1, Date date2) {
        BigDecimal diffspace = BigDecimal.valueOf(timespace(date1, date2));
        BigDecimal minutes = BigDecimal.valueOf(MILLIS_PER_MINUTE);
        return diffspace.divide(minutes, 3, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算两个 {@code java.util.Date} 对象表示的时间相隔的小时数。
     * <ul>
     * <li>若 {@code date2.getTime() > date1.getTime()}，返回正值。</li>
     * <li>若 {@code date2.getTime() < date1.getTime()}，返回负值。</li>
     * <li>若 {@code date2.getTime() == date1.getTime()}，返回 {@code 0}。</li>
     * </ul>
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 给定两个 {@code java.util.Date} 对象示的时间相隔的小时数。
     */
    public static BigDecimal timespaceOfHours(Date date1, Date date2) {
        BigDecimal diffspace = BigDecimal.valueOf(timespace(date1, date2));
        BigDecimal hours = BigDecimal.valueOf(MILLIS_PER_HOUR);
        return diffspace.divide(hours, 3, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算两个 {@code java.util.Date} 对象表示的时间相隔的天数。
     * <ul>
     * <li>若 {@code date2.getTime() > date1.getTime()}，返回正值。</li>
     * <li>若 {@code date2.getTime() < date1.getTime()}，返回负值。</li>
     * <li>若 {@code date2.getTime() == date1.getTime()}，返回 {@code 0}。</li>
     * </ul>
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 给定两个 {@code java.util.Date} 对象示的时间相隔的天数。
     */
    public static BigDecimal timespaceOfDays(Date date1, Date date2) {
        BigDecimal diffspace = BigDecimal.valueOf(timespace(date1, date2));
        BigDecimal days = BigDecimal.valueOf(MILLIS_PER_DAY);
        return diffspace.divide(days, 3, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算两个 {@code java.util.Date} 对象表示的时间相隔的星期数。
     * <ul>
     * <li>若 {@code date2.getTime() > date1.getTime()}，返回正值。</li>
     * <li>若 {@code date2.getTime() < date1.getTime()}，返回负值。</li>
     * <li>若 {@code date2.getTime() == date1.getTime()}，返回 {@code 0}。</li>
     * </ul>
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 给定两个 {@code java.util.Date} 对象示的时间相隔的星期数。
     */
    public static BigDecimal timespaceOfWeeks(Date date1, Date date2) {
        BigDecimal diffspace = BigDecimal.valueOf(timespace(date1, date2));
        BigDecimal weeks = BigDecimal.valueOf(MILLIS_PER_WEEK);
        return diffspace.divide(weeks, 3, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 检查指定的两个日期是否处于同一年份。
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 如果给定的两个日期处于同一年份，则返回{@code true}，否则{@code false}。
     */
    public static boolean isSameYear(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return isSameYear(cal1, cal2);
    }

    /**
     * 检查指定的两个日历日期是否处于同一年份。
     * 
     * @param cal1 第一个 {@code java.util.Calendar}。
     * @param cal2 第二个 {@code java.util.Calendar}。
     * @return 如果给定的两个日历日期处于同一年份，则返回{@code true}，否则{@code false}。
     */
    public static boolean isSameYear(Calendar cal1, Calendar cal2) {
        return cal1.get(YEAR) == cal2.get(YEAR);
    }

    /**
     * 检查指定的两个日期是否同年同月。
     * 
     * @param date1 第一个 {@code java.util.Date}。
     * @param date2 第二个 {@code java.util.Date}。
     * @return 如果给定的两个日期同年同月，则返回{@code true}，否则{@code false}。
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return isSameMonth(cal1, cal2);
    }

    /**
     * 检查指定的两个日历日期是否同年同月。
     * 
     * @param cal1 第一个 {@code java.util.Calendar}。
     * @param cal2 第二个 {@code java.util.Calendar}。
     * @return 如果给定的两个日历日期同年同月，则返回{@code true}，否则{@code false}。
     */
    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        return cal1.get(YEAR) == cal2.get(YEAR) && cal1.get(MONTH) == cal2.get(MONTH);
    }

    /**
     * 从给定字符串的开始解析文本，以生成一个日期。该方法不使用给定字符串的整个文本。
     * 
     * @param str 一个 {@code String}，应从其开始处进行解析。
     * @param pattern 描述日期和时间格式的模式。
     * @return {@code input} 中包含的日期字符串的 {@code java.util.Date} 对象。
     */
    public static Date parse(String str, String pattern) {
        if (StringUtils.isBlank(str))
            return null;
        if (StringUtils.isBlank(pattern))
            pattern = DateFormatUtils.DEFAULT_DATETIME_PATTERN;
        try {
            return parseDate(str, new String[] {
                pattern
            });
        } catch (ParseException ex) {
            return null;
        }
    }
    
    /**
     * 获取当前时间在今年的第几周
     * @author fly
     * @return  
     * @since
     */
    public static int currWeeKOfYear(){
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(new Date());
    	 return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 获取当前时间是星期几
     * @author fly
     * @return  
     * @since
     */
    public static int getDay(){
    	 Calendar cal = Calendar.getInstance();
    	 cal.setTime(new Date());
    	 return cal.get(Calendar.DAY_OF_WEEK);
    }
}
