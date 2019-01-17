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
 *  帖子评论回复表
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedForumCommentReply extends Model<SharedForumCommentReply> {

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
     * 回复内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime creationDate;

    /**
     * 论坛评论id
     */
    private String commentId;

    /**
     * 被评论的人的id（user表主键）
     */
    private String commentByuserId;

    /**
     * 评论回复回复id（sharedl_forum_comment_reply ）的id，该字段比较特殊，如果只是回复评论，该字段是空
     */
    private String commentReplyId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
