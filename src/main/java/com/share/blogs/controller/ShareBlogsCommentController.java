package com.share.blogs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.share.blogs.service.ShareBlogsCommentService;
import com.share.pojo.ShareBlogsComment;
import com.share.pojo.SharedUsers;
import com.share.util.ReturnResult;

/**
 * 博客评论前端控制器
 *
 * @author 博博大人
 * @time 2018/12/30 14:18
 */
@RestController
@RequestMapping("/shareBlogsComment")
public class ShareBlogsCommentController {

    @Resource
    private ShareBlogsCommentService commentService;

    /**
     * 添加一条评论
     *
     * @param blogsComment
     * @return
     */
    @PostMapping("/saveBlosgComm")
    public ReturnResult saveBlosgComm(ShareBlogsComment blogsComment) {
        Map<String, Object> map = new HashMap<>();
        Session session = SecurityUtils.getSubject().getSession();
        SharedUsers users = (SharedUsers) session.getAttribute("users");
        blogsComment.setCommentDate(new Date());
        // 执行添加操作
        if (commentService.saveBlogsComment(blogsComment)) {
            // 添加成功后把数据封装起来回显给用户
            map.put("commentDate", blogsComment.getCommentDate());
            map.put("realName", users.getRealName());
            map.put("commId", blogsComment.getId());
            map.put("headImg", users.getHeadImg());
            map.put("blogsComm", blogsComment.getCommentRetext());
            return ReturnResult.ok(map);
        }
        return ReturnResult.error();
    }

    /**
     * 删除评论,并带删除评论下的回复
     *
     * @param commId 传入的评论id
     * @return
     */
    @PostMapping("/deleteblogComm/{commId}")
    public ReturnResult deleteblogComm(@PathVariable String commId) {
        // 判断是否删除成功
        if (commentService.deleteBlogsCommet(commId)) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

}
