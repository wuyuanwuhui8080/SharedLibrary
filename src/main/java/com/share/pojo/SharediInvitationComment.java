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
 * 论坛评论
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharediInvitationComment extends Model<SharediInvitationComment> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 帖子id（对应share_invitation主键）
     */
    private String invitationId;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
