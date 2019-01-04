package com.share.blogs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.blogs.mapper.ShareBlogsCommentReplyMapper;
import com.share.blogs.service.ShareBlogsCommentReplyService;
import com.share.pojo.ShareBlogsCommentReply;

/**
 * 评论回复实现类
 * 
 * @author 博博大人
 * @time 2019/1/1 18:26
 */
@Service
public class ShareBlogsCommentReplyServiceImpl extends
		ServiceImpl<ShareBlogsCommentReplyMapper, ShareBlogsCommentReply>
		implements ShareBlogsCommentReplyService {

	@Resource
	private ShareBlogsCommentReplyMapper blogsCommentReplyMapperl;

	/**
	 * 添加回复评论
	 * 
	 * @param blogsCommentReply
	 *            传入的实体
	 * @return
	 */
	@Override
	public boolean saveBlogsCommentReply(
			ShareBlogsCommentReply blogsCommentReply) {
		return super.save(blogsCommentReply);
	}

	@Override
	public boolean deleteBlosCommReply(String replyId, String byUserId,
			String commId) {
		return blogsCommentReplyMapperl.deleteBlosCommReply(replyId, byUserId,
				commId) > 0 ? true : false;
	}
}
