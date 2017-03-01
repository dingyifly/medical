package com.medical.common.utils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class CommonUtil {
	
	public final static String TIME_FORMAT = "yyyy-MM-dd";
	public final static String TIME_FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 判断是否为空，适用于字符串、集合、数组；为空返回true，不为空返回false;
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static boolean isEmpty(Object obj) {
		if (obj == null) return true;
		if (obj instanceof CharSequence) {
			return ((CharSequence)obj.toString().trim()).length() == 0;
		} else if (obj instanceof String) {
			return obj != null && !"".equals(((String) obj).trim()) ? false : true;
		} else if (obj instanceof Collection) {
			return ((Collection)obj).isEmpty();
		} else if (obj instanceof Map) {
			return ((Map)obj).isEmpty();
		} else if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		return false;
	}
	
	/**
	 * 判断是否不为空，适用于字符串、集合、数组；为空返回false，不为空返回true;
	 * @param obj
	 * @return
	 */
	public static boolean notEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * 获取当前日期：yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDate() {
		return getDateStringByDateAndPattern(new Date(), TIME_FORMAT);
	}
	
	/**
	 * 获取当前详细时间：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateDetail() {
		return getDateStringByDateAndPattern(new Date(), TIME_FORMAT_DETAIL);
	}
	
	/**
	 * 根据日期字符串获取日期
	 * @param dateStr 日期字符串
	 * @param pattern 日期字符串格式 如："yyyy_MM-dd" 如果不传，默认为"yyyy_MM-dd"
	 * @return
	 */
	public static Date getDate(String dateStr, String...pattern) {
		String p = null;
		if (CommonUtil.notEmpty(pattern)) {
			p = pattern[0];
		} else {
			p = TIME_FORMAT;
		}
		return getDateByStringAndPatter(dateStr, p);
	}
	
	/**
	 * 根据日期获取指定格式字符串
	 * @param date 日期
	 * @param pattern 日期字符串格式 如："yyyy-MM-dd" 如果不传，默认为"yyyy-MM-dd"
	 * @return
	 */
	public static String getDateStr(Date date, String...pattern) {
		String p = null;
		if (CommonUtil.notEmpty(pattern)) {
			p = pattern[0];
		} else {
			p = TIME_FORMAT;
		}
		return getDateStringByDateAndPattern(date, p);
	}
	
	/**
	 * 根据日期和给定日期格式返回给定格式的日期字符串
	 * @param d
	 * @param pattern
	 * @return
	 */
	public static String getDateStringByDateAndPattern(Date d, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}
	
	/**
	 * 根据字符串和给定日期格式返回Date型
	 * @param dStr
	 * @param pattern
	 * @return
	 */
	public static Date getDateByStringAndPatter(String dStr, String pattern) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			d = sdf.parse(dStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

}
