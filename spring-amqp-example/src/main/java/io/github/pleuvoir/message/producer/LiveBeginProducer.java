package io.github.pleuvoir.message.producer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.pleuvoir.kit.RabbitConst;
import io.github.pleuvoir.message.LiveBeginMessage;

@Component
public class LiveBeginProducer {
	
	private static Logger logger = LoggerFactory.getLogger(LiveBeginProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	
	public void send(LiveBeginMessage msg){

		logger.info("【专场开始】审核通过，准备发送消息：msg -> {}", msg.toJSON());

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime beginTime = msg.getBeginTime();
		
		long expire = Duration.between(now, beginTime).toMillis();

		if (expire < 0) {
			logger.warn("【专场开始】 警告：专场开始时间在当前时间之前，专场编号：{}，专场开始时间：{}", msg.getLiveId(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(beginTime));
			return;
		}
		System.err.println(expire);
		rabbitTemplate.convertAndSend(RabbitConst.LiveBegin.EXCHANGE, RabbitConst.LiveBegin.ROUTING_KEY, msg.toJSON(), m -> {
			m.getMessageProperties().setExpiration(String.valueOf(5));
			return m;
		});
		
		logger.info("【专场开始】消息已发送，专场编号：{}", msg.getLiveId());
	}

}
