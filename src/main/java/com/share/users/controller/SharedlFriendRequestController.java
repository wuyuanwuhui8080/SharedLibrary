package com.share.users.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.share.enums.FriendStatusEnums;
import com.share.pojo.SharedFriends;
import com.share.pojo.SharedlFriendRequest;
import com.share.users.service.SharedFriendsService;
import com.share.users.service.SharedlFriendRequestService;
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

	@Resource
	private SharedFriendsService friendsService;

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
		// 没有发送过好友请求，进行好友请求发送
		if (friendRequestService.saveFriendRequst(friendRequest)) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error("发送好友请求失败!");
		}
	}

	/**
	 * 跳转到请求页面
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/goFirendRequest/{userId}")
	public String goFirendRequest(@PathVariable String userId, Model model) {
		// 查询所有好友请求
		List<SharedlFriendRequest> friendRequestList = friendRequestService
				.findFriendRequestByUserId(userId);
		model.addAttribute("friendRequestList", friendRequestList);
		return "background/users/friend_request";
	}

	/**
	 * 处理请求状态 （1. 未处理 2.同意 3.拒绝）
	 * 
	 * @param sharedlFriendRequest
	 *            传入实体
	 * @return
	 */
	@PostMapping("/updateFriendRequest")
	@ResponseBody
	public ReturnResult updateFriendRequest(
			SharedlFriendRequest sharedlFriendRequest) {
		// 判断是否是请求同意的
		if (sharedlFriendRequest
				.getStatus() == FriendStatusEnums.HAS_AGREED_TO_2.getStatus()) {
			// 拼装数据
			SharedFriends friends = new SharedFriends();
			friends.setFriendsId(sharedlFriendRequest.getRequestId());
			friends.setMeId(sharedlFriendRequest.getMeId());
			friends.setCreationDate(new Date());
			SharedFriends friends1 = new SharedFriends();
			friends1.setFriendsId(sharedlFriendRequest.getMeId());
			friends1.setMeId(sharedlFriendRequest.getRequestId());
			friends1.setCreationDate(new Date());
			// 执行批量添加
			if (friendsService.saveFirends(friends, friends1)) {

			} else {
				return ReturnResult.error();
			}
		}
		// 修改请求字段
		if (friendRequestService
				.updateFriendRequestByStatus(sharedlFriendRequest)) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

}
