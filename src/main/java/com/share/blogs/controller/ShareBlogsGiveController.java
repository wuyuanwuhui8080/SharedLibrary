package com.share.blogs.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.share.blogs.service.ShareBlogsGiveService;
import com.share.pojo.ShareBlogsGive;
import com.share.util.ReturnResult;

/**
 * 点赞表前端控制器
 * 
 * @author 博博大人
 * @time 2018/12/22 0:00
 */
@RestController
@RequestMapping("/shareBlogsGive")
public class ShareBlogsGiveController {

	@Resource
	private ShareBlogsGiveService blogsGiveService;

	/**
	 * 点赞记录管理
	 * 
	 * @param shareBlogsGive
	 * @return
	 */
	@PostMapping("/getGiveNum")
	public ReturnResult getGiveNum(ShareBlogsGive shareBlogsGive,
			@RequestParam("giveId") String giveId) {
		if (giveId.equals("0")) {
			if (blogsGiveService.saveBlogGive(shareBlogsGive)) {
				// 这里0是向前端发送状态，0表示这是一个没有点赞过的
				return ReturnResult.ok(0);
			} else {
				return ReturnResult.error("点赞失败!");
			}
		} else {
			if (blogsGiveService.deleteBlogGiveById(giveId)) {
				return ReturnResult.ok(1);
			} else {
				return ReturnResult.error("取消点赞失败!");
			}
		}
	}

}