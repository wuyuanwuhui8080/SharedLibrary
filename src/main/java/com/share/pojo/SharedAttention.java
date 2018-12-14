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
 *  关注人的实体
 * </p>
 *
 * @author Bean
 * @since 2018-12-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedAttention extends Model<SharedAttention> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 自己的id
     */
    private String meId;

    /**
     * 关注人的id
     */
    private String attentionId;

    /**
     * 创建时间
     */
    private LocalDateTime creationDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
