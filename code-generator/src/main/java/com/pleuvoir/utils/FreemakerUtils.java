package com.pleuvoir.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pleuvoir.bean.FreemakerParamsMap;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemakerUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FreemakerUtils.class);
	
	/**
	 * 通过文件名加载模版
	 * @param ftlName
	 */
	@SuppressWarnings("deprecation")
	public static Template getTemplate(String templateName) throws Exception{
		try {
			Configuration cfg = new Configuration();  												
			cfg.setEncoding(Locale.CHINA, "utf-8");
			cfg.setDirectoryForTemplateLoading(new File(BaseUtils.getClassResources()+Constant.TEMPLATE_SOURCE_PATH));		
			Template temp = cfg.getTemplate(templateName);											
			return temp;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 根据模版生成文件
	 * @param templateName
	 * @param map
	 * @param targetFileName
	 */
	public static void printFile(String templateName,FreemakerParamsMap freemakerParams){
		try {
			File file = new File(BaseUtils.getClasspath() + Constant.TARGET_FILE_PATH + getTargetFileName(templateName));
			logger.info("文件位于:" + file.getPath());
			if (!file.getParentFile().exists()) { // 判断有没有父路径，就是判断文件整个路径是否存在
				file.getParentFile().mkdirs(); // 不存在就全部创建
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(templateName + ".ftl");
			template.process(freemakerParams, out); // 模版输出
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 获得生成文件名称
	 * @param templateName
	 * @return
	 */
	private static String getTargetFileName(String templateName) {
		String result = "";
		if (templateName.equalsIgnoreCase("entity")) {
			result = Constant.ENTITY_NAME + ".java";
		} else if (templateName.equalsIgnoreCase("dao")) {
			result = Constant.ENTITY_NAME + "Dao.java";
		} else if (templateName.equalsIgnoreCase("daoImpl")) {
			result = Constant.ENTITY_NAME + "DaoImpl.java";
		} else if (templateName.equalsIgnoreCase("service")) {
			result = Constant.ENTITY_NAME + "Service.java";
		} else if (templateName.equalsIgnoreCase("serviceImpl")) {
			result = Constant.ENTITY_NAME + "ServiceImpl.java";
		} else if (templateName.equalsIgnoreCase("controller")) {
			result = Constant.ENTITY_NAME + "Controller.java";
		} else if (templateName.equalsIgnoreCase("list")) {
			result = "list.jsp";
		} else {
		}
		return result;
	}


	
	
	 
	 /**
	  * 获取所有模版文件名
	  * @param filePath
	  * @return
	  */
	public static List<String> getFileNameList(String filePath) {
		List<String> fileNameList = new ArrayList<String>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				logger.debug("显示" + filePath + "下所有文件:" + file.getName());
				fileNameList.add(getFileNameEx(file.getName()));
			}
		}
		return fileNameList;
	}
	
	
	/**
	 * 获取无格式的文件名
	 * @param fileName
	 * @return
	 */
	public static String getFileNameEx(String fileName){
		return fileName.substring(0,fileName.lastIndexOf("."));
	}
	
}
