package com.share.forum.vo;

import com.share.forum.bo.CommentBO;
import com.share.forum.bo.CommentGiveBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 *
 * 这个是帖子传输到页面的vo类
 *
 * @author 博博
 * @Title: ForumAndComment
 * @ProjectName SharedLibrary
 * @time 2019/1/20 14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumAndComment {

	/**
	 * 帖子id
	 */
	private String forumId;

	/**
	 * 论坛内容
	 */
	private String forumContent;

	/**
	 * 论坛发布时间
	 */
	private Date forumCreationDate;

	/**
	 * 帖子标题
	 */
	private String forumTitle;

	/**
	 * 回复类型id
	 */
	private Integer forumTypeId;

	/**
	 * 帖子用户真实姓名
	 */
	private String forumUsersRealName;

	/**
	 * 帖子用户id
	 */
	private String forumUsersId;

	/**
	 * 帖子用户头像
	 */
	private String forumUsersHeadImg;


	/**
	 * 分类中文名称
	 */
	private String classifyName;

	/**
	 * 类型的名称
	 */
	private String typeName;

	/**
	 * 分类id
	 */
	private Integer classId;

	/**
	 * 存储当前帖子的所有回复数
	 */
	private Integer countReply;

	/*-------------------------------上面是帖子属性-----------------------------------------------------------*/
	/*-------------------------------下面是回复属性-----------------------------------------------------------*/

	/**
	 * 存放回复集合
	 */
	private List<CommentBO> commentBOList;

	/**
	 * 点赞用户集合
	 */
	private List<CommentGiveBO> commentGiveBOList;

}
