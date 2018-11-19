package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.base.Message;
import io.github.pleuvoir.chapter8.Config8;
import io.github.pleuvoir.chapter8.custom.MessageListener;
import io.github.pleuvoir.chapter8.systemevent.ApplicationStartupListener;

public class ChapterTest8 {

	@Test
	public void context() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
		app.register(Config8.class);
		app.addApplicationListener(new ApplicationStartupListener());
		app.refresh();
		app.start();

		app.addApplicationListener(new MessageListener());
		app.publishEvent(new Message("消息"));

		app.publishEvent("pleuvior@foxmail.com");
		app.close();
	}

}
