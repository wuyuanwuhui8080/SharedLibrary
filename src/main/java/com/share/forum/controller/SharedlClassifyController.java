package com.share.forum.controller;

import com.share.forum.service.SharedlClassifyService;
import com.share.util.ReturnResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 分类控制器
 * 
 * @author 博博大人
 * @time 2019/2/21 10:58
 */
@Controller
@RequestMapping("/sharedlClassify")
public class SharedlClassifyController {

    @Resource
    private SharedlClassifyService classifyService;

	/**
	 * 跳转到添加分类页面
	 * 
	 * @return
	 */
	@GetMapping("/goAddForumClass")
	public String goAddForumClass() {
		return "background/forum/add_forum_class";
	}

    /**
     * 添加新分类
     * @param className
     * @return
     */
	@PostMapping("/saveForumClass")
    @ResponseBody
	public ReturnResult saveForumClass(String className){
	    if(classifyService.getClassfyByName(className)){
            return ReturnResult.error("分类名字重复！");
        }
	    if(classifyService.saveClassfy(className)){
	        return ReturnResult.ok();
        }
	    return ReturnResult.error("添加失败！");
    }
}
