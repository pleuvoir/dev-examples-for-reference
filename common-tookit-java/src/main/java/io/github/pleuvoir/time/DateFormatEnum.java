package io.github.pleuvoir.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public enum DateFormatEnum {

	/**
	 * yyyy-MM-dd
	 */
	DATE_DEFAULT("yyyy-MM-dd"),

	/**
	 * yyyyMMdd
	 */
	DATE_COMPACT("yyyyMMdd"),

	/**
	 * HH:mm:ss
	 */
	TIME_DEFAULT("HH:mm:ss"),

	/**
	 * yyyy-MM-dd HH:mm
	 */
	NOT_SS_DEFAULT("yyyy-MM-dd HH:mm"),

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	DATETIME_DEFAULT("yyyy-MM-dd HH:mm:ss"),

	/**
	 * yyyyMMddHHmmss
	 */
	DATETIME_COMPACT("yyyyMMddHHmmss"),

	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	DATETIME_MILLISECOND("yyyy-MM-dd HH:mm:ss:SSS"),

	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	DATETIME_MILLISECOND_1("yyyy-MM-dd HH:mm:ss.SSS"),

	/**
	 * yyyyMMddHHmmssSSS
	 */
	DATETIME_MILLISECOND_COMPACT("yyyyMMddHHmmssSSS"),

	/**
	 * HHmmss
	 */
	TIME_COMPACT("HHmmss");

	private final String pattern;

	private DateFormatEnum(String pattern) {
		this.pattern = pattern;
	}

	public String format(TemporalAccessor temporal) {
		if (temporal == null)
			throw new IllegalArgumentException();
		return DateTimeFormatter.ofPattern(pattern).format(temporal);
	}

	public String format(Date date) {
		if (date == null)
			throw new IllegalArgumentException();
		return new SimpleDateFormat(pattern).format(date);
	}

	public LocalDateTime parse(String datetime) {
		if (StringUtils.isBlank(datetime))
			throw new IllegalArgumentException();
		return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(pattern));
	}

	public Date parseToDate(String datetime) throws ParseException {
		if (StringUtils.isBlank(datetime))
			throw new IllegalArgumentException();
		return new SimpleDateFormat(pattern).parse(datetime);
	}

	public Date convert(TemporalAccessor temporal) {
		Date date;
		try {
			date = parseToDate(format(temporal));
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		return date;
	}

	public LocalDateTime convert(Date date) {
		return parse(format(date));
	}

	public String getPattern() {
		return pattern;
	}

}
