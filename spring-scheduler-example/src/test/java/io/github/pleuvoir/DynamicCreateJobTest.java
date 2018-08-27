package io.github.pleuvoir;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.pleuvoir.model.Message;
import io.github.pleuvoir.quartz.DynamicCreateJob;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicCreateJobTest {

	@Autowired
	private DynamicCreateJob createJob;

	@Test
	public void contextLoads() throws InterruptedException {
		Message msg = new Message();
		msg.setId("1");
		createJob.handler(msg);
		
		TimeUnit.SECONDS.sleep(10);
	}
}
