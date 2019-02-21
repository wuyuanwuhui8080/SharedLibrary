package com.share.users.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedFriends;

/**
 * 好友业务接口
 * 
 * @author 博博大人
 * @time 2018/12/15 15:39
 */
public interface SharedFriendsService extends IService<SharedFriends> {

	/**
	 * 根据用户id查询所有好友的id
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
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
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	List<String> getListUserIdByuserNameOrRealName(String userId, String name);

	/**
	 * 添加一条新好友的方法
	 * 
	 * @param friends
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	boolean saveFirends(SharedFriends friends, SharedFriends friends2);

	/**
	 * 查询是否有当前好友，如果有就返回1 否则 0
	 * 
	 * @param meId
	 *            自己的id
	 * @param firendId
	 *            朋友的id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	Integer getCountByMeIdAndFriendId(String meId, String firendId);


}
