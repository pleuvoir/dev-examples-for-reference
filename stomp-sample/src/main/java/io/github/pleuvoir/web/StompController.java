package io.github.pleuvoir.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import io.github.pleuvoir.domain.ChatRoomRequest;
import io.github.pleuvoir.domain.ChatRoomResponse;

@Controller
public class StompController {

	@Autowired
	private SimpMessagingTemplate template;/* Spring实现的一个发送模板类 */

	/* 消息群发，接受发送至自massRequest的请求 */
	@MessageMapping("/massRequest")
	@SendTo("/topic/getResponse")
	// SendTo 发送至 Broker 下的指定订阅路径topic , 所以这个开头一定要是注册过的 否则无法代理使用 registry.enableSimpleBroker("/topic", "/queue");
	// Broker再根据getResponse发送消息到订阅了/topic/getResponse的用户处
	public ChatRoomResponse mass(ChatRoomRequest chatRoomRequest) {
		System.out.println("name =" + chatRoomRequest.getName() + " chatValue =" + chatRoomRequest.getChatValue());
		ChatRoomResponse response = new ChatRoomResponse();
		response.setName(chatRoomRequest.getName());
		response.setChatValue(chatRoomRequest.getChatValue());
		// this.template.convertAndSend();
		return response;
	}

	/* 单独聊天，接受发送至自aloneRequest的请求 */
	@MessageMapping("/aloneRequest")
	// @SendToUser
	public ChatRoomResponse alone(ChatRoomRequest chatRoomRequest) {
		System.out.println("SendToUser = " + chatRoomRequest.getUserId() + " FromName = " + chatRoomRequest.getName()
				+ " ChatValue = " + chatRoomRequest.getChatValue());
		ChatRoomResponse response = new ChatRoomResponse();
		response.setName(chatRoomRequest.getName());
		response.setChatValue(chatRoomRequest.getChatValue());
		// 发送地址会变为 {userid}/alone ==> /queue/{userid}/alone  增加的前缀是  registry.setUserDestinationPrefix("/queue") 设置的
		this.template.convertAndSendToUser(chatRoomRequest.getUserId(), "/alone", response);
		return response;
	}
}