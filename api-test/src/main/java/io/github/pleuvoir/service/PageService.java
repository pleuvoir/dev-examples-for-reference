package io.github.pleuvoir.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import io.github.pleuvoir.model.Column;

public interface PageService {

	/**
	 * 初始化页面元素、每一行的数据
	 * @return
	 */
	List<Column> init();

	/**
	 * 获取页面描述
	 * @return
	 */
	String getDescription();

	/**
	 * 获取页面标题
	 * @return
	 */
	String getTitle();
	
	/**
	 * 获取接口路径
	 * @return
	 */
	String getPath();
	
	
	
	@Target({ ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Info {
		/**
		 * 接口访问路径
		 */
		String path();
		/**
		 * 标题
		 */
		String title();
		/**
		 * 描述
		 */
		String desc();
	}

}
