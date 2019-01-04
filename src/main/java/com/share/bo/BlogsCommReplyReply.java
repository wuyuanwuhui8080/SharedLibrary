package com.share.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 回复，回复评论映射类
 *
 * @author 博博
 * @Title: BlogsCommReplyReply
 * @ProjectName SharedLibrary
 * @time 2019/1/1 19:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogsCommReplyReply {

	/**
	 * 回复回复表对应的用户真实姓名
	 */
	private String repRepUserReaName;

	/**
	 * 回复回复表对应的用户头像
	 */
	private String repRepUserImg;

	/**
	 * 回复回复表对应的用户表id
	 */
	private String repRepUserId;

	/**
	 * 回复回复表内容
	 */
	private String repRepRetext;

	/**
	 * 回复表id
	 */
	private String repRepReplyId;

	/**
	 * 回复回复表id
	 */
	private String repRepId;

	/**
	 * 回复回复表时间
	 */
	private Date repRepDate;
}
