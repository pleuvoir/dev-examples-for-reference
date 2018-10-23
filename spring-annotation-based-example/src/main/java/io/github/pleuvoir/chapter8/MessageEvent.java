package io.github.pleuvoir.chapter8;

import org.springframework.context.PayloadApplicationEvent;

import io.github.pleuvoir.base.Message;

public class MessageEvent extends PayloadApplicationEvent<Message> {

	private static final long serialVersionUID = -2349201188349434475L;

	public MessageEvent(Object source, Message payload) {
		super(source, payload);
	}

}
