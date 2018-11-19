package io.github.pleuvoir.chapter8.systemevent;

import java.util.Objects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class ApplicationStartupListener implements ApplicationListener<ContextStartedEvent> {

	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		System.out.println("ApplicationStartupListener ：" + Objects.toString(applicationContext));
	}

}
