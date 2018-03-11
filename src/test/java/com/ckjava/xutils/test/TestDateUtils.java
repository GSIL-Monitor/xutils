package com.ckjava.xutils.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.ckjava.xutils.DateUtils;

public class TestDateUtils extends DateUtils {
	
	@Test
	public void TestGetCurrentTimeStamp() {
	}
	
	@Test 
	public void testWeak() {
		System.out.println(getWeek(new Date()));
	}
	
	@Test
	public void getWeekoneAndLast() {
		Date monday = getMonday(null);
		
		System.out.println(getDateString(monday, TIMEFORMAT.DATETIME));
		
		Date currentSunday = getAssignDay(monday, 6);
		Date nextMon = getAssignDay(monday, 7);
		Date nextSun = getAssignDay(monday, 13);
		System.out.println(getDateString(currentSunday, TIMEFORMAT.DATETIME));
		System.out.println(getDateString(nextMon, TIMEFORMAT.DATETIME));
		System.out.println(getDateString(nextSun, TIMEFORMAT.DATETIME));
	}
	
	@Test
	public void getAssignDay() {
		Date zhouri = getAssignDay(new Date(), 4);
		System.out.println(getDateString(zhouri, TIMEFORMAT.DATETIME));
		
		Date last_1 = getAssignDay(zhouri, -6);
		Date last_5 = getAssignDay(zhouri, -2);
		Date next_1 = getAssignDay(zhouri, 1);
		Date next_5 = getAssignDay(zhouri, 5);
		
		System.out.println("last_1:"+last_1.getTime());
		System.out.println("last_5:"+last_5.getTime());
		
		System.out.println("last_1:"+last_1.getTime()/1000);
		System.out.println("last_5:"+last_5.getTime()/1000);
		
		System.out.println(getDateString(last_1, TIMEFORMAT.DATETIME));
		System.out.println(getDateString(last_5, TIMEFORMAT.DATETIME));
		System.out.println(getDateString(next_1, TIMEFORMAT.DATETIME));
		System.out.println(getDateString(next_5, TIMEFORMAT.DATETIME));
	}
	
	@Test
	public void getBeginDay() {
		Date date = getBeginDay(new Date());
		System.out.println(getDateString(date, TIMEFORMAT.DATETIME));
	}
	
	@Test
	public void getEndDay() {
		Date date = getEndDay(new Date());
		System.out.println(getDateString(date, TIMEFORMAT.DATETIME));
	}
	
	@Test
	public void formatTime() {
		long time = 1510552197000L;
		System.out.println(formatTime(time, TIMEFORMAT.DATETIME));
		System.out.println(formatTime(time, "MM-dd"));
	}
	
	@Test
	public void testPastDays() {
		Date currentDate = parseDate("2017-12-25");
		Date pastDate = parseDate("2017-12-22");
		Assert.assertTrue(pastDays(currentDate, pastDate) == 3);
	}
	
	@Test
	public void testPastHours() {
		Date currentDate = parseDate("2017-12-25 17:25:36");
		Date pastDate = parseDate("2017-12-25 14:55:14");
		Assert.assertTrue(pastHours(currentDate, pastDate) == 2);
	}
	
	@Test
	public void testPastMinutes() {
		Date currentDate = parseDate("2017-12-25 17:25:36");
		Date pastDate = parseDate("2017-12-25 14:55:14");
		Assert.assertTrue(pastMinutes(currentDate, pastDate) == 150);
	}
	
	@Test
	public void testFormatTime() {
		String dateTimeString = "2017-12-25 17:25:36";
		Date dateTime = parseDate(dateTimeString);
		Assert.assertTrue(formatTime(dateTime.getTime(), TIMEFORMAT.DATETIME).equals(dateTimeString));
	}
	
}
