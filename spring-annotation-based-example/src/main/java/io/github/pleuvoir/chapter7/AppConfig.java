package io.github.pleuvoir.chapter7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "dev.properties" })
public class AppConfig {

	@Value(value = "${example.name}")
	private String name;

	@Value(value = "${example.version}")
	private Integer version;

	@Value(value = "${example.isfinish}")
	private boolean isFinish;

	@Value(value = "#{20-2}")
	private Integer age;

	public final String getName() {
		return name;
	}

	public final Integer getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "AppConfig [name=" + name + ", version=" + version + ", isFinish=" + isFinish + ", age=" + age + "]";
	}

}
