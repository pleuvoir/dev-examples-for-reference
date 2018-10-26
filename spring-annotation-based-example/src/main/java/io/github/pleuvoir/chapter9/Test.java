package io.github.pleuvoir.chapter9;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Test {

	private Properties properties;
	private String location;

	public void setLocation(String location) throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		this.location = location;
		Resource resource = resolver.getResource(location);
		Properties properties = PropertiesLoaderUtils.loadProperties(resource);
	}
}
