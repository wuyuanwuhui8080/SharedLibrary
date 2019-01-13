package com.share.charsocket.enums;

import com.share.charsocket.server.CharServer;

/**
 *
 * 启动类的单例枚举
 *
 * @author 博博
 * @Title: CharServerEnum
 * @ProjectName SharedLibrary
 * @time 2019/1/10 9:13
 */
public enum CharServerEnum {

	INSTANCE;

	private CharServer charServer;

	CharServerEnum() {
		charServer = new CharServer();
	}

	public CharServer getInstance() {
		return this.charServer;
	}

}
