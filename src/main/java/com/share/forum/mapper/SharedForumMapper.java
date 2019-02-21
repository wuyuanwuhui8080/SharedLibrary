package com.share.forum.mapper;

import com.share.forum.vo.ForumAndComment;
import com.share.pojo.SharedForum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 帖子持久层
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumMapper extends BaseMapper<SharedForum> {

	/**
	 * 查询全部帖子
	 * 
	 * @return
	 */
	List<SharedForum> findList(@Param("typeId") Integer typeId);

	/**
	 * 根据传入的forumId集合进行条件查询
	 * 
	 * @param forumIds
	 *            id集合
	 * @return
	 */
	List<SharedForum> findListStick(@Param("forumIds") Collection<Object> forumIds);

	/**
	 * 查询单个帖子
	 * 
	 * @param forumId
	 *            传入的帖子的id
	 * @return
	 */
	ForumAndComment findListByForumId(@Param("forumId") String forumId);

	/**
	 * 删除帖子
	 * 
	 * @param forumId
	 *            传入的id
	 * @return
	 */
	Integer deleteForum(@Param("forumId") String forumId);

	/**
	 * 获取七天内热帖
	 * 
	 * @return
	 */
	List<SharedForum> findWithinSevenDays();

}
