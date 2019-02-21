package com.share.util;

/**
 *
 * 用于生成redis key
 *
 * @author 博博
 * @Title: RedisKeys
 * @ProjectName SharedLibrary
 * @time 2019/2/18 8:51
 */
public class RedisKeys {

	/**
	 * 用于操作置顶的 hash key
	 * 
	 * @param forumId
	 * @return
	 */
	public static String forumStick(String forumId) {
		return "stick::" + forumId;
	}

	/**
	 * 获取hash 的置顶名
	 * 
	 * @return
	 */
	public static String getForumStick() {
		return "top::collection";
	}

	/**
	 * 获取收藏的key
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public static String gteCollection(String forumId) {
		return "collection::" + forumId;
	}

	/**
	 * 获取收藏的map key
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public static String getCollectionMapKey(String userId) {
		return "map_collection::" + userId;
	}

}
