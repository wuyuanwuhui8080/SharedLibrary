package com.share.charsocket.enums;

import com.share.charsocket.util.WebsocketUtil;

/**
 *
 * websocketUtil的单例工具类
 *
 * @author 博博
 * @Title: WebsocketUtilEnum
 * @ProjectName SharedLibrary
 * @time 2019/1/10 16:31
 */
public enum WebsocketUtilEnum {

	INSTANCE;

	private WebsocketUtil websocketUtil;

	WebsocketUtilEnum() {
		websocketUtil = new WebsocketUtil();
	}

	/**
	 * 公共获取实例方法
	 * 
	 * @return
	 */
	public WebsocketUtil getInstance() {
		return this.websocketUtil;
	}

}
