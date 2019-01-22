package com.share.forum.mapper;

import com.share.pojo.SharedForumComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 回复接口
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumCommentMapper
		extends BaseMapper<SharedForumComment> {

	/**
	 * 删除评论以及点赞
	 * 
	 * @param id
	 *            评论的id
	 * @return
	 */
	Integer deleteComment(@Param("id") String id);

}
