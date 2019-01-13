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
 *  论坛评论回复表
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareInvitationCommentReply extends Model<ShareInvitationCommentReply> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 论坛评论id（对应sharedi_invitation_givet主键）
     */
    private String invitationCommentId;

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



    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
