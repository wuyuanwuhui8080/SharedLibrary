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
public class SharediForum extends Model<SharediForum> {

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
    private String classifyId;

    /**
     * 论坛标题
     */
    private String forumTitle;

    /**
     * 论坛摘要
     */
    private String forumDigest;

    /**
     * 显示的图片
     */
    private String headImg;


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

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getForumDigest() {
        return forumDigest;
    }

    public void setForumDigest(String forumDigest) {
        this.forumDigest = forumDigest;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SharediForum{" +
        "id=" + id +
        ", userId=" + userId +
        ", content=" + content +
        ", creationDate=" + creationDate +
        ", updateDate=" + updateDate +
        ", classifyId=" + classifyId +
        ", forumTitle=" + forumTitle +
        ", forumDigest=" + forumDigest +
        ", headImg=" + headImg +
        "}";
    }
}
