package com.share.blogs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.ShareBlogsComment;

/**
 * 评论表业务区
 * 
 * @author 博博大人
 * @time 2018/12/30 14:32
 */
public interface ShareBlogsCommentService extends IService<ShareBlogsComment> {

	/**
	 * 添加评论
	 * 
	 * @param shareBlogsComment
	 * @return
	 */
	boolean saveBlogsComment(ShareBlogsComment shareBlogsComment);

	/**
	 * 删除评论以及关联回复
	 * 
	 * @param commId
	 *            传入的回复id
	 * @return
	 */
	boolean deleteBlogsCommet(String commId);

}
