package io.github.pleuvoir.xml.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import javax.xml.bind.JAXBContext;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;

public class Client {

	private static final String LOCATION = "config.xml";
	
	@SneakyThrows
	public static void main(String[] args) {
		// 麻烦
		FileInputStream fileInputStream = new FileInputStream(new File(URLDecoder
				.decode(Thread.currentThread().getContextClassLoader().getResource(LOCATION).getFile(), "utf-8")));
		
		// 简单
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(LOCATION);
		JAXBContext context = JAXBContext.newInstance(XmlConfig.class);
		XmlConfig configObj = (XmlConfig) context.createUnmarshaller().unmarshal(input);
		XmlConfig configObj2 = (XmlConfig) context.createUnmarshaller().unmarshal(fileInputStream);
		System.out.println(JSON.toJSON(configObj));
		System.out.println(JSON.toJSON(configObj2));
		// {"mode":"debug","cache":{"location":"d:/cache","persist":true},
		// "datasources":[{"name":"oracle"},{"name":"mysql","driveclass":"com.mysql.jdbc.Driver"}]}
	}
}
