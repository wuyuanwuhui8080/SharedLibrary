package com.share.forum.service;

import com.github.pagehelper.PageInfo;
import com.share.forum.vo.ForumAndComment;
import com.share.forum.vo.SharedForumVO;
import com.share.pojo.SharedForum;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
	PageInfo<SharedForum> findList(Integer typeId,Integer pageIndex, Integer pageSize);

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
	PageInfo<SharedForum> findForymByUserId(String userId, Integer pageIndex);

	/**
	 * 删除一个帖子
	 * 
	 * @param id
	 *            传入的id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	boolean deleteForum(String id);

	/**
	 * 根據文字进行全文引擎搜索
	 * 
	 * @param name
	 *            文字
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            结束
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	List<SharedForumVO> findListByName(String name, Integer pageIndex,
			Integer pageSize);

	/**
	 * 获取七天内热帖
	 *
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	List<SharedForum> findWithinSevenDays();

	/**
	 * 根据传入的集合获取 帖子集合
	 * 
	 * @param forumId
	 *            id集合
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	List<SharedForum> findListStipk(Collection<Object> forumId);

}
