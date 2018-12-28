package com.share.blogs.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.share.blogs.service.ShareBlogsService;
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

}
