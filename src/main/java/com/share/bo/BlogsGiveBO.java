package com.share.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来映射点赞的bo
 *
 * @author 博博
 * @Title: BlogsGiveBO
 * @ProjectName SharedLibrary
 * @time 2018/12/22 0:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogsGiveBO {

	/**
	 * 点赞的id
	 */
	private String giveId;
	/**
	 * 点赞人的id
	 */
	private String giveUserid;

}
