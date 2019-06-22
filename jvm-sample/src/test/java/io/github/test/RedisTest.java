package io.github.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.pleuvoir.jvm.App;
import redis.clients.jedis.Jedis;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class RedisTest {
	
	@Resource
	private Jedis jedis;

	@Test
	public void test() {
		jedis.set("test", "val");
		String val = jedis.get("test");
		System.out.println(val);
	}
}
