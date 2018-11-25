package io.github.pleuvoir.netty.echo.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	private final String host;
	private final int port;

	public EchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public static void main(String[] args) throws InterruptedException {
		EchoClient echoClient = new EchoClient("127.0.0.1", 9000);
		echoClient.start();
	}

	private void start() throws InterruptedException {
		// 线程组
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();	// 客户端启动必备
			bootstrap.group(group)
					.channel(NioSocketChannel.class) // 指明使用NIO进行网络通讯
					.remoteAddress(new InetSocketAddress(host, port))	// 配置远程服务器的地址 
					.handler(new EchoCientHandler());

			ChannelFuture channelFuture = bootstrap.connect().sync(); // 连接到远程节点，阻塞等待直到连接完成

			channelFuture.channel().closeFuture().sync(); // 阻塞，直到 channel 关闭
		} finally {
			group.shutdownGracefully().sync(); // 优雅停机
		}
	}
}
