package com.share.blogs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.ShareBlogs;
import com.share.vo.BlosAndUsersAndCommAndGiva;

/**
 * 博客数据接口
 *
 * @author 博博大人
 * @time 2018/12/15 15:46
 */
public interface ShareBlogsMapper extends BaseMapper<ShareBlogs> {
	/**
	 * 根据查询的list集合查询博客
	 * 
	 * @param userId
	 *            用户集合id
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            每页的页数
	 * @return
	 */
	List<ShareBlogs> findListByUserId(@Param("userId") List<String> userId,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize);

	/***
	 * 根据传入的usersid查询博客表，点赞表，回复、评论、用户表的记录
	 * 
	 * @param blogsList
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<BlosAndUsersAndCommAndGiva> findListByUsersIdToBlosgsAndCommAndUsers(
			@Param("blogsList") List<String> blogsList,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * 根据博客id级联删除，博客、点赞、评论、回复表的记录
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteBlogs(@Param("id") String id);
}
