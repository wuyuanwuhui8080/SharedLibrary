package com.share.blogs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.share.blogs.service.ShareBlogsService;
import com.share.pojo.ShareBlogs;
import com.share.util.ReturnResult;
import com.share.vo.BlosAndUsersAndCommAndGiva;

/**
 * 博客前端控制器
 * 
 * @author 博博大人
 * @time 2018/12/20 11:40
 */
@Controller
@RequestMapping("/shareBlogs")
public class ShareBlogsController {

	@Resource
	private ShareBlogsService blogsService;

	/**
	 * 第一次访问初始化博客列表
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/goBlos/{userId}")
	public String goBlos(@PathVariable String userId, Model model) {
		// 根据用户id查询前五条数据
		List<BlosAndUsersAndCommAndGiva> shareBlogsList = blogsService
				.findListByUseridToShareBlogs(userId, 0, 5);
		model.addAttribute("shareBlogsList", shareBlogsList);
		return "background/users/users_blogs";
	}

	/**
	 * 转到编辑博客的页面
	 * 
	 * @return
	 */
	@GetMapping("/goEditorsBlos")
	public String goEditorsBlos() {
		return "background/users/blog_editors";
	}

	/**
	 * 添加博客方法
	 * 
	 * @param shareBlogs
	 * @return
	 */
	@PostMapping("/saveBlos")
	@ResponseBody
	public ReturnResult saveBlos(ShareBlogs shareBlogs) {
		// 判断是否添加成功
		if (blogsService.saveBlos(shareBlogs)) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error("发博客失败！");
		}
	}

	/**
	 * 删除博客 的方法
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteBlos/{id}")
	@ResponseBody
	public ReturnResult deleteBlos(@PathVariable String id) {
		// 判断是否删除成功
		if (blogsService.deleteBlogs(id)) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error();
		}
	}

	/**
	 * 加载更多博客 数据
	 * 
	 * @param userId
	 * @param pageIndex
	 * @return
	 */
	@GetMapping("/toManyList/{userId}/{pageIndex}")
	@ResponseBody
	public ReturnResult toManyList(@PathVariable String userId,
			@PathVariable Integer pageIndex) {
		pageIndex = (pageIndex - 1) * 5;
		return ReturnResult.okAndList(blogsService
				.findListByUseridToShareBlogs(userId, pageIndex, 5));
	}

}
