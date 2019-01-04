package com.share.users.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedFriends;

/**
 * 好友业务接口
 * @author 博博大人
 * @time 2018/12/15 15:39
 */
public interface SharedFriendsService extends IService<SharedFriends> {

    /**
     * 根据用户id查询所有好友的id
     * @param userId 用户ID
     * @return
     */
    List<String> getListUsersId(String userId);


	/**
	 * 根据用户id 和用户名或者真实姓名模糊查询
	 * 
	 * @param userId
	 *            用户id
	 * @param name
	 *            传入的参数
	 * @return
	 */
	List<String> getListUserIdByuserNameOrRealName(String userId, String name);
}
