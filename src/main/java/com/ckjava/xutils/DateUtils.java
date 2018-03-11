package com.ckjava.xutils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 将 java.util.Date 类型转成 String 类型，或者将 String 类型转成 java.util.Date 类型
 * @author ck
 *
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils implements Constants {

	/**
	 * 获取指定时间的字符串格式
	 * 
	 * @param date {@code Date} 类型
	 * @param pattern 像 yyyy-MM-dd HH:mm:ss 之类的
	 * 
	 * @return 日期格式化后的字符串
	 */
	public static String getDateString(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 * 
	 * @param date {@code Date} 类型
	 * @return 年份字符串 yyyy
	 */
	public static String getYear(Date date) {
		return getDateString(date, TIMEFORMAT.YEAR);
	}

	/**
	 *  得到当前月份字符串,格式（MM） 
	 * @param date {@code Date} 类型
	 * @return 月份字符串 格式（MM） 
	 */
	public static String getMonth(Date date) {
		return getDateString(date, TIMEFORMAT.MONTH);
	}

	/**
	 * 得到当天字符串 格式（dd）
	 * 
	 * @param date {@code Date} 类型
	 * @return 当天字符串 格式（dd）
	 */
	public static String getDay(Date date) {
		return getDateString(date, TIMEFORMAT.DAY);
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 * 
	 * @param date {@code Date} 类型
	 * @return 当前星期字符串 格式（E）星期几
	 */
	public static String getWeek(Date date) {
		return getDateString(date, TIMEFORMAT.WEEKDAY);
	}
	
	

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	
	/**
	 * 日期型字符串转化为日期 {@code Date} 类型
	 * 
	 * @param str {@code Object} 类型
	 * @return 日期 {@code Date} 类型
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), TIMEFORMAT.PATTERNS);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param currentDate 当前的日期
	 * @param pastDate 指定的日期
	 * @return 天数
	 */
	public static long pastDays(Date currentDate, Date pastDate) {
		long t = currentDate.getTime() - pastDate.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param currentDate 当前的日期
	 * @param pastDate 指定的日期
	 * @return 小时数
	 */
	public static long pastHours(Date currentDate, Date pastDate) {
		long t = currentDate.getTime() - pastDate.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param currentDate 当前的日期
	 * @param pastDate 指定的日期
	 * @return 分钟数
	 */
	public static long pastMinutes(Date currentDate, Date pastDate) {
		long t = currentDate.getTime() - pastDate.getTime();
		return t / (60 * 1000);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param time {@code Date} long 类型
	 * @param format 日期格式, 像  yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
	 * @return 日期格式的字符串
	 */
	public static String formatTime(long time, String format) {
		Date date = new Date(time);
		return getDateString(date, format);
	}
	
	/**
	 * 获取指定日期的偏移日期
	 * 
	 * @param time Date, 指定日期
	 * @param offset int, 偏移量, 单位:天
	 * @return Date 便宜后的日期
	 */
	public static Date getAssignDay(Date time, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期的当天开始时间, 精确到 00:00:00
	 * 
	 * @param time 指定日期 , 类型为 {@code Date}
	 * @return 当天开始时间 , 类型为 {@code Date}
	 */
	public static Date getBeginDay(Date time) {
		String dateStr = getDateString(time, TIMEFORMAT.DATE).concat(" 00:00:00");
		try {
			return parseDate(dateStr, TIMEFORMAT.DATETIME);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取指定日期的当天结束时间,精确到 23:59:59
	 * 
	 * @param time 指定日期 , 类型为 {@code Date}
	 * @return 当天开始时间, 类型为 {@code Date}
	 */
	public static Date getEndDay(Date time) {
		String dateStr = getDateString(time, TIMEFORMAT.DATE).concat(" 23:59:59");
		try {
			return parseDate(dateStr, TIMEFORMAT.DATETIME);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取指定日期的周一时间
	 * 
	 * @param currentDate 指定日期, 如果为空表示当前时间, 类型为 {@code Date}
	 * @return 指定日期的周一时间, 类型为 {@code Date}
	 */
	public static Date getMonday(Date currentDate) {
		Calendar can = Calendar.getInstance(Locale.CHINA);
		if (currentDate != null) {
			can.setTime(currentDate);
		}
		can.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		return can.getTime();
	}
	
}
