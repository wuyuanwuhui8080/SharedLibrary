package com.share.users.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedlFriendRequest;

/**
 * 好友请求接口类
 * 
 * @author 博博大人
 * @time 2018/12/16 21:35
 */
public interface SharedlFriendRequestService extends IService<SharedlFriendRequest> {

	/**
	 * 发送好友请求
	 * 
	 * @param meId
	 *            发送者的id
	 * @param friendId
	 *            接受者的id
	 * @return
	 */
	Boolean saveFriendRequst(SharedlFriendRequest friendRequest);

	/**
	 * 校验是否存在该记录
	 * 
	 * @param friendRequest
	 * @return
	 */
	Boolean getFriendRequstEixt(SharedlFriendRequest friendRequest);

}
