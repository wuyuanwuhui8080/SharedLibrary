package com.share.users.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.forum.mapper.SharedForumMapper;
import com.share.forum.mapper.SharedForumVOReposiory;
import com.share.forum.service.SharedForumService;
import com.share.pojo.SharedForum;
import com.share.vo.SharedUsersJSONVO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.constant.PositionConstant;
import com.share.constant.StateConstant;
import com.share.pojo.SharedUsers;
import com.share.users.mapper.SharedUsersMapper;
import com.share.users.service.SharedUsersService;
import com.share.util.ShiroMd5;
import com.share.util.StringUtils;
import com.share.vo.SharedUsersVO;

/**
 * 用户实现类，负责用户的一切操作
 * 
 * @author 博博大人
 * @time 2018/12/13 14:27
 */
@Service
public class SharedUsersServiceImpl
		extends ServiceImpl<SharedUsersMapper, SharedUsers>
		implements SharedUsersService {

	@Resource
	private SharedUsersMapper usersMapper;

	@Resource
	private SharedForumVOReposiory forumVOReposiory;

	@Resource
	private SharedForumMapper forumMapper;

	/**
	 * 执行添加普通用户 用户操作，使用shiro md5进行 1024次加密
	 * 
	 * @param users
	 *            传入的用户实体
	 * @return
	 */
	@Override
	public Boolean saveNorsalSharedUsers(SharedUsers users) {
		String pwd = ShiroMd5.hashMd5(users.getUserName(), users.getPassword());
		users.setCreationDate(new Date());
		users.setPositionId(PositionConstant.NORMAL_USER);
		users.setStateId(StateConstant.NORMAL_USER);
		users.setPassword(pwd);
		users.setHeadImg("bd978735b33f496792673949e70fb2eb!400x400.jpeg");
		return this.save(users);
	}

	/**
	 * 根据传入的用户名 校验是否存在 y ：true n ： false
	 * 
	 * @param userName
	 *            传入的用户名
	 * @return Boolean
	 */
	@Override
	public Boolean getSharedUsersGetUserName(String userName) {
		QueryWrapper<SharedUsers> wrapper = new QueryWrapper<>();
		wrapper.eq("userName", userName);
		if (super.count(wrapper) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据传入的用户名 校验是否存在 存在返回SharedUsers 不存在返回null
	 * 
	 * @param userName
	 *            传入的用户名
	 * @return SharedUsers
	 */
	@Override
	public SharedUsers getSharedUsersByUserName(String userName) {
		/*
		 * QueryWrapper<SharedUsers> wrapper = new QueryWrapper<>();
		 * wrapper.eq("userName",userName);
		 */
		// 使用lambda表达式查询
		LambdaQueryWrapper<SharedUsers> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SharedUsers::getUserName, userName);
		return super.getOne(wrapper);
	}

	/**
	 * 根据用户名或真实姓名查询用户列表
	 * 
	 * @param name
	 *            传入的字符串
	 * @author cll 陈留领 马汇博
	 * @return
	 */
	@Override
	public PageInfo<SharedUsersJSONVO> findUsersListByUserNameOrRealName(
			String name, Integer position, Integer pageIndex,
			Integer pageSize) {
		// 拦截sql
		PageHelper.startPage(pageIndex, pageSize);
		PageInfo<SharedUsersJSONVO> pageInfo = new PageInfo<>(
				usersMapper.findUsersListByUserNameOrRealName(name, position));
		return pageInfo;
	}

	/**
	 * 根据真实姓名或者用户名查询
	 * 
	 * @param name
	 *            传入的真实姓名或者用户名
	 * @return
	 */
	@Override
	public List<SharedUsers> findUserListBYUserNameorRealName(String name) {
		return super.list(new LambdaQueryWrapper<SharedUsers>()
				.eq(SharedUsers::getUserName, name).or()
				.eq(SharedUsers::getRealName, name));
	}

	/**
	 * 修改头像
	 * 
	 * @param users
	 * @return
	 */
	@Override
	public Boolean updateUserHeadImg(SharedUsers users) {
		users.setUpdateDate(new Date());
		return super.updateById(users);
	}

	/**
	 * 查询所有好友
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<SharedUsersVO> findListByUsersIdForFriends(
			List<String> userId) {
		if (StringUtils.isNullToArray(userId)) {
			return null;
		}
		// 根据用户id 查询所有好友的 id
		return usersMapper.findListByUserIdList(userId);
	}

	@Override
	public SharedUsers getUserById(String userId) {
		return super.getById(userId);
	}

	/**
	 * 根据传入的参数判断是否存在
	 *
	 * @param name
	 *            传入的用户名或者真实姓名
	 * @return
	 */
	@Override
	public boolean getUserByUserNameOrRealName(String name) {
		return super.count(new LambdaQueryWrapper<SharedUsers>()
				.eq(SharedUsers::getUserName, name).or()
				.eq(SharedUsers::getRealName, name)) > 0 ? true : false;
	}

	/**
	 * 修改信息
	 *
	 * @param users
	 * @return
	 */
	@Override
	public boolean updateUsers(SharedUsers users) {
		users.setPassword(null);
		users.setUpdateDate(new Date());
		return super.updateById(users);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param users
	 *            传入的实体用户
	 * @return
	 */
	@Override
	public boolean updateUserPassword(SharedUsers users) {
		users.setUpdateDate(new Date());
		return super.updateById(users);
	}

	/**
	 * 删除用户
	 * 
	 * @param userId
	 *            传入的用户id
	 * @return
	 */
	@Override
	public boolean deleteUsers(String userId) {
		SharedForum forum = forumMapper
				.selectOne(new LambdaQueryWrapper<SharedForum>()
						.eq(SharedForum::getUserId, userId));
		forumVOReposiory.deleteById(forum.getId());
		return usersMapper.removeById(userId) > 0 ? true : false;
	}

}
