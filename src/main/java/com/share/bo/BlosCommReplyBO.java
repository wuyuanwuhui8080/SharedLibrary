package com.share.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 博客回复映射类
 *
 * @author 博博
 * @Title: BlosCommReplyBO
 * @ProjectName SharedLibrary
 * @time 2018/12/30 15:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlosCommReplyBO {
	/**
	 * 评论回复id
	 */
	private String blosCommReplyId;

	/**
	 * 回复内容
	 */
	private String blosCommReplyRetext;

	/**
	 * 回复时间
	 */
	private Date blosCommReplyDate;

	/**
	 * 回复者的id
	 */
	private String blosCommReplyUsersId;

	/**
	 * 回复者用户的头像
	 */
	private String blosCommReplyUsersImg;
	/**
	 * 回复者用户真实姓名
	 */
	private String blosCommReplyUsersRealName;

	/**
	 * 回复id
	 */
	private String commReplyBlosId;

	/**
	 * 被评论的id
	 */
	private String commentByuserId;

	/**
	 * 被评论的真实姓名
	 */
	private String commentByuserRealName;
}
