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
		// 创建延迟队列
		createBeginDelayQueueGroup();
		// 创建普通队列
		createNormalQueue();
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
	 * <p> 创建开始队列，到达开始时间的队列 
	 * <p> 到达开始时间的队列 作为 开始队列 的死信队列， 当开始队列的每个消息到达过期时间时会被投递到死信队列，消费者消费死信队列即可
	 */
	private void createBeginDelayQueueGroup(){

		//定义exchange、queue、routing key，并绑定，同时设置队列的死信
		Exchange exchangeBegin = ExchangeBuilder.directExchange(RabbitConst.Begin.EXCHANGE).durable(true).build();
		rabbitAdmin.declareExchange(exchangeBegin);
		
		Queue queueBegin = QueueBuilder.durable(RabbitConst.Begin.QUEUE)
				.withArgument("x-dead-letter-exchange", RabbitConst.BeginArrival.EXCHANGE)
				.withArgument("x-dead-letter-routing-key", RabbitConst.BeginArrival.ROUTING_KEY)
				.build();
		rabbitAdmin.declareQueue(queueBegin);
		
		Binding bindingOrder = BindingBuilder.bind(queueBegin).to(exchangeBegin).with(RabbitConst.Begin.ROUTING_KEY).noargs();
		rabbitAdmin.declareBinding(bindingOrder);
		
		//定义到达开始时间的队列exchange、queue、routing key，并绑定
		Exchange exchangeBeginArrival = ExchangeBuilder.directExchange(RabbitConst.BeginArrival.EXCHANGE).durable(true).build();
		rabbitAdmin.declareExchange(exchangeBeginArrival);
		
		Queue queueBeginArrival = QueueBuilder.durable(RabbitConst.BeginArrival.QUEUE).build();
		rabbitAdmin.declareQueue(queueBeginArrival);
		
		Binding bindingBeginArrival = BindingBuilder.bind(queueBeginArrival).to(exchangeBeginArrival)
				.with(RabbitConst.BeginArrival.ROUTING_KEY).noargs();
		rabbitAdmin.declareBinding(bindingBeginArrival);

	}
	

}
