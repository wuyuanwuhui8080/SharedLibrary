package com.share.util;

import java.io.Serializable;
import java.util.List;

import com.share.constant.HTTPStutusConstant;

import lombok.Data;

/**
 * 对json数据返回
 *
 * @author 博博大人
 * @time 2018/12/13 8:55
 */
@Data
public class ReturnResult implements Serializable {

	private Integer status;
	private String data;
	private Object obj;
	private String msg;
	private List<?> list;

	public ReturnResult(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public ReturnResult(Integer status, Object msg) {
		this.status = status;
		this.obj = msg;
	}

	public ReturnResult(Integer status, List<?> list) {
		this.status = status;
		this.list = list;
	}

	public ReturnResult(Integer status) {
		this.status = status;
	}

	/**
	 * 返回默认成功状态
	 *
	 * @param ReturnResult
	 */
	public static ReturnResult ok() {
		return new ReturnResult(HTTPStutusConstant.SUCCESS_STRUTS);
	}

	/**
	 * 返回默认成功状态
	 *
	 * @param ReturnResult
	 */
	public static ReturnResult ok(Object msg) {
		return new ReturnResult(HTTPStutusConstant.SUCCESS_STRUTS, msg);
	}

	/**
	 * 返回成功状态
	 *
	 * @param ReturnResult
	 */
	public static ReturnResult okAndList(List<?> list) {
		return new ReturnResult(HTTPStutusConstant.SUCCESS_STRUTS, list);
	}

	/**
	 * 返回失败状态
	 *
	 * @param ReturnResult
	 */
	public static ReturnResult error(String message) {
		return new ReturnResult(HTTPStutusConstant.ERROR_STRUTS, message);
	}

	/**
	 * 返回默认失败状态
	 *
	 * @param ReturnResult
	 */
	public static ReturnResult error() {
		return new ReturnResult(HTTPStutusConstant.ERROR_STRUTS);
	}

	public ReturnResult(String message, Integer status, String data) {
		this.msg = message;
		this.status = status;
		this.data = data;
	}

}
