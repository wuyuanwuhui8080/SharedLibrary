package com.share.forum.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * 回复字段Bo
 *
 * @author 博博
 * @Title: CommentBO
 * @ProjectName SharedLibrary
 * @time 2019/1/20 15:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentBO {

	/**
	 * 回复id
	 */
	private String commentId;

	/**
	 * 回复内容
	 */
	private String commentContent;
	/**
	 * 回复点赞数量
	 */
	private Integer commentGive;

	/**
	 * 回复用户id
	 */
	private String commentUsersId;

	/**
	 * 回复用户头像
	 */
	private String commentUsersHeadImg;

	/**
	 * 回复真实姓名
	 */
	private String commentUsersRealName;

	/**
	 * 职位信息
	 */
	private Integer commentUsersPositionid;

	/**
	 * 回帖时间
	 */
	private Date commentCreationTime;


}
