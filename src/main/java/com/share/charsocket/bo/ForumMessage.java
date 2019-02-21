package com.share.charsocket.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * Redis 论坛缓存数据类
 *
 * @author 博博
 * @Title: ForumMessage
 * @ProjectName SharedLibrary
 * @time 2019/2/8 19:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumMessage {

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 创建时间
	 */
	private String  creationTime;

}
