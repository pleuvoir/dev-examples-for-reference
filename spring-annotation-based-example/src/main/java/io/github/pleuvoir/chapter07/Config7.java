package io.github.pleuvoir.chapter07;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import io.github.pleuvoir.chapter07.location.AppInfoService;
import io.github.pleuvoir.chapter07.location.DefaultAppInfoService;
import io.github.pleuvoir.chapter07.wrap.EnvironmentWrapper;

@Configuration
@Import({ EnvironmentWrapper.class })
@ComponentScan("io.github.pleuvoir.chapter07")
public class Config7 {

	@Autowired
	private EnvironmentWrapper environmentWrapper;
	
	@Value("classpath:/config.properties")
	private Resource configFile;
	
	@Bean
	public AppInfoService appInfoService() {
		System.out.println("文件加载了:" + configFile);
		AppInfoService appInfoService = new DefaultAppInfoService();
		// appInfoService.setLocation("classpath:dev.properties");
		appInfoService.setLocation(environmentWrapper.getString("appInfoService.location", "classpath:dev.properties"));
		return appInfoService;
	}
	
	@Bean(name = "appProperties")
	public Properties configProperties() throws Exception {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		
		// 1
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		propertiesFactoryBean.setLocations(resolver.getResource("classpath:dev.properties"),
//				resolver.getResource("classpath:groovy.properties"));
		Resource[] resources = resolver.getResources("classpath:*.properties");  // 使用单元测试需注意不在同一目录下，会读取不到文件，参考下面的 main 方法即可
		propertiesFactoryBean.setLocations(resources);
		// 2
//		propertiesFactoryBean.setLocations(new ClassPathResource("dev.properties"), new ClassPathResource("groovy.properties"));
		propertiesFactoryBean.setFileEncoding("UTF-8");
		propertiesFactoryBean.afterPropertiesSet();
		Properties appProperties = propertiesFactoryBean.getObject();
		return appProperties;
	}
	
	
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
		app.register(Config7.class);
		app.getEnvironment().setActiveProfiles("dev");
		app.refresh();
		System.out.println(app.getBean(AppConfig.class));
		
		EnvironmentWrapper env = app.getBean(EnvironmentWrapper.class);
		System.out.println(env.getString("example.name"));
		System.out.println(env.getInteger("age", 26));
		System.out.println(env.getInteger("example.version"));
		System.out.println(env.getBoolean("example.not.exist", true));
		
		// 通过 location 读取
		AppInfoService appInfoService = app.getBean(AppInfoService.class);
		appInfoService.show();
		
		
		// 通过 PropertiesFactoryBean 
		System.out.println("===========通过 PropertiesFactoryBean==========");
		Properties pp = app.getBean("appProperties", Properties.class);
		
		pp.entrySet().forEach(k -> {
			System.out.println(k);
		});
		
		app.close();
	}
}
