package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedUsers extends Model<SharedUsers> {

    private static final long serialVersionUID = 1L;

    /**
     * user 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 名字
     */
    @TableField("userName")
    private String userName;

    /**
     * 真实姓名
     */
    @TableField("realName")
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 角色外键 （对应sharedl_position的id）
     */
    private Integer positionId;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 封号的次数
     */
    private Integer stateId;

    /**
     * 对应shared_state的主键
     */
    private Integer stopNum;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date creationDate;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updateDate;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 违规次数
     */
    private Integer violationNum;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
