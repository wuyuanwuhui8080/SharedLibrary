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
    private LocalDateTime creationDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getSharedlClassifyId() {
        return sharedlClassifyId;
    }

    public void setSharedlClassifyId(String sharedlClassifyId) {
        this.sharedlClassifyId = sharedlClassifyId;
    }

    public String getInvitationTitle() {
        return invitationTitle;
    }

    public void setInvitationTitle(String invitationTitle) {
        this.invitationTitle = invitationTitle;
    }

    public String getInvitationDigest() {
        return invitationDigest;
    }

    public void setInvitationDigest(String invitationDigest) {
        this.invitationDigest = invitationDigest;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SharediInvitation{" +
        "id=" + id +
        ", userId=" + userId +
        ", content=" + content +
        ", creationDate=" + creationDate +
        ", updateDate=" + updateDate +
        ", sharedlClassifyId=" + sharedlClassifyId +
        ", invitationTitle=" + invitationTitle +
        ", invitationDigest=" + invitationDigest +
        "}";
    }
}
