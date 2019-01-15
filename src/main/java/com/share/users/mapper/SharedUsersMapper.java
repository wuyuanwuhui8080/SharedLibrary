package com.share.users.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.SharedUsers;
import com.share.vo.SharedUsersJSONVO;
import com.share.vo.SharedUsersVO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户自定义数据接口
 * 
 * @author 博博大人
 * @time 2018/12/18 22:21
 */
public interface SharedUsersMapper extends BaseMapper<SharedUsers> {

	/**
	 * 根据userId集合获取UserName
	 * @param userId userId集合
	 * @return UserName
	 */
	List<String> findUserNameByUserId(@Param("userId")List userId);

	/**
	 * 根据传入的list集合，批量查询好友数据
	 * 
	 * @param userId
	 * @return
	 */
	List<SharedUsersVO> findListByUserIdList(List<String> userId);

	/**
	 * 根据传入的用户名和职位查询
	 * 
	 * @param name
	 * @param position
	 * @return
	 */
	List<SharedUsersJSONVO> findUsersListByUserNameOrRealName(
			@Param("name") String name, @Param("position") Integer position);

	/**
	 * 删除用户以及用户下的所有东西
	 * 
	 * @param userId
	 *            传入的用户id
	 * @return
	 */
	Integer removeById(@Param("userId") String userId);

}
