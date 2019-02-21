package com.share.charsocket.util;

/**
 *
 * 用于生成缓存key
 *
 * @author 博博
 * @Title: FriendAndMyIdKey
 * @ProjectName SharedLibrary
 * @time 2019/1/12 22:47
 */
public class FriendAndMyIdKey {

	/**
	 * 生成好友和自己的key方便查询
	 * 
	 * @param myId
	 *            自己的id
	 * @param receiverId
	 *            朋友的id
	 * @return
	 */
	public static String CharKey(String myId, String receiverId) {
		return myId + "and" + receiverId;
	}

	/**
	 * 生成帖子回复事件key
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	public static String ForumKey(String userName) {
		return "forum::" + userName;
	}

}
