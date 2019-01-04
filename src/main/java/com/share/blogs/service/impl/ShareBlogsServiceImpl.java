package com.share.blogs.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
		List<String> findList = new ArrayList<>();
		findList.add(userId);
		return blogsMapper.findListByUserId(findList, 0, 5);
	}

	/**
	 * 查询用户id的好友以及自己的博客、评论、点赞、还有回复
	 * 
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<BlosAndUsersAndCommAndGiva> findListByUseridToShareBlogs(
			String userId, Integer pageIndex, Integer pageSize) {
		List<String> findList = friendsMapper.findListByUserId(userId);
		findList.add(userId);
		List<BlosAndUsersAndCommAndGiva> list = blogsMapper
				.findListByUsersIdToBlosgsAndCommAndUsers(findList, pageIndex,
						pageSize);
		return list;
	}

	/**
	 * 增加博客记录
	 * 
	 * @param shareBlogs
	 *            传入的实体对象
	 * @return
	 */
	@Override
	public boolean saveBlos(ShareBlogs shareBlogs) {
		shareBlogs.setCreationDate(new Date());
		return super.save(shareBlogs);
	}

	/**
	 * 根据博客id级联删除，博客、点赞、评论、回复表的记录
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteBlogs(String id) {
		return blogsMapper.deleteBlogs(id) > 0 ? true : false;
	}

}
