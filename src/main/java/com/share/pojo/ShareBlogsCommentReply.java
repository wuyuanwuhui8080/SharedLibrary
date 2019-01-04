package com.share.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.share.vo.SharedUsersVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 博客评论回复表
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareBlogsCommentReply extends Model<ShareBlogsCommentReply> {

	private static final long serialVersionUID = 1L;

	/**
	 * id主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 博客评论id（对应share_blogs_comment主键）
	 */
	private String commentId;

	/**
	 * 评论的人的id（user表主键）
	 */
	private String commentUserId;

	/**
	 * 评论内容
	 */
	private String commentRetext;

	/**
	 * 评论时间
	 */
	private Date commentDate;

	/**
	 * 用来存储每个回复的用户
	 */
	@TableField(exist = false)
	private SharedUsersVO sharedUsersVO;

	/**
	 * 被评论id
	 */
	@TableField(value = "comment_byuser_id")
	private String commentByuserId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
