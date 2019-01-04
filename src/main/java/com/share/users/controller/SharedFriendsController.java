package com.share.users.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.share.constant.FriendsStatusConstant;
import com.share.pojo.SharedUsers;
import com.share.users.service.SharedFriendsService;
import com.share.users.service.SharedUsersService;
import com.share.util.CopyUtils;
import com.share.util.ReturnResult;
import com.share.util.StringUtils;
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
				if (usersList.get(i).getId().equals(users1.getId())) {
					// 自己
					usersList.get(i).setMsg(FriendsStatusConstant.FRIENDS_ME);
					// 判断好友表是否为空
				} else if (StringUtils.isNotNullToArray(friendsList)) { // 不为空就执行判断
					for (int a = 0; a < friendsList.size(); a++) {
						if (usersList.get(i).getId()
								.equals(friendsList.get(a))) {
							// 该好友已经是您的好友
							usersList.get(i).setMsg(
									FriendsStatusConstant.FRIENDS_NOTMEFRIEDS);
						} else if ((a + 1) == friendsList.size() && !usersList
								.get(i).getId().equals(friendsList.get(i))) {
							// 点击用户名添加哦
							usersList.get(i).setMsg(
									FriendsStatusConstant.FRIENDS_IS_MEFRIEDS);
						}
					}
				} else { // 为空就提示可以添加
					// 点击用户名添加哦
					usersList.get(i)
							.setMsg(FriendsStatusConstant.FRIENDS_IS_MEFRIEDS);
				}
			}
			return ReturnResult.okAndList(usersList);
		} else {
			return ReturnResult.error();
		}
	}

	/**
	 * 查看用户详细
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/userDetails")
	@ResponseBody
	public SharedUsersVO userDetails(String userName) throws Exception {
		SharedUsers users = usersService.getSharedUsersByUserName(userName);
		// 进行实体类属性copy
		SharedUsersVO usersVO = new SharedUsersVO();
		CopyUtils.Copy(users, usersVO);
		return usersVO;
	}

	/**
	 * 跳转到全部好友页面
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/goListFriendList/{userId}")
	public String goListFriendList(@PathVariable String userId, Model model) {
		List<String> userIdList = friendsService.getListUsersId(userId);
		List<SharedUsersVO> friendsList = usersService
				.findListByUsersIdForFriends(userIdList);
		model.addAttribute("friendsList", friendsList);
		return "background/users/users_list";
	}

	@GetMapping("/searchFriendList")
	@ResponseBody
	public ReturnResult searchFriendList(
			@RequestParam(value = "name", required = false) String name,
			String userId) {
		List<String> userIds = friendsService
				.getListUserIdByuserNameOrRealName(userId, name);
		List<SharedUsersVO> friendsLiist = usersService
				.findListByUsersIdForFriends(userIds);
		return ReturnResult.okAndList(friendsLiist);
	}

}
