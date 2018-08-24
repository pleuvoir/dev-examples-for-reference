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
		createLiveBeginQueue();
	}

	/**
	 * 创建专场开始队列，到达开始时间的专场队列
	 */
	private void createLiveBeginQueue(){

		//定义到达开始时间的专场exchange、queue、routing key，并绑定
		Exchange exchangeLiveBeginExpire = ExchangeBuilder.directExchange(RabbitConst.LiveBeginExpire.EXCHANGE).durable(true).build();
		rabbitAdmin.declareExchange(exchangeLiveBeginExpire);
		
		Queue queueLiveBeginExpire = QueueBuilder.durable(RabbitConst.LiveBeginExpire.QUEUE).build();
		rabbitAdmin.declareQueue(queueLiveBeginExpire);
		
		Binding bindingLiveBeginExpire = BindingBuilder.bind(queueLiveBeginExpire).to(exchangeLiveBeginExpire)
				.with(RabbitConst.LiveBeginExpire.ROUTING_KEY).noargs();
		rabbitAdmin.declareBinding(bindingLiveBeginExpire);

		//定义专场exchange、queue、routing key，并绑定，同时设置队列的死信
		Exchange exchangeLiveBegin = ExchangeBuilder.directExchange(RabbitConst.LiveBegin.EXCHANGE).durable(true).build();
		rabbitAdmin.declareExchange(exchangeLiveBegin);
		
		Queue queueLiveBegin = QueueBuilder.durable(RabbitConst.LiveBegin.QUEUE)
				.withArgument("x-dead-letter-exchange", RabbitConst.LiveBeginExpire.EXCHANGE)
				.withArgument("x-dead-letter-routing-key", RabbitConst.LiveBeginExpire.ROUTING_KEY)
				.build();
		rabbitAdmin.declareQueue(queueLiveBegin);
		
		Binding bindingOrder = BindingBuilder.bind(queueLiveBegin).to(exchangeLiveBegin).with(RabbitConst.LiveBegin.ROUTING_KEY).noargs();
		rabbitAdmin.declareBinding(bindingOrder);
	}
	

}
