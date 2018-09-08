package io.github.pleuvoir.resource;

import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

public abstract class ResourceUtil {

	private static final Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

	/**
	 * 获取多个资源 <br>
	 * 路径的写法可以是：classpath:config/service.xml
	 * @param location
	 * @return
	 */
	public static Resource[] getResources(String location) {
		try {
			return ResourcePatternUtils.getResourcePatternResolver(new PathMatchingResourcePatternResolver())
					.getResources(location);
		} catch (IOException e) {
			logger.error("getResources error：{}", e);
		}
		return null;
	}

	/**
	 * 获取单个资源 <br>
	 * 路径的写法可以是：classpath:config/service.xml
	 * @param location
	 * @return
	 */
	public static Resource getResource(String location) {
		return ResourcePatternUtils.getResourcePatternResolver(new PathMatchingResourcePatternResolver())
				.getResource(location);
	}

	/**
	 * 返回流
	 * @param location
	 * @return
	 */
	public static InputStream getResourceAsStream(String location) {
		try {
			return getResource(location).getInputStream();
		} catch (IOException e) {
			logger.error("getResourceAsStream error：{}", e);
		}
		return null;
	}

}