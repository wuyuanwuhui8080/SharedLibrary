package com.share.blogs.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.ShareBlogs;
import com.share.vo.BlosAndUsersAndCommAndGiva;

/**
 * 博客层业务接口
 * @author 博博大人
 * @time 2018/12/14 16:05
 */
public interface ShareBlogsService extends IService<ShareBlogs> {

    /**
     * 获取博客的总数
     * @param userId 传入的用户id
     * @return
     */
    Integer getCountForBlogsByUsersId(String userId);

    /**
     * 获取传入的用户id下的所有博客
     * @param userId 传入用户的id
     * @return
     */
    List<ShareBlogs> getFindListForBlogsByUsersId(String userId);

    /**
     * 查询朋友以及自己的全部博客
     * @param userId 自己的id
     * @return
     */
    List<ShareBlogs> findListFriendsByUsersId(String userId);

	/**
	 * 根据传入的用户id查询博客集合
	 * 
	 * @param userId
	 * @return
	 */
	List<BlosAndUsersAndCommAndGiva> findListByUseridToShareBlogs(String userId,
			Integer pageIndex, Integer pageSize);

	/**
	 * 添加一条博客记录
	 * 
	 * @param shareBlogs
	 *            传入的实体对象
	 * @return
	 */
	boolean saveBlos(ShareBlogs shareBlogs);

	/**
	 * 根据博客id级联删除，博客、点赞、评论、回复表的记录
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteBlogs(String id);

}
