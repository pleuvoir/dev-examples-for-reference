package io.github.pleuvoir.service.support;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import io.github.pleuvoir.bean.MetaData;
import io.github.pleuvoir.bean.TemplateFactory;
import io.github.pleuvoir.config.AppConfig;
import io.github.pleuvoir.service.convert.ConverterRoute;
import io.github.pleuvoir.service.metadata.ResultSetMetaDataService;
import lombok.SneakyThrows;

@Configuration
public class SimpleInitService implements InitService {

	@Autowired
	ResultSetMetaDataService metaDataService;
	@Autowired
	private AppConfig appConfig;
	@Autowired
	private ConverterRoute converterRoute;

	@PostConstruct
	@Bean
	@Override
	public MetaData initialize() {
		return new MetaData(
				metaDataService.query(appConfig.getQuerySql(), converterRoute.autoRoute()));
	}

	@PostConstruct
	@Override
	@SneakyThrows
	@Bean
	public TemplateFactory initTemplate() {
		TemplateFactory templateFactory = new TemplateFactory();
		templateFactory.setLocation(new DefaultResourceLoader().getResource("classpath:ftl"));
		return templateFactory;
	}

}
