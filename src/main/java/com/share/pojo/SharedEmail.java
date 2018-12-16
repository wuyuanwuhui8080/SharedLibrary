package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
     * 自己的id
     */
    private String meId;

    /**
     * 朋友id
     */
    private String friendsId;

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
     * 邮件状态
     */
    private Integer state;

    //--------------------------拓展列------------------------------

    /**
     * 发件人姓名
     */
    private String friend_name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
