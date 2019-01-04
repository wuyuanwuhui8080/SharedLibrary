package com.share.users.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.SharedUsers;
import com.share.vo.SharedUsersVO;

/**
 * 用户自定义数据接口
 * 
 * @author 博博大人
 * @time 2018/12/18 22:21
 */
public interface SharedUsersMapper extends BaseMapper<SharedUsers> {

	/**
	 * 根据传入的list集合，批量查询好友数据
	 * 
	 * @param userId
	 * @return
	 */
	List<SharedUsersVO> findListByUserIdList(List<String> userId);

	// List<SharedUsersVO> findListByUserNameOrRealNameForFriends(List<String>
	// userId,String name);

}
