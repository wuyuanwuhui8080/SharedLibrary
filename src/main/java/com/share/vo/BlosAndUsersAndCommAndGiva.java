package com.share.vo;

import java.util.Date;
import java.util.List;

import com.share.bo.BlogsCommBO;
import com.share.bo.BlogsGiveBO;
import com.share.bo.BlosCommReplyBO;

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
	 * 用来放评论集合
	 */
	private List<BlogsCommBO> blogsCommBOList;

	/**
	 * 用来放评论回复
	 */
	private List<BlosCommReplyBO> blosCommReplyBOList;

	/**
	 * 点赞数
	 */
	private Integer giveCount;

	/**
	 * 包括点赞人和点赞表的id
	 */
	private List<BlogsGiveBO> blogsGiveBOList;

}
