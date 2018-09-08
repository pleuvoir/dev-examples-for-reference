package io.github.pleuvoir.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class DateTimeFormat {
	/** yyyy-MM-dd HH:mm:ss */
	public static final DateTimeFormat DATETIME = new DateTimeFormat("yyyy-MM-dd HH:mm:ss");
	/** yyyy-MM-dd */
	public static final DateTimeFormat DATE = new DateTimeFormat("yyyy-MM-dd");
	/** HH:mm:ss */
	public static final DateTimeFormat TIME = new DateTimeFormat("HH:mm:ss");
	/** yyyyMMddHHmmss */
	public static final DateTimeFormat DATETIME_COMPACT = new DateTimeFormat("yyyyMMddHHmmss");
	/** yyyyMMdd */
	public static final DateTimeFormat DATE_COMPACT = new DateTimeFormat("yyyyMMdd");
	/** HHmmss */
	public static final DateTimeFormat TIME_COMPACT = new DateTimeFormat("HHmmss");

	private String pattern;
	private DateTimeFormatter formatter;

	private DateTimeFormat(String pattern){
		this.pattern = pattern;
		this.formatter = DateTimeFormatter.ofPattern(pattern);
	}


	public String getPattern(){
		return this.pattern;
	}

	public String format(TemporalAccessor temporal){
		return formatter.format(temporal);
	}

	public TemporalAccessor parse(String date){
		return formatter.parse(date);
	}

	public Date parseToDate(String date) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.parse(date);
	}


	public String formatFromDate(Date date){
		return new SimpleDateFormat(pattern).format(date);
	}

}
