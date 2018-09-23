package io.github.pleuvoir.chapter7.conf;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * {@link org.springframework.core.env.Environment} 包装类，提供了更为方便的属性获取方法。
 * @author pleuvoir
 *
 */
public class EnvironmentWrapper implements Environment, EnvironmentAware {

	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	/**
	 * 获取 string 类型的属性值，当不存在时抛出 IllegalStateException
	 * @param key	属性名
	 * @return
	 */
	public String getString(String key) {
		return getRequiredProperty(key);
	}
	
	/**
	 * 获取 string 类型的属性值，当不存在时返回默认值
	 * @param key			属性名
	 * @param defaultVal	默认值
	 * @return
	 */
	public String getString(String key, String defaultVal) {
		return getProperty(key, defaultVal);
	}
	
	/**
	 * 获取 Integer 类型的属性值，当不存在时抛出 IllegalStateException
	 * @param key	属性名
	 * @return
	 */
	public String getInteger(String key) {
		return getRequiredProperty(key);
	}
	
	/**
	 * 获取 Integer 类型的属性值，当不存在时返回默认值
	 * @param key			属性名
	 * @param defaultVal	默认值
	 * @return
	 */
	public Integer getInteger(String key, Integer defaultVal) {
		return getProperty(key, Integer.class, defaultVal);
	}
	
	/**
	 * 获取 Long 类型的属性值，当不存在时抛出 IllegalStateException
	 * @param key	属性名
	 * @return
	 */
	public Long getLong(String key) {
		return getRequiredProperty(key, Long.class);
	}
	
	/**
	 * 获取 Long 类型的属性值，当不存在时返回默认值
	 * @param key			属性名
	 * @param defaultVal	默认值
	 * @return
	 */
	public Long getLong(String key, Long defaultVal) {
		return getProperty(key, Long.class, defaultVal);
	}
	
	/**
	 * 获取 Double 类型的属性值，当不存在时抛出 IllegalStateException
	 * @param key	属性名
	 * @return
	 */
	public Double getDouble(String key) {
		return getRequiredProperty(key, Double.class);
	}
	
	/**
	 * 获取 Double 类型的属性值，当不存在时返回默认值
	 * @param key			属性名
	 * @param defaultVal	默认值
	 * @return
	 */
	public Double getDouble(String key, Double defaultValue) {
		return getProperty(key, Double.class, defaultValue);
	}
	
	/**
	 * 获取 Boolean 类型的属性值，当不存在时抛出 IllegalStateException
	 * @param key	属性名
	 * @return
	 */
	public Boolean getBoolean(String key) {
		return getRequiredProperty(key, Boolean.class);
	}
	
	/**
	 * 获取 Boolean 类型的属性值，当不存在时返回默认值
	 * @param key			属性名
	 * @param defaultVal	默认值
	 * @return
	 */
	public Boolean getBoolean(String key, Boolean defaultValue) {
		return getProperty(key, Boolean.class, defaultValue);
	}
	
	
	//// ###  default environment interface impl
	
	@Override
	public boolean containsProperty(String key) {
		return environment.containsProperty(key);
	}

	@Override
	public String getProperty(String key) {
		return environment.getProperty(key);
	}

	@Override
	public String getProperty(String key, String defaultValue) {
		return environment.getProperty(key, defaultValue);
	}

	@Override
	public <T> T getProperty(String key, Class<T> targetType) {
		return environment.getProperty(key, targetType);
	}

	@Override
	public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
		return environment.getProperty(key, targetType, defaultValue);
	}

	@Override
	public String getRequiredProperty(String key) throws IllegalStateException {
		return environment.getRequiredProperty(key);
	}

	@Override
	public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
		return environment.getRequiredProperty(key, targetType);
	}

	@Override
	public String resolvePlaceholders(String text) {
		return environment.resolvePlaceholders(text);
	}

	@Override
	public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
		return environment.resolvePlaceholders(text);
	}

	@Override
	public String[] getActiveProfiles() {
		return environment.getActiveProfiles();
	}

	@Override
	public String[] getDefaultProfiles() {
		return environment.getDefaultProfiles();
	}

	@Override
	public boolean acceptsProfiles(String... profiles) {
		return environment.acceptsProfiles(profiles);
	}
	
}
