package com.share.forum.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 回复点赞bo
 *
 * @author 博博
 * @Title: CommentGiveBO
 * @ProjectName SharedLibrary
 * @time 2019/1/20 16:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentGiveBO {

	/**
	 * 回复id
	 */
	private String commentId;

	/**
	 * 评论用户id
	 */
	private String userId;

	/**
	 * 点赞id
	 */
	private String giveId;

}
