package com.share.forum.service;

import com.share.pojo.SharedForumComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumCommentService extends IService<SharedForumComment> {

    /**
     * 个人页面根据userid获取最近的回帖
     *
     * @param userId    用户Id
     * @param pageIndex 分页查看
     * @return 最近的回帖
     */
    List<SharedForumComment> findForymCommentByUserID(String userId, Integer pageIndex);
}
