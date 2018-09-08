package io.github.pleuvoir.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {

	/**
	 * 时间戳转日期标准年月日时分秒字符串
	 * @param at
	 * @return
	 */
	public static String clearDate(Integer at) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format((long) at * 1000);
	}
	
}
