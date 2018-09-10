package io.github.pleuvoir.service;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pleuvoir.bean.MetaData;
import io.github.pleuvoir.bean.TemplateFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultPayloadService implements PayloadService {

	@Autowired
	private MetaData metaData;
	@Autowired
	private TemplateFactory templateFactory ;
	
	@Override
	@SneakyThrows
	public void go() {
		String content = templateFactory.processToString("entity.ftl", metaData);
		log.info("[*] 模版内容：{}", content);
		log.info("[*] 写入文件D:\\entity.java");
		IOUtils.write(content, new FileOutputStream(new File("D:\\entity.java")));
	}
	
}
