package com.share.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.SharedFriends;

/**
 * 好友数据访问接口
 * @author 博博大人
 * @time 2018/12/15 15:45
 */
public interface SharedFriendsMapper extends BaseMapper<SharedFriends> {

    /**
     * 根据传入的id查询所有好友的id
     * @param usersId
     * @return
     */
    List<String> findListByUserId(@Param("usersId") String usersId);

	/**
	 * 根据传入的id 和真实姓名或者用户名查询
	 * 
	 * @param usersId
	 * @param name
	 * @return
	 */
	List<String> findListByUsersByidAndUserNameOrReamName(
			@Param("usersId") String usersId, @Param("name") String name);
}
