package io.github.pleuvoir.netty.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoCientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	/// 客户端读取到数据后干什么
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("EchoCientHandler channelRead0 ..");
		System.out.println("Client accetp:" + msg.toString(CharsetUtil.UTF_8));
	}

	// 客户端被通知 channel 活跃以后，做事（和服务器建立了连接）
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 往服务器写数据
		System.out.println("EchoCientHandler channelActive ..");
		ctx.writeAndFlush(Unpooled.copiedBuffer("Hello，Netty", CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
