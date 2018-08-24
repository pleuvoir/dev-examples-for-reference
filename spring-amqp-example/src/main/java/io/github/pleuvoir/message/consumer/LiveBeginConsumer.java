package io.github.pleuvoir.message.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import io.github.pleuvoir.kit.RabbitConst;
import io.github.pleuvoir.message.LiveBeginMessage;

/**
 * 由RabbitMQ发送来的到达专场开始的MQ<br/>
 * 接收到专场开始的消息
 * <ol>
 *     <li>向STOMP发送专场开始MQ</li>
 *     <li>创建检查专场拍品到期的定时任务</li>
 * </ol>
 */
@RabbitListener(
		bindings = @QueueBinding(
				value = @Queue(RabbitConst.LiveBeginExpire.QUEUE),
				exchange = @Exchange(RabbitConst.LiveBeginExpire.EXCHANGE),
				key = RabbitConst.LiveBeginExpire.ROUTING_KEY
		)
)
@Component
public class LiveBeginConsumer {

	private static Logger logger = LoggerFactory.getLogger(LiveBeginConsumer.class);

	
	@RabbitHandler
	public void handler(String data) {

		logger.info("【专场开始】接收消息：" + data);

		LiveBeginMessage msg = JSON.parseObject(data, LiveBeginMessage.class);

		logger.info("【LiveBeginConsumer】，msg：{}", msg.toJSON());

	}


}
