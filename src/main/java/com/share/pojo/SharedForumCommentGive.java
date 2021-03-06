package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 帖子评论点赞表
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedForumCommentGive extends Model<SharedForumCommentGive> {

	private static final long serialVersionUID = 1L;

	/**
	 * id主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 用户id（对应share_user主键）
	 */
	private String userId;

	/**
	 * 论坛评论Id
	 */
	private String forumCommentId;

	/**
	 * 创建时间
	 */
	private LocalDateTime creationDate;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
