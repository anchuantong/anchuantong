
package com.act.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class DateUtil {

	public static long currentTime = System.currentTimeMillis();

	private static long lastMidnightTime = 0;

	private static long updateInterval = 1000;

	public static final long SECOND = 1000L;

	public static final long MINUTE = 60000L;

	public static final long HOUR = 3600000L;

	public static final long DAY = 86400000L;

	public static final long WEEK = 7*24*3600*1000L;

	public static final long MONTH = 30*24*3600*1000L;

	private static Map dateFormatCache = new HashMap();

	private static SimpleDateFormat httpDateFormat;

	static {
		httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyyy HH:mm:ss z", Locale.US);
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public static String asHtml() {
		return asHtml(new Date());
	}

	public static String asHtml(Date d) {
		return asHtml(d, TimeZone.getDefault());
	}

	public static String asHtml(Date date, TimeZone timeZone) {
		if (date == null) {
			return "";
		}
		String key = timeZone.getID();
		DateFormat formatter = (DateFormat) dateFormatCache.get(key);
		if (formatter == null) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			formatter.setTimeZone(timeZone);
			dateFormatCache.put(key, formatter);
		}
		synchronized (formatter) {
			return formatter.format(date);
		}
	}

	public static String asShortString(Date date, TimeZone timeZone) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(timeZone);
		return formatter.format(date);
	}

	public static String asShortString1(Date date, TimeZone timeZone) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		formatter.setTimeZone(timeZone);
		return formatter.format(date);
	}

	public static Date asDate(String time) {
		if (time == null || time.length() < 19) {
			return new Date();
		}
		time = time.replaceAll(":", "-");
		time = time.replaceAll(" ", "-");
		String strs[] = time.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, StringUtil.asInteger(strs[0], 1));
		if (strs[1].startsWith("0")) {
			cal.set(Calendar.MONTH, StringUtil.asInteger(strs[1].substring(1), 1) - 1);
		} else {
			cal.set(Calendar.MONTH, StringUtil.asInteger(strs[1], 1) - 1);
		}
		cal.set(Calendar.DAY_OF_MONTH, StringUtil.asInteger(strs[2], 1));
		cal.set(Calendar.HOUR_OF_DAY, StringUtil.asInteger(strs[3], 1));
		cal.set(Calendar.MINUTE, StringUtil.asInteger(strs[4], 1));
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static String formatHttpDate(Date date) {
		synchronized (httpDateFormat) {
			return httpDateFormat.format(date);
		}
	}
	
	public static Date getTodayBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getYesterday() {
		
		return new Date(getTodayBegin().getTime()-DAY);
	}
	
	public static Date getMonthBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getWeekBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 2);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getNextDate(Date date,int days) {
		return new Date((date.getTime() +(long) days*DAY));
	}
	public static void main(String[] args) {
		// System.out.println(DateUtil.asShortString1(new Date(),
		// TimeZone.getDefault()));
		System.out.println(DateUtil.asHtml(DateUtil.asDate("2006-9-14 15:36:15.0")));
		System.out.println("getToday:"+getTodayBegin());
		System.out.println("getMonthBegin:"+getMonthBegin());
		System.out.println("getWeekBegin:"+getWeekBegin());
		System.out.println(StringUtil.toUpperCase("user_info"));
		System.out.println("getYesterday:"+getYesterday());
	}

}
