package com.share.enums;

/**
 *
 * Http状态枚举
 *
 * @author 博博
 * @Title: HttpStatus
 * @ProjectName SharedLibrary
 * @time 2019/1/2 20:33
 */
public enum HttpStatus {

	/**
	 * 返回成功状态
	 */
	SUCCESS(200),

	/**
	 * 返回失败状态
	 */
	ERROR(500),

	/**
	 * 返回找不到资源状态
	 */
	NOTFOUND(404);

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	HttpStatus(Integer status) {
		this.status = status;
	}

}
