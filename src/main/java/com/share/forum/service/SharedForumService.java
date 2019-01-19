package com.share.forum.service;

import com.github.pagehelper.PageInfo;
import com.share.pojo.SharedForum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 帖子业务逻辑层
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumService extends IService<SharedForum> {

    /**
     * 添加帖子
     *
     * @param sharedForum
     * @return
     */
    Boolean saveForum(SharedForum sharedForum);


    /**
     * 查询全部帖子
     *
     * @param pageIndex 起始页
     * @param pageSize  每页的页数
     * @return
     */
    PageInfo<SharedForum> findList(Integer pageIndex, Integer pageSize);



}
