package com.share.charsocket.util;

import io.netty.channel.Channel;

import java.net.SocketAddress;

/**
 * 获取远程地址的工具类
 *
 * @author 博博
 * @Title: WebSocketLocalhostUtil
 * @ProjectName SharedLibrary
 * @time 2019/1/10 10:14
 */
public class WebSocketLocalhostUtil {

	/**
	 * 获得Channel远程主机IP地址
	 */
	public static String getChannelAddress(final Channel channel) {
		if (null == channel) {
			return "";
		}
		SocketAddress address = channel.remoteAddress();
		String addr = (address != null ? address.toString() : "");
		int index = addr.lastIndexOf("/");
		if (index >= 0) {
			return addr.substring(index + 1);
		}
		return addr;
	}
}
