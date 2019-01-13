package com.share.charsocket.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * 用于储存消息 因为json序列化不能序列化空值，所以要用这个类
 *
 * @author 博博
 * @Title: FriendAndUsers
 * @ProjectName SharedLibrary
 * @time 2019/1/12 22:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendAndUsers implements Serializable {
	/**
	 * 接收人 id
	 */
	private String receiverId;

	/**
	 * 自己的id
	 */
	private String myId;

    /**
     * 朋友的头像
     */
	private String receiverHeadImg;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 接收时间
	 */
	private String time;
	/**
	 * 接收消息
	 */
	private String message;
}
