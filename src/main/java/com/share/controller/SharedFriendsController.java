package com.share.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.share.constant.FriendsStatusConstant;
import com.share.pojo.SharedUsers;
import com.share.service.SharedFriendsService;
import com.share.service.SharedUsersService;
import com.share.util.CopyUtils;
import com.share.util.ReturnResult;
import com.share.vo.SharedUsersVO;

/**
 * 好友前端控制器
 * 
 * @author 博博大人
 * @time 2018/12/16 14:07
 */
@Controller
@RequestMapping("/sharedFriends")
public class SharedFriendsController {

	@Resource
	private SharedUsersService usersService;

	@Resource
	private SharedFriendsService friendsService;

	@GetMapping("/goSearchFriend")
	public String goSearchFriend() {
		return "background/users/friends_search";
	}

	/**
	 * 根据传入的参数查找好友，然后展现在页面
	 * 
	 * @param name
	 * @param session
	 * @return
	 */
	@PostMapping("/searchFriend")
	@ResponseBody
	public ReturnResult searchFriend(@RequestParam("name") String name,
			HttpSession session) {
		// 根据用户名或者真实姓名查询
		List<SharedUsers> usersList = usersService
				.findUsersListByUserNameOrRealName(name, null);
		SharedUsers users1 = (SharedUsers) session.getAttribute("users");
		List<String> friendsList = friendsService
				.getListUsersId(users1.getId());
		if (usersList != null && usersList.size() > 0) {
			for (int i = 0; i < usersList.size(); i++) {
				for (int s = 0; s < friendsList.size(); s++) {
					if (usersList.get(i).getId().equals(users1.getId())) {
						// 自己
						usersList.get(i)
								.setMsg(FriendsStatusConstant.FRIENDS_ME);
					} else if (usersList.get(i).getId()
							.equals(friendsList.get(i))) {
						// 该好友已经是您的好友
						usersList.get(i).setMsg(
								FriendsStatusConstant.FRIENDS_NOTMEFRIEDS);
					} else if (!usersList.get(i).getId()
							.equals(friendsList.get(i))) {
						// 点击用户名添加哦
						usersList.get(i).setMsg(
								FriendsStatusConstant.FRIENDS_IS_MEFRIEDS);
					}
				}
			}
			return ReturnResult.okAndList(usersList);
		} else {
			return ReturnResult.error();
		}
	}

	@GetMapping("/userDetails")
	@ResponseBody
	public SharedUsersVO userDetails(String userName) throws Exception {
		SharedUsers users = usersService.getSharedUsersByUserName(userName);
		SharedUsersVO usersVO = new SharedUsersVO();
		CopyUtils.Copy(users, usersVO);
		return usersVO;
	}


}
