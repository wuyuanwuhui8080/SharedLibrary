package com.share.util;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 对json数据返回
 *
 * @author 博博大人
 * @time 2018/12/13 8:55
 */
@Data
public class ReturnResult implements Serializable {

    private int status;
	private String data;
    private String msg;
	private List<?> list;

    public ReturnResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

	public ReturnResult(int status, List<?> list) {
        this.status = status;
		this.list = list;
    }

	public ReturnResult(int status) {
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
	public static ReturnResult okAndList(List<?> list) {
		return new ReturnResult(200, list);
    }

    /**
     * 返回失败状态
     *
     * @param ReturnResult
     */
    public static ReturnResult error(String message) {
        return new ReturnResult(500, message);
    }

    /**
     * 返回默认失败状态
     *
     * @param ReturnResult
     */
    public static ReturnResult error() {
        return new ReturnResult(500);
    }

	public ReturnResult(String message, int status, String data) {
        this.msg = message;
        this.status = status;
        this.data = data;
    }

    public ReturnResult(){

    }
}
