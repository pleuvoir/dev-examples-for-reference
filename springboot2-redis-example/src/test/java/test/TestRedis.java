package test;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.pleuvoir.RedisApplicationExample;
import io.github.pleuvoir.redis.LiveCurrentItemRepository;
import io.github.pleuvoir.redis.model.LiveCurrentItemCache;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplicationExample.class)
public class TestRedis {

	@Autowired
	private LiveCurrentItemRepository liveCurrentItemRepository;
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
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
	}
}
