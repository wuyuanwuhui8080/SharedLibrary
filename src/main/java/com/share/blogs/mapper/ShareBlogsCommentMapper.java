package com.share.blogs.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.ShareBlogsComment;

/**
 * 博客回复dao层
 * 
 * @author 博博大人
 * @time 2018/12/20 21:22
 */
public interface ShareBlogsCommentMapper extends BaseMapper<ShareBlogsComment> {

	/**
	 * 删除评论以及关联回复
	 * 
	 * @param commId
	 *            传入的评论id
	 * @return
	 */
	Integer deleteBlogsComm(@Param("commId") String commId);

}
