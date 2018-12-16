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
import java.util.Calendar;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 个人介绍
     */
    private String individual;

    /**
     * 用户头像
     */
    private String headImg;


    /**
     * 对应shared_state的主键
     */
    private Integer stateId;

    /**
     * 封号的次数
     */
    private Integer stopNum;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date creationDate;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateDate;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 非数据库的字段 ，需要 @TableField(exist = false) 扩展字段
     * 用来计算年龄
     */
    @TableField(exist = false)
    private Integer age;

    /**
     * 根据生日计算出年龄
     *
     * @return
     */
    public Integer getAge() {
        if (birthday != null) {
            Calendar cal = Calendar.getInstance();
            if (cal.before(this.birthday)) {
                throw new IllegalArgumentException(
                        "The birthDay is before Now.It's unbelievable!");
            }
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(this.birthday);

            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) age--;
                } else {
                    age--;
                }
            }
            return age;
        } else {
            return 0;
        }

    }


    /**
     * 违规次数
     */
    private Integer violationNum;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
