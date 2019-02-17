package io.github.pleuvoir.common;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

@Configuration
@EnableRedisRepositories(basePackages="io.github.pleuvoir.redis", repositoryImplementationPostfix = "Repository")
@AutoConfigureAfter({RedisAutoConfiguration.class})
public class CommonConfiguration {


	/**
	 * redis
	 */
	@Bean("redisTemplate")
	public RedisTemplate<String,Object> getRedisTemplate(LettuceConnectionFactory redisConnectionFactory){
		RedisTemplate<String,Object> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericFastJsonRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean("stringRedisTemplate")
	public StringRedisTemplate getStringRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}


}
