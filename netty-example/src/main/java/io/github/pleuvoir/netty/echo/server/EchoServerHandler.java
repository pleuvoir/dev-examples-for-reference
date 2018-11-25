package io.github.pleuvoir.netty.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable // 指明我这个 handler 可以在多个 channel 之间共享，意味这个实现必须是线程安全的。
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	// 读取到网络数据
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("EchoServerHandler channelRead ..");
		ByteBuf in = (ByteBuf) msg; // netty实现的缓冲区
		System.out.println("Server Accept:" + in.toString(CharsetUtil.UTF_8));
		ctx.write(in); // 写回客户端
	}

	// 读取网络数据完成，和上面的是连贯的
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("EchoServerHandler channelReadComplete ..");
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // flush掉所有的数据
				.addListener(ChannelFutureListener.CLOSE); // 当flush完成后，关闭连接
	}

	// 异常处理
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
