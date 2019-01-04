package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户邮箱
 * </p>
 *
 * @author Bean
 * @since 2018-12-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedEmail extends Model<SharedEmail> {

    private static final long serialVersionUID = 1L;


    /**
     * 邮箱id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 发件人id
     */
    private String hairId;

    /**
     * 收件人id
     */
    private String receiveId;

    /**
     * 发送时间
     */
    private Date creationDate;

    /**
     * 摘要
     */
    private String emailDigest;

    /**
     * 内容
     */
    private String emailContent;

    /**
     * 读取状态(
     * 5:草稿)
     */
    private Integer state;

    //--------------------------拓展列------------------------------

    /**
     * 收件人姓名
     */
    private String receiveName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
