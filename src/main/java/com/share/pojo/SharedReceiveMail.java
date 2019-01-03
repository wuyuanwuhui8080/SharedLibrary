package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 邮件收件表
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-03
 */
@Data
public class SharedReceiveMail extends Model<SharedReceiveMail> {

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
     * 发件人UserName
     */
    private String hairName;

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
     * 读取状态(1:已读,2:未读,3:重要4:删除)
     */
    private Integer state;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
