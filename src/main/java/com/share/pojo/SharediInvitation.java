package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 帖子表
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharediInvitation extends Model<SharediInvitation> {

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
	 * 博客内容
	 */
	private String content;

	/**
	 * 创建时间
	 */
	private Date creationDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 分类（对应sharedl_classify主键）
	 */
	private String sharedlClassifyId;

	/**
	 * 帖子标题
	 */
	private String invitationTitle;

	/**
	 * 帖子摘要
	 */
	private String invitationDigest;

	/**
	 * 显示的头部图片
	 */
	private String headImg;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
