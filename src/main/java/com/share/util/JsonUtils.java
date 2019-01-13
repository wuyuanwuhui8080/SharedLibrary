package com.share.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * JSON数据处理
 *
 * @author cll
 * @Title: JsonUtil
 * @ProjectName SharedLibrary
 * @time 2018/12/15 17:57
 */
public class JsonUtils {


    /**
     * 把传入的数据进行json化
     *
     * @param obj 传入的数据
     * @return
     */
    public static String JSONString(Object obj) {
        return JSON.toJSON(obj).toString();
    }

    /**
     * 把JSON数据转换成实体
     *
     * @param json
     * @param className
     * @return
     */
    public static Object JSONPojo(String json, Class<?> className) {
        return JSON.parseObject(json, className);
    }

    /**
     * 把json数据转换成list
     *
     * @param json
     * @return
     */
    public static List<?> JSONList(String json, Class<?> className) {
        return JSON.parseArray(json, className);
    }

}