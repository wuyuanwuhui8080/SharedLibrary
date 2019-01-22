package com.share.forum.controller;

import com.github.pagehelper.PageInfo;
import com.share.ControllerUtil.CaptchaController;
import com.share.constant.PageConstant;
import com.share.forum.service.SharedForumCommentService;
import com.share.forum.service.SharedForumService;
import com.share.forum.service.SharedlClassifyService;
import com.share.forum.vo.ForumAndComment;
import com.share.pojo.SharedForum;
import com.share.pojo.SharedForumComment;
import com.share.pojo.SharedlClassify;
import com.share.util.ReturnResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 帖子前端控制器
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Controller
@RequestMapping("/sharedForum")
public class SharedForumController {

	@Resource
	private SharedlClassifyService classifyService;

    @Resource
    private SharedForumService forumService;
    @Resource
    private SharedForumCommentService forumCommentService;


	/**
	 * 初始化主页
	 *
	 * @param pageIndex
	 *            起始页
	 * @param model
	 * @return
	 */
	@GetMapping("/goIndex")
	public String goIndex(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			Model model) {
		// 执行查询
		PageInfo<SharedForum> page = forumService.findList(pageIndex,
				PageConstant.PAGESIZE);
		// 把结果传递到页面
		model.addAttribute("page", page);
		return "reception/index";
	}

	/**
	 * 看帖子详细
	 * 
	 * @param id
	 *            帖子id
	 * @param model
	 * @return
	 */
	@GetMapping("/goForumDetailed/{id}")
	public String goForumDetailed(@PathVariable String id, Model model) {
		// 执行查询
		ForumAndComment forumAndComment = forumService.findListByForumId(id);
		// 如果没有回复，就直接弄空，不然前台处问题
		if (forumAndComment.getCommentBOList().get(0).getCommentId() == null) {
			forumAndComment.setCommentBOList(null);
		}
		model.addAttribute("forumAndComment", forumAndComment);
		return "reception/jie/detail";
	}

	/**
	 * 跳转到编辑帖子的页面
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/goWriteForum")
	public String goWriteForum(Model model) {
		// 查询分类列表
		List<SharedlClassify> sharedlClassifyList = classifyService
				.findSharedlClassifyList();
		model.addAttribute("list", sharedlClassifyList);
		return "reception/jie/add";
	}

	/**
	 * 发帖操作
	 *
	 * @param sharedForum
	 *            传入的实体
	 * @return
	 */
	@PostMapping("/saveForum")
	@ResponseBody
	public ReturnResult saveForum(SharedForum sharedForum, String captcha) {
		Session session = SecurityUtils.getSubject().getSession();
		// session中的验证码
		String sessionCaptcha = (String) session
				.getAttribute(CaptchaController.KEY_CAPTCHA);
		if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
			return ReturnResult.error("验证码错误！");
		}
		if (forumService.saveForum(sharedForum)) {
			return ReturnResult.ok(sharedForum.getId());
		}
		return ReturnResult.error("发布失败！");
	}

    /**
     * 前台页面跳转个人主页
     *
     * @return
     */
    @RequestMapping("/gohome/{userId}")
    public String gohome(@PathVariable String userId,
                         @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
                         Model model) {
        List<SharedForum> foryms = forumService.findForymByUserId(userId, pageIndex);
        List<SharedForumComment> forumComments = forumCommentService.findForymCommentByUserID(userId, pageIndex);
        model.addAttribute("foryms", foryms);
        model.addAttribute("forumComments", forumComments);
        return "reception/user/home";
    }

}
