package com.pleuvoir.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class BaseUtils {

	private static final char SEPARATOR = '_';
	
	
	public static boolean isBlank(String s){
		return null == s || "".equals(s);
	}
	
	
	/**
	 * 将list用separator拼接
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String appendBySeparator(List<String> list,String separator){
		StringBuffer sb = new StringBuffer();
		list.forEach(s -> sb.append(s).append(separator));
		return sb.deleteCharAt(sb.length()-1).toString().toLowerCase();
	}
	
	
	/**
	 * 首字母小写
	 * @param string
	 * @return
	 */
	public static String firstToLower(String s){
		return s.substring(0, 1).toLowerCase()+s.substring(1);
	}
	
	
	/**
	 * 首字母大写
	 * @param s
	 * @return
	 */
	public static String firstToUpper(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	
	/**
	 * 列名中是否包含 "_" 如果包含则返回最后_后面的字符
	 * @return
	 */
	public static String exist_(String columnName){
		int _index = columnName.lastIndexOf(SEPARATOR);
		if(_index != -1){
			return columnName.substring(_index+ 1, columnName.length());
		}
		return columnName;
	}
	
	
	
	
	/**
	 * toCamelCase("hello_world") == "helloWorld" 
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	
    /**
	 * toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return firstToUpper(s);
    }
  
    
	/**获取classpath1
	 * @return
	 */
	public static  String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		try {
			path = URLDecoder.decode(path, "utf-8");
			if(path.indexOf(":") != 1){
				path = File.separator + path;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**获取classpath2
	 * @return
	 */
	public static String getClassResources(){
		String path =  (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		try {
			path = URLDecoder.decode(path, "utf-8");
			if(path.indexOf(":") != 1){
				path = File.separator + path;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return path;
	}

	

}
