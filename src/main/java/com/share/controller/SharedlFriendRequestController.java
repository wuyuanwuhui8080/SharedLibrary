package com.share.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.share.pojo.SharedlFriendRequest;
import com.share.service.SharedlFriendRequestService;
import com.share.util.ReturnResult;

/**
 * 好友请求前端控制器
 * 
 * @author 博博大人
 * @time 2018/12/16 21:45
 */
@Controller
@RequestMapping("/sharedlFriendRequest")
public class SharedlFriendRequestController {

	@Resource
	private SharedlFriendRequestService friendRequestService;

	/**
	 * 处理好友请求
	 * 
	 * @param meId
	 *            传入的自己的id
	 * @param requestId
	 *            好友的id
	 * @return
	 */
	@GetMapping("/toFriendsRTS")
	@ResponseBody
	public ReturnResult toFriendsRTS(String meId, String requestId) {
		SharedlFriendRequest friendRequest = new SharedlFriendRequest();
		friendRequest.setMeId(meId);
		friendRequest.setRequestId(requestId);
		// 校验是否发送重复请求
		if (friendRequestService.getFriendRequstEixt(friendRequest)) {
			return ReturnResult.error("您已经发送过请求了,不能在发送请求了!");
		}
		if (friendRequestService.saveFriendRequst(friendRequest)) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error("发送好友请求失败!");
		}
	}
}
