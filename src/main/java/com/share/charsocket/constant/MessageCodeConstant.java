package com.share.charsocket.constant;

/**
 *
 * 访问状态常量类
 *
 * @author 博博
 * @Title: MessageCodeConstant
 * @ProjectName SharedLibrary
 * @time 2019/1/10 9:56
 */
public class MessageCodeConstant {
	/*
	 * CODE 客户端信息传送 code
	 */
	/**
	 * 登陆
	 */
	public static final int LOGIN_CODE = 1000;

	/**
	 * 注销
	 */
	public static final int LOGIN_OUT = 1001;

	/**
	 * 论坛登录
	 */
	public static  final  int FORUM_LOGIN_CODE = 10001;

	/**
	 * 论坛注销
	 */
	public static  final  int FORUM_LOGIN_OUT_CODE = 10002;

	/**
	 * 群聊
	 */
	public static final int GROUP_CHAT_CODE = 1002;
	/**
	 * 私聊
	 */
	public static final int PRIVATE_CHAT_CODE = 1003;
	/**
	 * pong 信息
	 */
	public static final int PONG_CHAT_CODE = 1004;

	/*
	 * MESSAGE_CODE 服务端信息传送 code
	 */
	/**
	 * 群聊信息
	 */
	public static final int GROUP_CHAT_MESSAGE_CODE = 2000;
	/**
	 * 系统信息
	 */
	public static final int SYSTEM_MESSAGE_CODE = 2001;
	/**
	 * 私聊信息
	 */
	public static final int PRIVATE_CHAT_MESSAGE_CODE = 2002;
	/**
	 * ping 信息
	 */
	public static final int PING_MESSAGE_CODE = 2003;

	/*
	 * SYSTEM_MESSGAE_CODE
	 */
	/**
	 * 普通系统信息：用户上线，下线广播通知等
	 */
	public static final int NORMAL_SYSTEM_MESSGAE_CODE = 3000;
	/**
	 * 更新当前用户数量的系统信息
	 */
	public static final int UPDATE_USERCOUNT_SYSTEM_MESSGAE_CODE = 3001;
	/**
	 * 更新当前用户列表的系统信息
	 */
	public static final int UPDATE_USERLIST_SYSTEM_MESSGAE_CODE = 3002;
	/**
	 * 获取个人信息的系统信息
	 */
	public static final int PERSONAL_SYSTEM_MESSGAE_CODE = 3003;


	/**
	 * 回复信息
	 */
	public static final int FORUM_MESSAGE_CODE = 20001;

	/**
	 * 推送给自己
	 */
	public static final int FORUM_MESSAGEBYME_CODE = 20002;
}
