package com.share.forum.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.share.constant.GiveConstant;
import com.share.forum.service.SharedForumCommentGiveService;
import com.share.forum.service.SharedForumCommentService;
import com.share.pojo.SharedForumCommentGive;
import com.share.util.JsonUtils;
import com.share.util.ReturnResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 评论点赞前端控制器
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@RestController
@RequestMapping("/sharedForumCommentGive")
public class SharedForumCommentGiveController {

	@Resource
	private SharedForumCommentGiveService forumCommentGiveService;

	/**
	 * 点赞/取消点赞的操作
	 * 
	 * @param giveJson
	 *            传入的json字符串
	 * @param type
	 *            操作类型 0:点赞/1:取消点赞
	 * @return
	 */
	@PostMapping("/commentGive")
	public ReturnResult commentGive(String giveJson, Integer type) {
		Map<String, Object> map = new HashMap<>();
		// 把json数据转换为实体
		SharedForumCommentGive forumCommentGive = (SharedForumCommentGive) JsonUtils
				.JSONPojo(giveJson, SharedForumCommentGive.class);
		// 点赞操作
		if (type == GiveConstant.GIVE_TYPE_0) {
			if (forumCommentGiveService.saveCommentGive(forumCommentGive)) {
				// 获取点赞记录
				int count = forumCommentGiveService
						.count(new LambdaQueryWrapper<SharedForumCommentGive>()
								.eq(SharedForumCommentGive::getForumCommentId,
										forumCommentGive.getForumCommentId()));
				map.put("giveId", forumCommentGive.getId());
				map.put("cuount", count);
				map.put("commentId", forumCommentGive.getForumCommentId());
				return ReturnResult.ok(map);
			} else {
				return ReturnResult.error("点赞失败！");
			}
			// 取消点赞
		} else if (type == GiveConstant.GIVE_TYPE_1) {

			if (forumCommentGiveService
					.deleteCommentGive(forumCommentGive.getId())) {
				// 获取点赞记录
				int count = forumCommentGiveService
						.count(new LambdaQueryWrapper<SharedForumCommentGive>()
								.eq(SharedForumCommentGive::getForumCommentId,
										forumCommentGive.getForumCommentId()));
				map.put("cuount", count);
				map.put("commentId", forumCommentGive.getForumCommentId());
				return ReturnResult.ok(map);
			}else{
				return ReturnResult.error("取消点赞失败！");
			}
		}
		return ReturnResult.error("连接失败！");
	}

}
