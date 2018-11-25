package io.github.pleuvoir.netty.echo.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

	private int port;

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("准备启动服务端 ...");
		EchoServer echoServer = new EchoServer(9000);
		echoServer.start();
		System.out.println("服务端关闭 ...");
	}

	private void start() throws InterruptedException {

		// 线程组
		EventLoopGroup group = new NioEventLoopGroup();

		final EchoServerHandler echoServerHandler = new EchoServerHandler();
		
		try {
			// 服务端启动必备
			ServerBootstrap bootstrap = new ServerBootstrap();

			bootstrap.group(group).channel(NioServerSocketChannel.class) 	// 指明使用NIO进行网络通讯
					.localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<Channel>() {		// 接收到连接请求，新启一个 socket 通信，也就是channel，这里可以选择new 一个或者 shared
						@Override
						protected void initChannel(Channel ch) throws Exception {
							ch.pipeline().addLast(echoServerHandler);
						//	ch.pipeline().addLast(new EchoServerHandler());
						}
					});
				
			ChannelFuture channelFuture = bootstrap.bind().sync();	// 绑定到端口，阻塞等待直到连接完成，这里 ChannelFuture 就像一个占位
			
			channelFuture.channel().closeFuture().sync();	// 阻塞，直到 channel 关闭
		} finally {
			group.shutdownGracefully().sync(); // 优雅停机
		}

	}

}
