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
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedlPosition extends Model<SharedlPosition> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员（1）、扫地僧（2）、普通用户（3）
     */
    private String positionName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
