package com.share.forum.service;

import com.github.pagehelper.PageInfo;
import com.share.forum.vo.ForumAndComment;
import com.share.pojo.SharedForum;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 帖子业务逻辑层
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumService extends IService<SharedForum> {

	/**
	 * 添加帖子
	 *
	 * @param sharedForum
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	Boolean saveForum(SharedForum sharedForum);

	/**
	 * 查询全部帖子
	 *
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            每页的页数
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	PageInfo<SharedForum> findList(Integer pageIndex, Integer pageSize);

	/**
	 * 查询单个帖子
	 *
	 * @param forumId
	 *            传入的帖子的id
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	ForumAndComment findListByForumId(@Param("forumId") String forumId);

	/**
	 * 个人页面中获取帖子集合
	 *
	 * @param userId
	 *            用户id
	 * @param pageIndex
	 *            分页数
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	List<SharedForum> findForymByUserId(String userId, Integer pageIndex);

	/**
	 * 删除一个帖子
	 * 
	 * @param id
	 *            传入的id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	boolean deleteForum(String id);

}
