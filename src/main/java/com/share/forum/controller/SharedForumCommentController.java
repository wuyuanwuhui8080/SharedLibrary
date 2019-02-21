package com.share.forum.controller;

import com.share.forum.service.SharedForumCommentService;
import com.share.forum.service.SharedForumService;
import com.share.forum.vo.ForumAndComment;
import com.share.pojo.SharedForum;
import com.share.pojo.SharedForumComment;
import com.share.pojo.SharedUsers;
import com.share.users.service.SharedUsersService;
import com.share.util.JsonUtils;
import com.share.util.ReturnResult;
import com.share.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

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

	@Resource
	private SharedUsersService usersService;

	/**
	 * 添加一条回复
	 *
	 * @param comment
	 *            回复实体
	 * @return
	 */
	@PostMapping("/saveComment")
	@ResponseBody
	public ReturnResult saveComment(SharedForumComment comment,
			String commUsersName) {
		SharedUsers users = (SharedUsers) SecurityUtils.getSubject()
				.getSession().getAttribute("users");
		if (StringUtils.isNotNull(commUsersName)) {
			List<String> list = (List<String>) JsonUtils.JSONList(commUsersName,
					String.class);
			for (String i : list) {
				// 如果一致了表示@了自己
				if (users.getUserName().equals(i)) {
					return ReturnResult.error("不可以@自己!");
				}
				// 表示不存在
				if (usersService.getSharedUsersByUserName(i) == null) {
					return ReturnResult.error("您@的用户["+i+"]不存在!");
				}
			}
		}
		if (forumCommentService.saveComment(comment)) {
			return ReturnResult.ok(comment);
		}
		return ReturnResult.error("回复失败！");
	}

	/**
	 * 删除评论
	 *
	 * @param commentId
	 *            传入的id'
	 * @return
	 */
	@PostMapping("/deleteComment")
	@ResponseBody
	public ReturnResult deleteComment(String commentId) {
		if (forumCommentService.deleteComment(commentId)) {
			return ReturnResult.ok();
		}
		return ReturnResult.error("删除失败！");
	}

	@RequestMapping("/toForumDetailed/{comment}")
	public String toForumDetailed(@PathVariable String comment) {
		SharedForumComment byId = forumCommentService.getById(comment);
		return "redirect:/sharedForum/goForumDetailed/" + byId.getForumId();
	}

}
