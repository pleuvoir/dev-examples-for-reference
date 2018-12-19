package io.github.pleuvoir.chapter07.location;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;

import io.github.pleuvoir.chapter07.wrap.PropertiesWrap;

public class DefaultAppInfoService implements AppInfoService {

	private String location;

	@Override
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public void show() throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Assert.hasText(location, "location must non-null");
		Resource resource = resolver.getResource(location);
		Properties pro = PropertiesLoaderUtils.loadProperties(resource);
		
		PropertiesWrap propertiesWrap = new PropertiesWrap(PropertiesLoaderUtils.loadProperties(new DefaultResourceLoader().getResource(location)));
		System.out.println(propertiesWrap.getInteger("example.version"));
		
		PropertiesWrap proWrap = new PropertiesWrap(pro);
		System.out.println("proWrap show ========== " + proWrap.getInteger("example.version"));
	}

}
