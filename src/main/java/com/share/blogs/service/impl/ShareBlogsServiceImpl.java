package com.share.blogs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.blogs.mapper.ShareBlogsMapper;
import com.share.blogs.service.ShareBlogsService;
import com.share.pojo.ShareBlogs;
import com.share.users.mapper.SharedFriendsMapper;
import com.share.vo.BlosAndUsersAndCommAndGiva;

/**
 * 博客业务实现层
 *
 * @author 博博大人
 * @time 2018/12/14 16:12
 */
@Service
public class ShareBlogsServiceImpl extends
		ServiceImpl<ShareBlogsMapper, ShareBlogs> implements ShareBlogsService {

	@Resource
	private ShareBlogsMapper blogsMapper;

	@Resource
	private SharedFriendsMapper friendsMapper;

	/**
	 * 根据传入的usersid查询博客表的数据总数
	 *
	 * @param userId
	 *            传入的用户id
	 * @return Integer
	 */
	@Override
	public Integer getCountForBlogsByUsersId(String userId) {
		QueryWrapper<ShareBlogs> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		return super.count(wrapper);
	}

	/**
	 * 根据传入的用户id查询博客集合
	 *
	 * @param userId
	 *            传入用户的id
	 * @return List<ShareBlogs>
	 */
	@Override
	public List<ShareBlogs> getFindListForBlogsByUsersId(String userId) {
		QueryWrapper<ShareBlogs> wrapper = new QueryWrapper<>();
		wrapper.eq("user_id", userId);
		return super.list(wrapper);
	}

	/**
	 * 查询自己和自己的所有好友的博客
	 *
	 * @param userId
	 *            自己的id
	 * @return
	 */
	@Override
	public List<ShareBlogs> findListFriendsByUsersId(String userId) {
		List<String> findList = friendsMapper.findListByUserId(userId);
		// Session session = SecurityUtils.getSubject().getSession();
		// SharedUsers users = (SharedUsers) session.getAttribute("users");
		findList.add(userId);
		return blogsMapper.findListByUserId(findList, 0, 5);
	}

	@Override
	public List<BlosAndUsersAndCommAndGiva> findListByUseridToShareBlogs(
			String userId, Integer pageIndex, Integer pageSize) {
		List<String> findList = friendsMapper.findListByUserId(userId);
		findList.add(userId);
		return blogsMapper.findListByUsersIdToBlosgsAndCommAndUsers(findList,
				pageIndex, pageSize);
	}

}
