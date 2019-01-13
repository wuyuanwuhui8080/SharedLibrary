package com.share.charsocket.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 储存用户信息
 *
 * @author 博博
 * @Title: Users
 * @ProjectName SharedLibrary
 * @time 2019/1/10 10:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	/**
	 * id
	 */
	private String id;

	/**
	 * 用戶名
	 */
	private String userName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * netty随机发配的地址
	 */
	private String hostLocal;

	/**
	 * 头像
	 */
	private String headImg;

}
