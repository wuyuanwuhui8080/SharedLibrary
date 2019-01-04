package com.share.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 博客评论工具实体
 *
 * @author 博博
 * @Title: BlogsCommBO
 * @ProjectName SharedLibrary
 * @time 2018/12/30 14:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogsCommBO {

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

}
