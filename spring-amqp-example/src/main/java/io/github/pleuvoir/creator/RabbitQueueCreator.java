package io.github.pleuvoir.creator;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.pleuvoir.kit.RabbitConst;

@Component
public class RabbitQueueCreator {

	@Autowired
	private RabbitAdmin rabbitAdmin;


	@PostConstruct
	public void init(){
		
		/**
		 * Direct
		 */
		// 创建延迟队列
		createBeginDelayQueueGroup();
		// 创建普通队列
		createNormalQueue();
		// 创建定时队列
		createTimeFixedQueue();
		
		
		/**
		 * Topic 通过使用 * 和 #，使来自不同源头的消息到达同一个队列，.将路由键分为了几个标识符，* 匹配 1 个，# 匹配一个或多个。
		 */
		initTopicQueue();
	}

	
	/**
	 * 创建普通队列
	 */
	private void createNormalQueue() {
		Exchange exchangeNormal = ExchangeBuilder.directExchange(RabbitConst.Normal.EXCHANGE).durable(true).build();
		Queue 	queueNormal 	= QueueBuilder.durable(RabbitConst.Normal.QUEUE).build();
		Binding bindingNormal 	= BindingBuilder.bind(queueNormal).to(exchangeNormal).with(RabbitConst.Normal.ROUTING_KEY).noargs();
		rabbitAdmin.declareExchange(exchangeNormal);
		rabbitAdmin.declareQueue(queueNormal);
		rabbitAdmin.declareBinding(bindingNormal);
	}
	
	
	/**
	 * <p> 创建开始队列（该队列无消费者），到达开始时间的队列 
	 * <p> 到达开始时间的队列 作为 开始队列 的死信队列， 当开始队列的每个消息到达过期时间未被消费时会被投递到死信队列，消费者消费死信队列即可实现延迟消费
	 * <p> 注意：队列中的消息遵循 FIFO 原则，直到消息到达队列头部时才会被投递进入死信队列
	 */
	private void createBeginDelayQueueGroup(){

		Exchange exchangeBegin = ExchangeBuilder.directExchange(RabbitConst.Begin.EXCHANGE).durable(true).build();
		Queue queueBegin = QueueBuilder.durable(RabbitConst.Begin.QUEUE)
				.withArgument("x-dead-letter-exchange", RabbitConst.BeginArrival.EXCHANGE)
				.withArgument("x-dead-letter-routing-key", RabbitConst.BeginArrival.ROUTING_KEY)
				.build();
		Binding bindingBegin = BindingBuilder.bind(queueBegin).to(exchangeBegin).with(RabbitConst.Begin.ROUTING_KEY).noargs();
		
		rabbitAdmin.declareExchange(exchangeBegin);
		rabbitAdmin.declareQueue(queueBegin);
		rabbitAdmin.declareBinding(bindingBegin);
		
		Exchange exchangeBeginArrival = ExchangeBuilder.directExchange(RabbitConst.BeginArrival.EXCHANGE).durable(true).build();
		Queue queueBeginArrival = QueueBuilder.durable(RabbitConst.BeginArrival.QUEUE).build();
		Binding bindingBeginArrival = BindingBuilder.bind(queueBeginArrival).to(exchangeBeginArrival).with(RabbitConst.BeginArrival.ROUTING_KEY)
				.noargs();
		
		rabbitAdmin.declareExchange(exchangeBeginArrival);
		rabbitAdmin.declareQueue(queueBeginArrival);
		rabbitAdmin.declareBinding(bindingBeginArrival);
	}
	
	
	/**
	 * 创建定时队列，该定时队列用来消费多个临时队列
	 */
	private void createTimeFixedQueue() {
		Exchange exchangeFixedTime = ExchangeBuilder.directExchange(RabbitConst.FixedTime.EXCHANGE).durable(true).build();
		Queue 	queueFixedTime 	= QueueBuilder.durable(RabbitConst.FixedTime.QUEUE).build();
		Binding bindingFixedTime 	= BindingBuilder.bind(queueFixedTime).to(exchangeFixedTime).with(RabbitConst.FixedTime.ROUTING_KEY).noargs();
		rabbitAdmin.declareExchange(exchangeFixedTime);
		rabbitAdmin.declareQueue(queueFixedTime);
		rabbitAdmin.declareBinding(bindingFixedTime);
	}

	
	
	private void initTopicQueue() {

		// 使用同一个交换机
		Exchange topicExchange = ExchangeBuilder.topicExchange("x.log").durable(true).build();
		rabbitAdmin.declareExchange(topicExchange);

		// 这些队列应当在 live 和  wechat 两个项目中分别创建，演示用
		Queue liveInfo = QueueBuilder.durable("q.info.live").build();
		Queue liveError = QueueBuilder.durable("q.error.live").build();
		rabbitAdmin.declareQueue(liveInfo);
		rabbitAdmin.declareQueue(liveError);
		
		Queue wechatInfo = QueueBuilder.durable("q.info.wechat").build();
		Queue wechatError = QueueBuilder.durable("q.error.wechat").build();
		rabbitAdmin.declareQueue(wechatInfo);
		rabbitAdmin.declareQueue(wechatError);

		//rabbitAdmin.declareBinding(BindingBuilder.bind(info).to(topicExchange).with(routingKey).noargs());
		//rabbitAdmin.declareBinding(BindingBuilder.bind(warn).to(topicExchange).with(routingKey).noargs());
	}
	
}
