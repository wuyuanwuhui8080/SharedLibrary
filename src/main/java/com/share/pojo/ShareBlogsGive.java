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
public class ShareBlogsGive extends Model<ShareBlogsGive> {

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
     * 点赞人的id（user表主键）
     */
    private String giveUserId;

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

    public String getBlogsId() {
        return blogsId;
    }

    public void setBlogsId(String blogsId) {
        this.blogsId = blogsId;
    }

    public String getGiveUserId() {
        return giveUserId;
    }

    public void setGiveUserId(String giveUserId) {
        this.giveUserId = giveUserId;
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
        return "ShareBlogsGive{" +
        "id=" + id +
        ", blogsId=" + blogsId +
        ", giveUserId=" + giveUserId +
        ", creationDate=" + creationDate +
        "}";
    }
}
