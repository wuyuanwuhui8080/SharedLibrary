package com.share.constant;

/**
 *
 * 好友状态常量
 *
 * @author 博博
 * @Title: FriendsStatusConstant
 * @ProjectName SharedLibrary
 * @time 2018/12/16 21:26
 */
public final class FriendsStatusConstant {

	private FriendsStatusConstant() {

	}

	// 自己
	public static final Integer FRIENDS_ME = 1;
	// 不是自己的好友
	public static final Integer FRIENDS_NOTMEFRIEDS = 2;
	// 自己的好友
	public static final Integer FRIENDS_IS_MEFRIEDS = 3;

}
