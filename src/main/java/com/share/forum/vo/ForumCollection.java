package com.share.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 帖字收藏工具类
 *
 * @author 博博
 * @Title: ForumCollection
 * @ProjectName SharedLibrary
 * @time 2019/2/19 17:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumCollection {

	/**
	 * 消息
	 */
	private String title;

	/**
	 * 创建时间
	 */
	private String creatDate;

	/**
	 * 帖子id
	 */
	private String forumId;


}
