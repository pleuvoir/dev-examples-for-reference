package io.github.pleuvoir;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.github.pleuvoir.chapter12.Config12;
import io.github.pleuvoir.chapter12.MyAppBootstrap;

@SuppressWarnings("resource")
public class ChapterTest12 {

	// eclipse 中增加启动参数 run Configuration -> Arguments -> VM 格式 ：
	// -Dargname=argvalue
	public static void main(String[] args) throws IOException {

		Properties properties = System.getProperties();

		properties.entrySet().forEach(k -> {
			System.out.println(k);
		});

		MyAppBootstrap app = new MyAppBootstrap(Config12.class);
		for (String beanDefinitionName : app.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}

		// 定时监测线程，默认是 10 秒监测一次，可以改为动态配置的
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(r -> {
			Thread t = new Thread(r, "check-and-test");
			t.setDaemon(true);
			return t;
		});

		executorService.scheduleWithFixedDelay(() -> {
			try {
				System.out.println("获取值mockKey：         " + app.getEnvironment().getProperty("mockKey"));
				System.out.println("获取值mockKey2：      " + app.getEnvironment().getProperty("mockKey2"));
			} catch (Throwable t) {
				t.getStackTrace();
			}
		}, 0L, 1L, TimeUnit.SECONDS);

		
		System.in.read();
		app.close();
	}

}
