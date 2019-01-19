package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 帖子实体类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedForum extends Model<SharedForum> {

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
    private Date creationDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 分类id
     */
    private String classId;

    /**
     * 标题
     */
    private String title;

    @TableField("type_id")
    private Integer typeId;

    /**
     * 额外字段，用于存放每个帖子的用户信息
     */
    @TableField(exist = false)
    private SharedUsers sharedUsers;

    /**
     * 用于存放帖子下的回复和评论的次数
     */
    @TableField(exist = false)
    private Integer commCounts;

    /**
     * 用于存放每个帖子的类型
     */
    @TableField(exist = false)
    private SharedForumType forumType;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
