package com.share.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.share.constant.PageConstant;
import com.share.pojo.SharedForum;
import com.share.pojo.SharedForumComment;
import com.share.forum.mapper.SharedForumCommentMapper;
import com.share.forum.service.SharedForumCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Service
public class SharedForumCommentServiceImpl extends ServiceImpl<SharedForumCommentMapper, SharedForumComment> implements SharedForumCommentService {
    @Resource
    private SharedForumCommentMapper forumCommentMapper;

    /**
     * 个人页面根据userid获取最近的回帖
     *
     * @param userId    用户Id
     * @param pageIndex 分页查看
     * @return 最近的回帖
     */
    @Override
    public List<SharedForumComment> findForymCommentByUserID(String userId, Integer pageIndex) {
        List<SharedForumComment> forumComments = forumCommentMapper.findForymCommentByUserID(userId, pageIndex, PageConstant.PAGESIZE);
        return forumComments;
    }
}
