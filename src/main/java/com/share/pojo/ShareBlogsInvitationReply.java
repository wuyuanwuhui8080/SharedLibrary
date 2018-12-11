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
 * @author Bean
 * @since 2018-12-11
 */
public class ShareBlogsInvitationReply extends Model<ShareBlogsInvitationReply> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 论坛评论id（对应sharedi_invitation_givet主键）
     */
    private String invitationGivetId;

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

    public String getInvitationGivetId() {
        return invitationGivetId;
    }

    public void setInvitationGivetId(String invitationGivetId) {
        this.invitationGivetId = invitationGivetId;
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
        return "ShareBlogsInvitationReply{" +
        "id=" + id +
        ", invitationGivetId=" + invitationGivetId +
        ", commentUserId=" + commentUserId +
        ", commentRetext=" + commentRetext +
        ", commentDate=" + commentDate +
        "}";
    }
}
