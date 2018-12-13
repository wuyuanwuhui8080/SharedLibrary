package com.share.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 对json数据返回
 *
 * @author 博博大人
 * @time 2018/12/13 8:55
 */
@Data
@NoArgsConstructor
public class ReturnResult implements Serializable {

    private int status;
    private Object data;
    private String msg;

    public ReturnResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ReturnResult(int status) {
        this.status = status;
    }

    public ReturnResult(int status, Objects data) {
        this.data = data;
        this.status = status;
    }


    /**
     * 返回默认成功状态
     *
     * @param ReturnResult
     */
    public static ReturnResult ok() {
        return new ReturnResult(200);
    }

    /**
     * 返回成功状态
     *
     * @param ReturnResult
     */
    public static ReturnResult okAndObj(Objects obj) {
        return new ReturnResult(200, obj);
    }

    /**
     * 返回失败状态
     *
     * @param ReturnResult
     */
    public static ReturnResult error(String message) {
        return new ReturnResult(500, message);
    }

    public ReturnResult(String message, int status, Object data) {
        this.msg = message;
        this.status = status;
        this.data = data;
    }

}
