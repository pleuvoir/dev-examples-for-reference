package test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.pleuvoir.RedisApplicationExample;
import io.github.pleuvoir.redis.LiveCurrentItemRepository;
import io.github.pleuvoir.redis.RabbitMessageLogCache;
import io.github.pleuvoir.redis.RabbitMessageStatusEnum;
import io.github.pleuvoir.redis.model.LiveCurrentItemCache;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplicationExample.class)
public class TestRedis {

	@Autowired
	private LiveCurrentItemRepository liveCurrentItemRepository;
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired KeyValueTemplate keyValueTemplate;
	
	@Test
	public void test() throws InterruptedException {

		String liveId = String.valueOf(ThreadLocalRandom.current().nextInt(999999));
		// 检查专场缓存，若不存在，表示专场尚未开始
		Optional<LiveCurrentItemCache> liveCurrentItemOptional = liveCurrentItemRepository.findById(liveId);
		if (!liveCurrentItemOptional.isPresent()) {
			RedisHash redisHash = LiveCurrentItemCache.class.getAnnotation(RedisHash.class);
			System.out.println("缓存中未能查询到专场，表示专场尚未开始，RedisHash:" + redisHash + "，liveId:" + liveId);
		}
		
		LiveCurrentItemCache liveCurrentItemCache = new LiveCurrentItemCache();
		liveCurrentItemCache.setLiveId(liveId);
		liveCurrentItemRepository.save(liveCurrentItemCache);
		
	//	TimeUnit.SECONDS.sleep(5);
		
		Optional<LiveCurrentItemCache> liveCurrentItemOptionalAgain = liveCurrentItemRepository.findById(liveId);
		RedisHash redisHash = LiveCurrentItemCache.class.getAnnotation(RedisHash.class);
		if (!liveCurrentItemOptionalAgain.isPresent()) {
			System.out.println("缓存中未能查询到专场，表示专场尚未开始，RedisHash:" + redisHash + "，liveId:" + liveId);
		}else{
			System.out.println("查询到缓存，专场编号：" + liveCurrentItemOptionalAgain.get().getLiveId() + "，RedisHash:" + redisHash);
		}

		Boolean setIfAbsent = redisTemplate.opsForValue().setIfAbsent(liveId, liveId, Duration.ofHours(1));
		System.out.println(setIfAbsent);
		
		Boolean setIfAbsentAgain = redisTemplate.opsForValue().setIfAbsent(liveId, liveId, Duration.ofHours(1));
		System.out.println(setIfAbsentAgain);
		
		String messageId = String.valueOf(ThreadLocalRandom.current().nextInt(9999));
		RabbitMessageLogCache prevRabbitMessageLogCache = new RabbitMessageLogCache();
		prevRabbitMessageLogCache.setMessageId(messageId);
		prevRabbitMessageLogCache.setCreateTime(LocalDateTime.now());
		prevRabbitMessageLogCache.setMessageStatus(RabbitMessageStatusEnum.PREPARE_TO_BROKER);
		keyValueTemplate.insert(prevRabbitMessageLogCache);
		
		RabbitMessageLogCache rabbitMessageLogCache = new RabbitMessageLogCache();
		rabbitMessageLogCache.setMessageId(messageId);
		rabbitMessageLogCache.setMessageStatus(RabbitMessageStatusEnum.CONSUMER_SUCCESS);
		keyValueTemplate.update(rabbitMessageLogCache);
		
		Optional<RabbitMessageLogCache> rabbitMessageLogOptional = keyValueTemplate.findById(messageId, RabbitMessageLogCache.class);
		if (!rabbitMessageLogOptional.isPresent()) {
			System.out.println("未找到");
			return;
		}else{
			System.out.println(rabbitMessageLogOptional.get().getMessageId());
		}
	}
}
