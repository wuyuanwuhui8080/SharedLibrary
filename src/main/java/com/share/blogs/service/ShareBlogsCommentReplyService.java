package com.share.blogs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.ShareBlogsCommentReply;

/**
 * 回复业务接口
 * 
 * @author 博博大人
 * @time 2019/1/1 18:24
 */
public interface ShareBlogsCommentReplyService
		extends IService<ShareBlogsCommentReply> {

	/**
	 * 添加回复评论
	 * 
	 * @param blogsCommentReply
	 *            传入的实体
	 * @return
	 */
	boolean saveBlogsCommentReply(ShareBlogsCommentReply blogsCommentReply);

	/**
	 * 删除回复评论，并且把回复回复评论的一并删除
	 *
	 * @param replyId
	 *            回复表id
	 * @param byUserId
	 *            被回复者id
	 * @param commId
	 *            评论id
	 * @return
	 */
	boolean deleteBlosCommReply(String replyId, String byUserId, String commId);
}
