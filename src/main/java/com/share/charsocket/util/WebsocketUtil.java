package com.share.charsocket.util;

import com.share.charsocket.bo.Users;

import com.share.charsocket.bo.WebSocketMessage;
import com.share.charsocket.config.NettyConfig;
import com.share.charsocket.constant.MessageCodeConstant;
import com.share.util.JsonUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.catalina.User;
import org.springframework.integration.codec.kryo.MessageCodec;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 处理消息的工具类
 *
 * @author 博博
 * @Title: WebsocketUtil
 * @ProjectName SharedLibrary
 * @time 2019/1/10 10:05
 */
public class WebsocketUtil {


	/**
	 * 存储 Channel 与用户信息
	 */
	public static ConcurrentMap<Channel, Users> concurrentMap = new ConcurrentHashMap<>();

	/**
	 * 储存在线人数
	 */
	public static AtomicInteger userCount = new AtomicInteger(0);

	/**
	 * 添加一个新channel
	 *
	 * @param channel
	 *            传入Channel的实例
	 */
	public void addChannel(Channel channel) {
		// 获取远程地址
		String loaclHost = WebSocketLocalhostUtil.getChannelAddress(channel);
		// 实例化实体
		Users users = new Users();
		users.setHostLocal(loaclHost);
		// 设置值
		concurrentMap.put(channel, users);
		NettyConfig.channels.add(channel);
	}

	/**
	 * 删除一个Channel
	 *
	 * @param channel
	 *            传入的Channel实例
	 */
	public void deleteChannel(Channel channel) {
		// 获取真实姓名
		String realName = concurrentMap.get(channel).getRealName();
		// 把channel从concurrentMap移除
		concurrentMap.remove(channel);
		// 在线人数减一
		userCount.decrementAndGet();
		// 把channel从ChannelGroup删掉
		NettyConfig.channels.remove(channel);

		// 定义消息内容
		String msg = MessageUtil.messageJSONStringFactory(
				MessageCodeConstant.SYSTEM_MESSAGE_CODE, realName + "离开了聊天室..",
				MessageCodeConstant.NORMAL_SYSTEM_MESSGAE_CODE, null);
		TextWebSocketFrame frame = new TextWebSocketFrame(msg);
		// 刷新信息
		updateUserListAndCount();
		// 把信息刷到客户端
		NettyConfig.channels.writeAndFlush(frame);
	}

	/**
	 * 更新在线人数和用户列表
	 */
	public void updateUserListAndCount() {
		// 定义序列化数据
		String msg = MessageUtil.messageJSONStringFactory(
				MessageCodeConstant.SYSTEM_MESSAGE_CODE, null,
				MessageCodeConstant.UPDATE_USERCOUNT_SYSTEM_MESSGAE_CODE,
				userCount);
		// 把数据刷到客户端
		NettyConfig.channels.writeAndFlush(new TextWebSocketFrame(msg));

		/*List<Users> users = new ArrayList<>();
		// 获取map中全部的key
		Set<Channel> setKeys = concurrentMap.keySet();
		setKeys.forEach(channel -> {
			// 根据key获取用户
			Users user = concurrentMap.get(channel);
			// 把用户塞入list集合
			users.add(user);
		});

		// 定义用户列表序列化数据
		String msgList = MessageUtil.messageJSONStringFactory(
				MessageCodeConstant.SYSTEM_MESSAGE_CODE, null,
				MessageCodeConstant.UPDATE_USERLIST_SYSTEM_MESSGAE_CODE, users);
		// 刷新到客户端
		NettyConfig.channels.writeAndFlush(new TextWebSocketFrame(msgList));*/
	}

	/**
	 * 添加一个信息保存到对应的 channel 的 value 中
	 * 
	 * @param channel
	 *            传入的channel的实例
	 * @param userName
	 *            用户名
	 * @param realName
	 *            真实姓名
	 * @param id
	 *            id
	 * @param headImg
	 *            头像
	 * @return
	 */
	public boolean addUsers(Channel channel, String userName, String realName,
			String id, String headImg) {
		// 根据channel获取用户
		Users users = concurrentMap.get(channel);
		// 判断用户是否存在
		if (users == null) {
			return false;
		}

		// 把数据塞入
		users.setId(id);
		users.setHeadImg(headImg);
		users.setRealName(realName);
		users.setUserName(userName);
		// 用户在线数量 + 1
		userCount.incrementAndGet();
		return true;
	}

	/**
	 * 广播ping
	 */
	public void sendPing() {
		// 获取所有key
		Set<Channel> sets = concurrentMap.keySet();

		Channel channel = null;

		sets.forEach(channel1 -> {
			// 循环取users
			Users users = concurrentMap.get(channel);
			// 如果用户是空，直接下一次循环
			if (users == null) {
				return;
			}
			WebSocketMessage socketMessage = new WebSocketMessage();
			// 设置状态信息
			socketMessage.setCode(MessageCodeConstant.PING_MESSAGE_CODE);
			// json化
			String msg = JsonUtil.JSONObjectString(socketMessage);
			// 刷新到客户端
			NettyConfig.channels.writeAndFlush(new TextWebSocketFrame(msg));
		});

	}

	/**
	 * 发送一条私聊
	 * 
	 * @param id
	 * @param textWebSocketFrame
	 */
	public void sendPrivateChatMessage(String id,
			TextWebSocketFrame textWebSocketFrame) {
		// 获取所有key
		Set<Channel> sets = concurrentMap.keySet();
		Channel channes = null;
		for (Channel channel : sets) {
			Users users = concurrentMap.get(channel);
			// 判断是不是发送的用户
			if (users.getId().equals(id)) {
				channes = channel;
				break;
			} else {
				continue;
			}
		}
		// Channel判断是否找到
		if (channes != null) {
			channes.writeAndFlush(textWebSocketFrame);
		}
	}

}
