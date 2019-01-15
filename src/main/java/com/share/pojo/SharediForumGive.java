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
public class SharediForumGive extends Model<SharediForumGive> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 论坛id（对应share_blogs主键）
     */
    private String forumId;

    /**
     * 点赞人的id（user表主键）
     */
    private String forumUserId;

    /**
     * 创建时间
     */
    private LocalDateTime creationDate;


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

    public String getForumUserId() {
        return forumUserId;
    }

    public void setForumUserId(String forumUserId) {
        this.forumUserId = forumUserId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SharediForumGive{" +
        "id=" + id +
        ", forumId=" + forumId +
        ", forumUserId=" + forumUserId +
        ", creationDate=" + creationDate +
        "}";
    }
}
