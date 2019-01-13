package com.share.charsocket.server;

import com.share.aop.LoggingAspect;
import com.share.charsocket.constant.WebSocketConstant;
import com.share.charsocket.handler.CharHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

/**
 * netty启动类
 *
 * @author 博博
 * @Title: CharServer
 * @ProjectName SharedLibrary
 * @time 2019/1/10 9:08
 */
@Component
public class CharServer {

	// 初始化老板线程
	private EventLoopGroup bossGroup;
	// 初始化业务线程
	private EventLoopGroup workGroup;
	// 初始化服务器
	private ServerBootstrap serverBootstrap;
	// 端口绑定
	private ChannelFuture future;

	public CharServer() {
		bossGroup = new NioEventLoopGroup();
		workGroup = new NioEventLoopGroup();
		serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						//请求解码器
						pipeline.addLast("http-codec", new HttpServerCodec());
						//将多个消息转换成单一的消息对象
						pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
						//支持异步发送大的码流，一般用于发送文件流
						pipeline.addLast("http-chunked", new ChunkedWriteHandler());
						// websocket访问路径
						/*pipeline.addLast(
								new WebSocketServerProtocolHandler("ws"));*/
						// 自定义handler
						pipeline.addLast(new CharHandler());
					}
				});
	}

	/**
	 * 启动netty方法
	 */
	public void start() {
		// 绑定启动端口
		future = serverBootstrap.bind(WebSocketConstant.WEB_SOCKET_PORT);
	}

}
