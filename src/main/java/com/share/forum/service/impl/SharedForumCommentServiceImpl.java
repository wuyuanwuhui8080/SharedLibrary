package com.share.forum.service.impl;

import com.share.constant.EventConstant;
import com.share.constant.PageConstant;
import com.share.pojo.SharedForumComment;
import com.share.forum.mapper.SharedForumCommentMapper;
import com.share.forum.service.SharedForumCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.recent_events.Recent_Events;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
public class SharedForumCommentServiceImpl
        extends ServiceImpl<SharedForumCommentMapper, SharedForumComment>
        implements SharedForumCommentService {

    @Resource
    private SharedForumCommentMapper forumCommentMapper;

    @Resource
    private Recent_Events recent_events;

    /**
     * 添加一个回复
     *
     * @param comment 传入的实体
     * @return
     */
    @Override
    public boolean saveComment(SharedForumComment comment) {
        comment.setCreationDate(new Date());
        boolean save = super.save(comment);
        //调用回复事件,传入事件id
        recent_events.setEvent(EventConstant.REPLY_EVENT, comment.getId());
        return save;
    }

    /**
     * 删除评论以及回复
     *
     * @param commentId 传入的id
     * @return
     */
    @Override
    public boolean deleteComment(String commentId) {
        return forumCommentMapper.deleteComment(commentId) > 0 ? true : false;
    }

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
