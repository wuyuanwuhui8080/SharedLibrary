package com.share.users.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.pojo.SharedlFriendRequest;
import com.share.users.mapper.SharedlFriendRequestMapper;
import com.share.users.service.SharedlFriendRequestService;

/**
 * 好友请求实现类
 * 
 * @author 博博大人
 * @time 2018/12/16 21:38
 */
@Service
public class SharedlFriendRequestServiceImpl extends ServiceImpl<SharedlFriendRequestMapper, SharedlFriendRequest> implements SharedlFriendRequestService {

	/**
	 * 添加好友请求
	 * 
	 * @param friendRequest
	 * @return
	 */
	@Override
	public Boolean saveFriendRequst(SharedlFriendRequest friendRequest) {
		friendRequest.setCreationDate(new Date());
		return super.save(friendRequest);
	}

	/**
	 * 是否存在一样的请求
	 * 
	 * @param friendRequest
	 * @return
	 */
	@Override
	public Boolean getFriendRequstEixt(SharedlFriendRequest friendRequest) {
		QueryWrapper<SharedlFriendRequest> wrapper = new QueryWrapper<>();
		wrapper.eq("me_id", friendRequest.getMeId());
		wrapper.eq("request_id", friendRequest.getRequestId());
		if (super.count(wrapper) > 0) {
			return true;
		}
		return false;
	}
}
