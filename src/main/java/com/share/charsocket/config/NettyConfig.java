package com.share.charsocket.config;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.integration.aggregator.MessageGroupExpiredEvent;

/**
 *
 * netty配置
 *
 * @author 博博
 * @Title: NettyConfig
 * @ProjectName SharedLibrary
 * @time 2019/1/10 10:00
 */
public class NettyConfig {

	// 定义ChannelGroup集合
	public static ChannelGroup channels = new DefaultChannelGroup(
			GlobalEventExecutor.INSTANCE);

}
