package com.share.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
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
@Document(indexName = "forums", type = "forum")
public class SharedForumVO {

    /**
     * id主键
     */
    @Id
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
     * 分类id
     */
    private String classId;

    /**
     * 标题
     */
    private String title;
}
