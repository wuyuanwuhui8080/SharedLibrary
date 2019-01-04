package com.share.blogs.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.ShareBlogsCommentReply;

/**
 * 评论回复表dao访问接口
 * 
 * @author 博博大人
 * @time q 21:13
 */
public interface ShareBlogsCommentReplyMapper
		extends BaseMapper<ShareBlogsCommentReply> {

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
	Integer deleteBlosCommReply(@Param("replyId") String replyId,
			@Param("byUserId") String byUserId, @Param("commId") String commId);

}
