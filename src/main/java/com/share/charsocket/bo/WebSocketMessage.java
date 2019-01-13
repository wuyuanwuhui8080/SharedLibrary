package com.share.charsocket.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息处理实体
 *
 * @author 博博
 * @Title: WebSocketMessage
 * @ProjectName SharedLibrary
 * @time 2019/1/10 10:32
 */
@Data
public class WebSocketMessage {
	/**
	 * 请求状态
	 */
	private Integer code;
	/**
	 * 发送人信息
	 */
	private Users user;
	/**
	 * 接收人 id
	 */
	private String receiverId;
	/**
	 * 接收时间
	 */
	private String time;
	/**
	 * 接收消息
	 */
	private String message;

	/**
	 * 可以存放在线人数，在线用户列表，code等
	 */
	private Map<String, Object> body = new HashMap<>();

	/**
	 * 三参构造
	 * 
	 * @param code
	 *            请求状态
	 * @param message
	 *            传输的消息
	 * @param dateTime
	 *            传输的时间
	 */
	public WebSocketMessage(int code, String message, String dateTime) {
		this.code = code;
		this.message = message;
		this.time = dateTime;
	}

	/**
	 * 五参构造
	 * 
	 * @param code
	 *            请求状态
	 * @param message
	 *            消息
	 * @param user
	 *            用户信息
	 * @param receiverId
	 *            接收人id
	 * @param time
	 *            发送时间
	 */
	public WebSocketMessage(Integer code, String message, Users user,
			String receiverId, String time) {
		this.code = code;
		this.user = user;
		this.receiverId = receiverId;
		this.time = time;
		this.message = message;
	}

	public WebSocketMessage(){

    }

}
