package com.share.users.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.pojo.SharedFriends;
import com.share.users.mapper.SharedFriendsMapper;
import com.share.users.service.SharedFriendsService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Service
public class SharedFriendsServiceImpl
		extends ServiceImpl<SharedFriendsMapper, SharedFriends>
		implements SharedFriendsService {

	@Resource
	private SharedFriendsMapper friendsMapper;

	/**
	 * 根据用户id查询所有好友的id
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	@Override
	public List<String> getListUsersId(String userId) {
		return friendsMapper.findListByUserId(userId);
	}

	/**
	 * 根据用户id 和用户名或者真实姓名模糊查询用户id
	 * 
	 * @param userId
	 *            用户id
	 * @param name
	 *            传入的参数
	 * @return
	 */
	@Override
	public List<String> getListUserIdByuserNameOrRealName(String userId,
			String name) {
		return friendsMapper.findListByUsersByidAndUserNameOrReamName(userId,
				name);
	}

	/**
	 * 添加双向好友请求
	 * 
	 * @param friends
	 * @param friends2
	 * @return
	 */
	@Override
	public boolean saveFirends(SharedFriends friends, SharedFriends friends2) {
		if (super.saveBatch(Arrays.asList(friends, friends2))) {
			return true;
		} else {
			// 手动回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return false;
		}
	}

	/**
	 * 查询是否有该好友的
	 * 
	 * @param meId
	 *            自己的id
	 * @param firendId
	 *            朋友的id
	 * @return
	 */
	@Override
	public Integer getCountByMeIdAndFriendId(String meId, String firendId) {
		LambdaQueryWrapper<SharedFriends> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SharedFriends::getMeId, meId);
		wrapper.eq(SharedFriends::getFriendsId, firendId);
		return super.count(wrapper);
	}


}
