package com.share.users.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.share.vo.SharedUsersJSONVO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedUsers;
import com.share.vo.SharedUsersVO;

/**
 * Users业务接口
 *
 * @author 博博大人
 * @time 2018/12/13 14:21
 */
public interface SharedUsersService extends IService<SharedUsers> {

	/**
	 * 添加用户
	 *
	 * @param users
	 *            传入的用户实体
	 * @return Boolean
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	Boolean saveNorsalSharedUsers(SharedUsers users);

	/**
	 * 判断用户名是否重复
	 *
	 * @param userName
	 *            传入的用户名
	 * @return Boolean
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	Boolean getSharedUsersGetUserName(String userName);

	/**
	 * 根据用户名 查询是否存在
	 *
	 * @param userName
	 *            传入的用户名
	 * @return SharedUsers
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	SharedUsers getSharedUsersByUserName(String userName);

	/**
	 * 根据用户名或真实姓名查询用户列表
	 * 
	 * @author cll 陈留领 马汇博
	 * @time 2018/12/15 16:05
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	PageInfo<SharedUsersJSONVO> findUsersListByUserNameOrRealName(String name,
																  Integer position, Integer pageIndex, Integer pageSize);

	/**
	 * 根据真实姓名或者用户名查询
	 * 
	 * @param name
	 *            传入的真实姓名或者用户名
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	List<SharedUsers> findUserListBYUserNameorRealName(String name);

	/**
	 * 修改头像
	 * 
	 * @param users
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	Boolean updateUserHeadImg(SharedUsers users);

	/**
	 * 根据传入的自己的id 查询出自己的全部好友
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	List<SharedUsersVO> findListByUsersIdForFriends(List<String> userId);

	/**
	 * 根据id查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	SharedUsers getUserById(String userId);

	/**
	 * 根据传入的参数判断是否存在
	 * 
	 * @param name
	 *            传入的用户名或者真实姓名
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	boolean getUserByUserNameOrRealName(String name);

	/**
	 * 修改信息
	 * 
	 * @param users
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	boolean updateUsers(SharedUsers users);

	/**
	 * 修改用户密码
	 * 
	 * @param users
	 *            传入的实体用户
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	boolean updateUserPassword(SharedUsers users);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 *            传入的用户id
	 * @return
	 */
	boolean deleteUsers(String userId);
}
