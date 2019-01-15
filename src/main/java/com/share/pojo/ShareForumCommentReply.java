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
public class ShareForumCommentReply extends Model<ShareForumCommentReply> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 论坛评论id（对应sharedi_invitation_givet主键）
     */
    private String forumCommentId;

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

    /**
     * 被评论的人的id（user表主键）
     */
    private String commentByuserId;

    /**
     * 评论回复回复id（share_invitation_comment_reply）的id，该字段比较特殊，如果只是回复评论，该字段是空
     */
    private String commReplyId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getForumCommentId() {
        return forumCommentId;
    }

    public void setForumCommentId(String forumCommentId) {
        this.forumCommentId = forumCommentId;
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

    public String getCommentByuserId() {
        return commentByuserId;
    }

    public void setCommentByuserId(String commentByuserId) {
        this.commentByuserId = commentByuserId;
    }

    public String getCommReplyId() {
        return commReplyId;
    }

    public void setCommReplyId(String commReplyId) {
        this.commReplyId = commReplyId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ShareForumCommentReply{" +
        "id=" + id +
        ", forumCommentId=" + forumCommentId +
        ", commentUserId=" + commentUserId +
        ", commentRetext=" + commentRetext +
        ", commentDate=" + commentDate +
        ", commentByuserId=" + commentByuserId +
        ", commReplyId=" + commReplyId +
        "}";
    }
}
