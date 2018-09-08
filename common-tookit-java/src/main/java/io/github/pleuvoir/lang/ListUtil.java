package io.github.pleuvoir.lang;

import java.util.List;

import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

/**
 * 集合操作工具类
 * @author pleuvoir
 * 
 */
public class ListUtil {
	
	/**
	 * 将字符串以分隔符分割 并存入ArrayList中
	 * @param s 待分割的字符串  如：a，b，c，d
	 * @param separator 分隔符
	 * @return
	 */
	public static List<String> toArrayList(String s,String separator){
		return Lists.newArrayList(StringUtils.split(s, separator));
	}
	
}
