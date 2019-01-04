package com.share.blogs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.blogs.mapper.ShareBlogsCommentMapper;
import com.share.blogs.service.ShareBlogsCommentService;
import com.share.pojo.ShareBlogsComment;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Service
public class ShareBlogsCommentServiceImpl
		extends ServiceImpl<ShareBlogsCommentMapper, ShareBlogsComment>
		implements ShareBlogsCommentService {

	@Resource
	private ShareBlogsCommentMapper blogsCommentMapper;

	/**
	 * 添加某条博客下的某个评论
	 * 
	 * @param shareBlogsComment
	 * @return
	 */
	@Override
	public boolean saveBlogsComment(ShareBlogsComment shareBlogsComment) {
		return super.save(shareBlogsComment);
	}

	/**
	 * 删除评论以及关联回复
	 * 
	 * @param commId
	 *            传入的回复id
	 * @return
	 */
	@Override
	public boolean deleteBlogsCommet(String commId) {
		return blogsCommentMapper.deleteBlogsComm(commId) > 0 ? true : false;
	}
}
