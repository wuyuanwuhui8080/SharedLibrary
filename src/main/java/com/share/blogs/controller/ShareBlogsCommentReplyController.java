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

import com.share.blogs.service.ShareBlogsCommentReplyService;
import com.share.pojo.ShareBlogsCommentReply;
import com.share.pojo.SharedUsers;
import com.share.util.ReturnResult;

/**
 * 评论回复前端控制器
 *
 * @author 博博大人
 * @time 2019/1/1 18:27
 */
@RestController
@RequestMapping("/shareBlogsCommentReply")
public class ShareBlogsCommentReplyController {

    @Resource
    private ShareBlogsCommentReplyService blogsCommentReplyService;

    /**
     * 删除博客评论回复
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteReply/{relpyId}")
    public ReturnResult deleteReply(
            @PathVariable(value = "relpyId") String relpyId) {
        if (blogsCommentReplyService.deleteBlosCommReply(relpyId)) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

    /**
     * 添加评论回复
     *
     * @param blogsCommentReply 传入的评论回复实体
     * @return
     */
    @PostMapping("/saveReply")
    public ReturnResult saveReply(ShareBlogsCommentReply blogsCommentReply,
                                  String byRealName) {
        // 时间设置为系统时间
        blogsCommentReply.setCommentDate(new Date());
        // 获取当前用户
        Session session = SecurityUtils.getSubject().getSession();
        SharedUsers users = (SharedUsers) session.getAttribute("users");
        // 执行添加
        if (blogsCommentReplyService.saveBlogsCommentReply(blogsCommentReply)) {
            // 成功添加之后，把结果回调页面的参数添加
            Map<String, Object> map = new HashMap<>();
            map.put("creatDate", blogsCommentReply.getCommentDate());
            map.put("commentRetext", blogsCommentReply.getCommentRetext());
            map.put("relpyId", blogsCommentReply.getId());
            map.put("byUsersId", blogsCommentReply.getCommentUserId());
            map.put("headImg", users.getHeadImg());
            map.put("realName", users.getRealName());
            map.put("byRealName", byRealName);
            map.put("commReplyId", blogsCommentReply.getCommReplyId());
            map.put("commId", blogsCommentReply.getCommentId());
            return ReturnResult.ok(map);
        }
        return ReturnResult.error();
    }

}
