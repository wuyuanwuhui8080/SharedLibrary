package com.share.enums;

/**
 *
 * 好友请求状态枚举
 *
 * @author 博博
 * @Title: FriendStatusEnums
 * @ProjectName SharedLibrary
 * @time 2019/1/4 13:37
 */
public enum FriendStatusEnums {

	/**
	 * 未处理请求
	 */
	UNTREATED_1(1),

	/**
	 * 已经添加
	 */
	HAS_AGREED_TO_2(2),

	/**
	 * 拒绝
	 */
	HAS_REFUSED_TO_3(3);

	private Integer status;

	FriendStatusEnums(int i) {
		this.status = i;
	}

	public Integer getStatus() {
		return status;
	}
}
