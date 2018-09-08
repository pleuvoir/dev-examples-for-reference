package io.github.pleuvoir.lang;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * 安全的使用空值
 * @author pleuvoir
 * 
 */
public class NullUtil {

	/**
	 * 若为null或""，返回：""，否则返回它本身
	 * @param s
	 * @return
	 */
	public static String elseDefaultEmpty(String s) {
		return StringUtils.isBlank(s) ? StringUtils.EMPTY : s;
	}

	/**
	 * 若为null或""，返回默认值，否则返回它本身
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static String elseDefault(String s, String defaultValue) {
		return StringUtils.isBlank(s) ? defaultValue : s;
	}

	/**
	 * 若为null，返回：0，否则返回它本身
	 * @param integer
	 * @return
	 */
	public static Integer elseDefaultZero(Integer integer) {
		return null == integer ? new Integer(0) : integer;
	}

	/**
	 * 若为null，返回默认值，否则返回它本身
	 * @param integer
	 * @param defaultValue
	 * @return
	 */
	public static Integer elseDefault(Integer integer, Integer defaultValue) {
		return null == integer ? defaultValue : integer;
	}
	
	/**
	 * 若为null，返回：0，否则返回它本身
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal elseDefaultZero(BigDecimal bigDecimal) {
		return null == bigDecimal ? new BigDecimal(0) : bigDecimal;
	}

	/**
	 * 若为null，返回默认值，否则返回它本身
	 * @param bigDecimal
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal elseDefault(BigDecimal bigDecimal, BigDecimal defaultValue) {
		return null == bigDecimal ? defaultValue : bigDecimal;
	}
	
	
}