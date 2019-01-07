package com.share.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用来用于json化的用户类
 *
 * @author 博博
 * @Title: SharedUsersJSONVO
 * @ProjectName SharedLibrary
 * @time 2019/1/6 22:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedUsersJSONVO implements Serializable {
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
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 电话
	 */
	private String phone;
}
