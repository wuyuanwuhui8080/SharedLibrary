package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @author 牛自豪
 * @since 2019-01-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedlClassify extends Model<SharedlClassify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 论坛分类名称
     */
    private String classifyName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
