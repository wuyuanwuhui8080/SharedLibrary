package com.share.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表 的视图表示对象
 * 
 * @author 博博大人
 * @time 2018/12/16 20:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedUsersVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * user 主键
	 */
	private String id;

	/**
	 * 名字
	 */
	private String userName;


	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date creationDate;

	/**
	 * 职位名称
	 */
	private String positionName;

	/**
	 * 角色外键 （对应sharedl_position的id）
	 */
	private Integer positionId;


	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 个人介绍
	 */
	private String individual;

	public String getIndividual() {
		return (individual == null || individual.equals("")) ? "这人比较懒，没有介绍..."
				: individual;
	}

	/**
	 * 用户头像
	 */
	private String headImg;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 非数据库的字段 ，需要 @TableField(exist = false) 扩展字段 用来计算年龄
	 */
	private Integer age;

	private String sexName;

	public String getSexName() {
		return sex == 1 ? "男" : "女";
	}

	/**
	 * 好友状态
	 */
	private Integer msg;

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
					if (dayOfMonthNow < dayOfMonthBirth)
						age--;
				} else {
					age--;
				}
			}
			return age;
		} else {
			return 0;
		}

	}

}
