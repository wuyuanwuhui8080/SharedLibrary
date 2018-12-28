package com.share.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 博客评论表
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareBlogsComment extends Model<ShareBlogsComment> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 博客id（对应share_blogs主键）
     */
    private String blogsId;

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
	 * 用来储存每个评论下的回复
	 */
	@TableField(exist = false)
	private ShareBlogsCommentReply blogsCommentReply;

	/**
	 * 用来储存评论的用户
	 */
	@TableField(exist = false)
	private SharedUsers users;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
