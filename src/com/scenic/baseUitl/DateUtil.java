package com.scenic.baseUitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 购买monthCount个月后的时间
	 * 
	 * @param date
	 * @param monthCount
	 * @return
	 */
	public static Date monthAdd(Date date, int monthCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthCount);
		Date date2 = calendar.getTime();
		return date2;
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 购买dayCount多天后的时间
	 * 
	 * @param date
	 * @param dayCount
	 *            如果为负数，则是回滚
	 * @return
	 */
	public static Date dayAdd(Date date, int dayCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dayCount);
		Date date2 = calendar.getTime();
		return date2;
	}

	/**
	 * DateTime 转换为 String "yyyy-MM-dd HH:mm:ss"
	 */
	public static String dateTimeToString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * @param date
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date StringToDateTime(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(date);
		} catch (ParseException e) {
			// throw new DateParaseException("时间转换导出错!");
			return null;
		}
	}

	/**
	 * @param date
	 *            yyyy-MM-dd
	 * @return
	 */
	public static Date StringToDate(String date) {
		if (date == null || (date = date.trim()).isEmpty())
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(date);
		} catch (ParseException e) {
			// throw new DateParaseException("时间转换导出错!");
			return null;
		}
	}

	public static Date ddHHmmssToDate(Date date, String ddHHmmss) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dd = calendar.get(Calendar.DATE);
		String[] str1 = ddHHmmss.split(" ");
		int dd2 = Integer.valueOf(str1[0]);
		if (dd2 < dd) {
			calendar.add(Calendar.MONTH, 1);
		}
		String[] str = str1[1].split(":");
		calendar.set(Calendar.DATE, dd2);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(str[0]));
		calendar.set(Calendar.MINUTE, Integer.valueOf(str[1]));
		calendar.set(Calendar.SECOND, Integer.valueOf(str[2]));
		return calendar.getTime();
	}

	/**
	 * 
	 * 获取当前日期的前一天
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		String daybefore = sdf.format(c.getTime());
		return daybefore;
	}

	/**
	 * @param String
	 *            yyyy-MM-dd
	 * @return Date yyyy-MM-dd 00:00:00
	 */
	public static Date getDayTime(String date) {
		date = date + " 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date day = sdf.parse(date);
			return day;
		} catch (ParseException e) {
			// throw new DateParaseException("时间转换导出错!");
			return null;
		}
	}

	/**
	 * 获取当前日期
	 * 
	 * @return Date yyyy-MM-dd
	 * 
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/**
	 * 
	 * 获取当前日期的后一天
	 */
	public static String getSpecifiedDayLater(String specifiedDay) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		String daybehind = sdf.format(c.getTime());
		return daybehind;
	}
}
