package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.share.forum.mapper.SharedForumMapper;
import com.share.forum.service.SharedForumService;
import com.share.forum.service.impl.SharedForumServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 帖子评论实体
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedForumComment extends Model<SharedForumComment> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 用户id（对应share_user主键）
	 */
	private String userId;

	/**
	 * 回复内容
	 */
	private String content;

	/**
	 * 创建时间
	 */
	private Date creationDate;

	/**
	 * 论坛id(shared_forum )id
	 */
	private String forumId;

	/**
	 * 论坛实体
	 */
	@TableField(exist = false)
	private SharedForum sharedForum;

	/**
	 * 用户实体
	 */
	@TableField(exist = false)
	private SharedUsers sharedUsers;

	/**
	 * 回复数
	 */
	@TableField(exist = false)
	private Integer commentCount;

}
