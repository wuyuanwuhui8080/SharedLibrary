package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharediInvitationGive extends Model<SharediInvitationGive> {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 帖子id（对应share_blogs主键）
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
