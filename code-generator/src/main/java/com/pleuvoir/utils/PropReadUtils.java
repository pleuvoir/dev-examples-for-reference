package com.pleuvoir.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropReadUtils {
	
    private static Logger logger = LoggerFactory.getLogger(PropReadUtils.class);  

	private static Properties prop = new Properties();
	
	static{
		 try {
			prop.load(ClassLoader.getSystemResourceAsStream("config.properties")); 
		} catch (Exception e) {
			prop =  null;
	        logger.error("config.properties文件未找到");  
		}
	}
	
	public static String getProperString(String key){
		if(BaseUtils.isBlank(prop.getProperty(key))){
			logger.warn(key+"未找到值");
			return null;
		}
		return prop.getProperty(key).trim();
	}
	
	public static String getProperString(String key,String defaultValue){
		if(BaseUtils.isBlank(prop.getProperty(key))){
			logger.warn(key+"未找到值将使用默认值:"+defaultValue);
			return defaultValue;
		}
		return prop.getProperty(key).trim();
	}
	
	
}
