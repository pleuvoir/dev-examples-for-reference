package io.github.pleuvoir.lang;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils{

	/** 分隔符 */
	public static final String SEPARATOR = "_";
	
	/** 空行 */
	public static final String BLANK_LINE = " ";
	
	/**
	 * 合并多个字符串
	 * @param args
	 * @return
	 */
	public static String of(String... args) {
		StringBuilder builder = new StringBuilder();
		for (String s : args) {
			builder.append(s);
		}
		return builder.toString();
	}
	
	
	/**
	 * 合并多个字符串，中间以分隔符拼接
	 * @param separator 分隔符
	 * @param args
	 * @return
	 */
	public static String separatorOf(String separator, String... args) {
		StringBuilder builder = new StringBuilder();
		for (String s : args) {
			builder.append(s).append(separator);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	
	
	/**
	 * 将list用separator拼接
	 * @param list 
	 * @param separator 分割符
	 * @return
	 */
	public static String appendBySeparator(List<String> list,String separator){
		StringBuilder sb = new StringBuilder();
		list.forEach(s -> sb.append(s).append(separator));
		return sb.deleteCharAt(sb.length() - 1).toString().toLowerCase();
	}
	
	
	/**
	 * 首字母大写
	 * @param s 字符串
	 * @return
	 */
	public static String firstToUpperCase(String s) {
		return StringUtils.substring(s, 0, 1).toUpperCase() + StringUtils.substring(s, 1);
	}
	
	
	/**
	 * 首字母小写
	 * @param s 字符串
	 * @return
	 */
	public static String firstToLowerCase(String s) {
		return StringUtils.substring(s, 0, 1).toLowerCase() + StringUtils.substring(s, 1);
	}
	
	
	/**
	 * 转换为驼峰命名  HELLO_WORLD -> helloWorld
	 * @param s 字符串 例如：HELLO_WORLD
	 * @return
	 */
	public static String toCamelCase(String s) {
		StringBuilder builder = new StringBuilder();
		for (String str : ListUtil.toArrayList(s, SEPARATOR)) {
			builder.append(firstToUpperCase(str.toLowerCase()));
		}
		return firstToLowerCase(builder.toString());
	}
	
}
