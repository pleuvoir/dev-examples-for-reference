package io.github.pleuvoir.controller;

import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Value;

import io.github.pleuvoir.model.Page;
import io.github.pleuvoir.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

	@Value("${base.page.package}")
	String BASE_PAGE_PACKAGE;
	
	
	/**
	 * 获取当前环境的请求主域名
	 */
	public String getCurrentEndPoint(){
		return ServletUtil.getSessionAttr("url").trim();
	}
	

	/**
	 * 通过反射生成页面对象 <br>
	 * 如果未找到该对象则会生成一个空的页面
	 * @param className  需要生成页面的对象名称
	 */
	public Page getPage(String className) {
		try {
			Class<?> clazz = Class.forName(BASE_PAGE_PACKAGE + className);
			Method method = clazz.getMethod("build");
			method.setAccessible(true);
			Page page = (Page) method.invoke(clazz.newInstance());
			return page;
		} catch (Exception e) {
			log.error("生成页面异常：{}", e);
		}
		return new Page();
	}
	
}
