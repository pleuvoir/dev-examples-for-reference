package io.github.pleuvoir;

import io.github.pleuvoir.config.BatchConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(BatchConfiguration.class);

		SimpleFetch fetch = app.getBean(SimpleFetch.class);

		Object o = fetch.fetch();

		System.out.println(o);
	}

}
