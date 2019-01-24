package com.share.forum.controller;

import com.share.forum.service.SharedForumCommentService;
import com.share.forum.service.SharedForumService;
import com.share.forum.vo.ForumAndComment;
import com.share.pojo.SharedForum;
import com.share.pojo.SharedForumComment;
import com.share.util.ReturnResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 评论前端控制器
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Controller
@RequestMapping("/sharedForumComment")
public class SharedForumCommentController {

    @Resource
    private SharedForumCommentService forumCommentService;

    @Resource
    private SharedForumService forumService;

    /**
     * 添加一条回复
     *
     * @param comment 回复实体
     * @return
     */
    @PostMapping("/saveComment")
    @ResponseBody
    public ReturnResult saveComment(SharedForumComment comment) {
        if (forumCommentService.saveComment(comment)) {
            return ReturnResult.ok(comment);
        }
        return ReturnResult.error("回复失败！");
    }

    /**
     * 删除评论
     *
     * @param commentId 传入的id'
     * @return
     */
    @PostMapping("/deleteComment")
    public ReturnResult deleteComment(String commentId) {
        if (forumCommentService.deleteComment(commentId)) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

    @RequestMapping("/toForumDetailed/{comment}")
    public String toForumDetailed(@PathVariable String comment) {
        SharedForumComment byId = forumCommentService.getById(comment);
        return "redirect:/sharedForum/goForumDetailed/" + byId.getForumId();
    }


}
