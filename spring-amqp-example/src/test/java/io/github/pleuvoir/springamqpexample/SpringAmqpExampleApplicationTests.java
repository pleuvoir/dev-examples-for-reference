package io.github.pleuvoir.springamqpexample;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.pleuvoir.message.LiveBeginMessage;
import io.github.pleuvoir.message.producer.LiveBeginProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpExampleApplicationTests {

	@Autowired
	private LiveBeginProducer liveBeginProducer;

	@Test
	public void contextLoads() throws InterruptedException {
		LiveBeginMessage msg = new LiveBeginMessage();
		msg.setLiveId("1");
		msg.setBeginTime(LocalDateTime.now().plusSeconds(15));
		liveBeginProducer.send(msg);
		
		TimeUnit.SECONDS.sleep(60);
	}

}
