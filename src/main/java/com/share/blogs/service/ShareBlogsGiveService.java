package com.share.blogs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.ShareBlogsGive;

/**
 * 点赞业务接口
 * 
 * @author 博博大人
 * @time 2018/12/21 22:36
 */
public interface ShareBlogsGiveService extends IService<ShareBlogsGive> {

	/**
	 * 添加一条点赞记录
	 * 
	 * @param id
	 * @return
	 */
	Boolean saveBlogGive(ShareBlogsGive shareBlogsGive);

	/**
	 * 根据传入的id删除
	 * 
	 * @param id
	 * @return
	 */
	Boolean deleteBlogGiveById(String id);

	/**
	 * 根据传入的博客id查询总记录数
	 *
	 * @param blogId
	 * @return
	 */
	Integer getCount(String blogId);

}
