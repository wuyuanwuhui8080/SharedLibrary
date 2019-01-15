package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-15
 */
public class SharediForumComment extends Model<SharediForumComment> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 论坛id（对应share_invitation主键）
     */
    private String forumId;

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
    private LocalDateTime commentDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentRetext() {
        return commentRetext;
    }

    public void setCommentRetext(String commentRetext) {
        this.commentRetext = commentRetext;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SharediForumComment{" +
                "id=" + id +
                ", forumId=" + forumId +
                ", commentUserId=" + commentUserId +
                ", commentRetext=" + commentRetext +
                ", commentDate=" + commentDate +
                "}";
    }
}
