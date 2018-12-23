package io.github.pleuvoir;

import java.util.Properties;

import io.github.pleuvoir.chapter12.Config12;
import io.github.pleuvoir.chapter12.MyAppBootstrap;

public class ChapterTest12 {

	// eclipse 中增加启动参数 run Configuration -> Arguments -> VM  格式 ：   -Dargname=argvalue
	public static void main(String[] args) {
		
        Properties properties = System.getProperties();
        
		properties.entrySet().forEach(k -> {
			System.out.println(k);
		});
        
        
		MyAppBootstrap app = new MyAppBootstrap(Config12.class);
		for (String beanDefinitionName : app.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
		app.close();
	}

}
