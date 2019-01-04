package com.share.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 博客表
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareBlogs extends Model<ShareBlogs> {

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


    @TableField(exist = false)
    private SharedUsers  users;

	/**
	 * 点赞表对象
	 */
	@TableField(exist = false)
	private List<ShareBlogsGive> blogsGive;

	/**
	 * 点赞数
	 */
	@TableField(exist = false)
	private Integer blogsGiveCount;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
