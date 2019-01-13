package com.share.charsocket.util;

import com.share.charsocket.bo.Users;
import com.share.charsocket.bo.WebSocketMessage;
import com.share.util.DateUtils;
import com.share.util.JsonUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理消息的工具类
 *
 * @author 博博
 * @Title: MessageUtil
 * @ProjectName SharedLibrary
 * @time 2019/1/10 10:36
 */
public class MessageUtil {

    /**
     * 普通的系统消息序列化
     *
     * @param messageCode       用户传输状态信息
     * @param message           消息信息
     * @param systemMessageCode 系统传输状态信息
     * @param object            需要序列化的任意类型
     * @return String
     */
    public static String messageJSONStringFactory(int messageCode,
                                                  String message, int systemMessageCode, Object object) {
        // 初始化值
        WebSocketMessage socketMessage = new WebSocketMessage(messageCode,
                message, DateUtils.date2String(new Date()));
        Map<String, Object> map = new HashMap<>();
        map.put("systemMessageCode", systemMessageCode);
        map.put("obj", object);
        socketMessage.setBody(map);
        // 进行json化
        return JsonUtils.JSONObjectString(socketMessage);
    }

    /**
     * 给用户发信息
     *
     * @param messageCode 用户传输状态信息
     * @param chatMessage 聊天信息
     * @param user        用户信息
     * @param receiverId  发送人的id
     * @return String
     */
    public static String messageJSONStringFactory(int messageCode,
                                                  String chatMessage, Users user, String receiverId) {
        // 初始化WebSocketMessage信息
        WebSocketMessage socketMessage = new WebSocketMessage(messageCode,
                chatMessage, user, receiverId,
                DateUtils.date2String(new Date()));
        return JsonUtils.JSONObjectString(socketMessage);
    }

}
