package com.share.forum.service.impl;

import com.share.pojo.SharedForumCommentGive;
import com.share.forum.mapper.SharedForumCommentGiveMapper;
import com.share.forum.service.SharedForumCommentGiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Service
public class SharedForumCommentGiveServiceImpl extends
		ServiceImpl<SharedForumCommentGiveMapper, SharedForumCommentGive>
		implements SharedForumCommentGiveService {

	/**
	 * 添加一条点赞记录
	 * 
	 * @param forumCommentGive
	 *            传入的实体
	 * @return
	 */
	@Override
	public Boolean saveCommentGive(SharedForumCommentGive forumCommentGive) {
		forumCommentGive.setCreationDate(LocalDateTime.now());
		return super.save(forumCommentGive);
	}

	/**
	 * 删除一条点赞记录
	 * 
	 * @param id
	 *            传入的点赞id
	 * @return
	 */
	@Override
	public Boolean deleteCommentGive(String id) {
		return super.removeById(id);
	}
}
