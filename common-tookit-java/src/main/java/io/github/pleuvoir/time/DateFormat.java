package io.github.pleuvoir.time;

import java.text.SimpleDateFormat;

/**
 * 日期格式的枚举
 * @author pleuvoir
 *
 */
public enum DateFormat {

	/** 默认时间日期格式：yyyy-MM-dd HH:mm:ss */
    DATETIME_DEFAULT("yyyy-MM-dd HH:mm:ss"),
    
    DATETIME_DEFAULT_SHORT_YEAR("yy-MM-dd HH:mm:ss"),
    
    /** 默认日期格式：yyyy-MM-dd */
    DATE_DEFAULT("yyyy-MM-dd"),
    /** 默认时间格式：HH:mm:ss */
    TIME_DEFAULT("HH:mm:ss"),
    /** 紧凑的日期时间格式：yyyyMMddHHmmss */
    DATETIME_COMPACT("yyyyMMddHHmmss"),
    /** 紧凑的日期格式：yyyyMMdd */
    DATE_COMPACT("yyyyMMdd"),
    /** 紧凑的时间格式：HHmmss */
    TIME_COMPACT("HHmmss");
    
    
    private String pattern;
    
    private DateFormat(String pattern){
        this.pattern = pattern;
    }
    
    /**
     * 获取格式字符串
     * @return
     */
    public String getPartten(){
        return this.pattern;
    }
    
    /**
     * 获取一个新的{@link SimpleDateFormat}对象
     * @return
     */
    public SimpleDateFormat get(){
        return new SimpleDateFormat(this.pattern);
    }
}
