package com.share.enums;

import com.share.util.RedisUtil;

/**
 * reids单例工具类
 *
 * @author 博博
 * @Title: RedisUtilEnums
 * @ProjectName SharedLibrary
 * @time 2019/1/12 23:56
 */
public enum RedisUtilEnums {

    INSTANCE;

    private RedisUtil redisUtil;

    RedisUtilEnums() {
        redisUtil = new RedisUtil();
    }

    public  RedisUtil getInstance() {
        return redisUtil;
    }

}
