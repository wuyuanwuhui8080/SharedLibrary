package com.share.Recent_Events;

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
