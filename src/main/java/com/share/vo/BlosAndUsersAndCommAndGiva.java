package com.share.vo;

import java.util.Date;
import java.util.List;

import com.share.bo.BlogsGiveBO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 博博
 * @Title: BlosAndUsersAndCommAndGiva
 * @ProjectName SharedLibrary
 * @time 2018/12/21 1:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlosAndUsersAndCommAndGiva {

	/**
	 * 博客id
	 */
	private String blosId;

	/**
	 * 博客时间
	 */
	private Date blosDate;

	/**
	 * 博客内容
	 */
	private String blosContent;

	/**
	 * 博客对应的用户id
	 */
	private String blosUserId;

	/**
	 * 博客用户对应的图片
	 */
	private String blosUserImg;
	/**
	 * 博客用户对应真实姓名
	 */
	private String blosUserRealName;

	/**
	 * 博客评论id
	 */
	private String blosCommId;
	/**
	 * 评论的文字
	 */
	private String blosCommRetext;

	/**
	 * 博客评论时间
	 */
	private Date blosCommDate;

	/**
	 * 博客评论用户id
	 */
	private String blosCommUsersId;

	/**
	 * 博客评论用户头像
	 */
	private String blosCommUsersImg;

	/**
	 * 博客评论用户真实姓名
	 */
	private String blosCommUsersRealName;

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
	 * 点赞数
	 */
	private Integer giveCount;

	/**
	 * 包括点赞人和点赞表的id
	 */
	private List<BlogsGiveBO> blogsGiveBOList;

}
