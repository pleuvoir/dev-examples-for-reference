package io.github.pleuvoir.sample;

import io.github.pleuvoir.container.Container;
import io.github.pleuvoir.container.SimpleContainer;

public class Client {

	private static Container container = new SimpleContainer();

	public static void main(String[] args) {
		// [*] 如果一个类需要注入属性，那么它本身必须在容器中
		container.registerBean(Server.class);
		// [*] 如果需要用bean name来注入，那么这个bean应该是容器初始化的时候就已经在容器中了
		container.registerBean("alipayService", new AlipayService());
		// [*] 开始注入
		container.init();
		// [*] 从容器中取出这个类，此时里面需要注入的字段都已指向了具体的对象实例
		Server server = container.getBean(Server.class);
		server.wechatPay();
		server.alipay();
		server.jdPay();
	}

}
