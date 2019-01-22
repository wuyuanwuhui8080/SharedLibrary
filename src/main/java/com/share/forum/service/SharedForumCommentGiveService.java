package com.share.forum.service;

import com.share.pojo.SharedForumCommentGive;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 点赞业务逻辑层
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumCommentGiveService
		extends IService<SharedForumCommentGive> {

	/**
	 * 添加一条点赞记录
	 * 
	 * @param forumCommentGive
	 *            传入的实体
	 * @return
	 */
	Boolean saveCommentGive(SharedForumCommentGive forumCommentGive);

	/**
	 * 删除一条点赞记录
	 * 
	 * @param id
	 *            传入的点赞id
	 * @return
	 */
	Boolean deleteCommentGive(String id);

}
