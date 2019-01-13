package com.share.recent_events;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 事件类
 *
 * @author 牛自豪
 * @date 2019-01-08-14:49
 */
@Data
public class Event implements Serializable {

    /**
     * 事件对象id
     * 也就是处理的事情
     * 列:SharedlFriendRequest对象的Id
     */
    private String eventid;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 事件描述
     */
    private String eventDescription;

    /**
     * 事件时间
     */
    private Date eventTime;

    /**
     * 事件类型
     */
    private String type;

}
