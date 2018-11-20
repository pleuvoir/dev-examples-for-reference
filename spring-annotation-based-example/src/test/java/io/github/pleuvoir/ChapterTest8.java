package io.github.pleuvoir;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.pleuvoir.base.Man;
import io.github.pleuvoir.base.User;
import io.github.pleuvoir.chapter8.Config8;
import io.github.pleuvoir.chapter8.custom.interfaces.ManListener;
import io.github.pleuvoir.chapter8.custom.interfaces.UserListener;
import io.github.pleuvoir.chapter8.systemevent.ApplicationStartupListener;

public class ChapterTest8 {

	@Test
	public void context() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext();
		app.register(Config8.class);
		app.addApplicationListener(new ApplicationStartupListener());

		app.addApplicationListener(new UserListener());
		app.addApplicationListener(new ManListener());
		app.refresh();
		app.start();


		app.publishEvent("pleuvior@foxmail.com");

		app.publishEvent(new User("pleuvoir", 26));
		app.publishEvent(new Man("pleuvoir", 26));
		app.close();
	}

}
