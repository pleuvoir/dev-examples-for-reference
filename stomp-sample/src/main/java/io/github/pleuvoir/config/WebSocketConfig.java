package io.github.pleuvoir.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/*
 * 开启使用Stomp协议来传输基于消息broker的消息 这时控制器支持使用@MessageMapping，就像使用@RequestMapping一样
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


//	@Value("${rabbitmq.addresses}")
//	private String rabbitAddresses;
//	@Value("${rabbitmq.virtualHost}")
//	private String rabbitmqVirtualHost;
//	@Value("${rabbitmq.username}")
//	private String rabbitmqUsername;
//	@Value("${rabbitmq.password}")
//	private String rabbitmqPassword;

	@Value("${rabbitmq.stomp.host}")
	private String rabbitmqStompHost;
	@Value("${rabbitmq.stomp.port}")
	private Integer rabbitmqStompPort;
	@Value("${rabbitmq.stomp.virtualHost}")
	private String rabbitmqStompVirtualHost;
	@Value("${rabbitmq.stomp.login}")
	private String rabbitmqStompLogin;
	@Value("${rabbitmq.stomp.passcode}")
	private String rabbitmqStompPasscode;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/*
		 * 注册STOMP协议的节点(endpoint),并映射指定的url, 添加一个访问端点“/chatroom”,客户端打开双通道时需要的url, 允许所有的域名跨域访问，指定使用SockJS协议。
		 */
		registry.addEndpoint("/chatroom").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

		// 发送消息前缀，如 @MessageMapping("/massRequest") 请求时则为 /live/massRequest
		registry.setApplicationDestinationPrefixes("/live");

		/*
		 * 1. 配置一个基于内存的消息代理 topic 负责群聊 queue 单聊
		 */
		// registry.enableSimpleBroker("/topic", "/queue");


		// 2. 使用 RabbitMQ 做为消息代理，替换默认的 Simple Broker
		// 在 RabbitMQ中合法的目的前缀： /temp-queue, /exchange, /topic, /queue, /amq/queue, /reply-queue/

		StompBrokerRelayRegistration relayRegistry = registry.enableStompBrokerRelay("/topic")
				.setVirtualHost(rabbitmqStompVirtualHost).setRelayHost(rabbitmqStompHost)
				.setRelayPort(rabbitmqStompPort == null ? 61613 : rabbitmqStompPort);
		
		//  registry 内部也操作的是这个对象，所以可以直接设置值
		if (StringUtils.isNotBlank(rabbitmqStompLogin)) {
			relayRegistry.setClientLogin(rabbitmqStompLogin);
		}
		if (StringUtils.isNotBlank(rabbitmqStompPasscode)) {
			relayRegistry.setClientPasscode(rabbitmqStompPasscode);

			// 一对一的用户， 即 convertAndSendToUser 方法会在回传给前端消息地址前追加此前缀
			registry.setUserDestinationPrefix("/user");
		}
	}
}
