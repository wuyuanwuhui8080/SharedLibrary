package com.share.service;

import com.share.pojo.ShareBlogs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

}
