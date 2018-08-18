package io.github.pleuvoir.sample;

import io.github.pleuvoir.annotation.Inject;

public class Server {

	@Inject
	private WechatPayService wechatPayService;

	@Inject(name = "alipayService")
	private AlipayService alipayService;

	@Inject(value = JDPayService.class)
	private JDPayService jdPayService;

	public void wechatPay() {
		wechatPayService.pay();
	}

	public void alipay() {
		alipayService.pay();
	}

	public void jdPay() {
		jdPayService.pay();
	}
}
