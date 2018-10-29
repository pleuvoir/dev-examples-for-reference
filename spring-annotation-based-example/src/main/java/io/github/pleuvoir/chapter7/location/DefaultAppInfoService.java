package io.github.pleuvoir.chapter7.location;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import io.github.pleuvoir.chapter7.wrap.PropertiesWrap;

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
		if (Objects.isNull(location) || "".equals(location)) {
			throw new IllegalArgumentException("location must non-null");
		}
		Resource resource = resolver.getResource(location);
		Properties pro = PropertiesLoaderUtils.loadProperties(resource);
		PropertiesWrap proWrap = new PropertiesWrap(pro);
		System.out.println("proWrap show ========== " + proWrap.getInteger("example.version"));
	}

}
