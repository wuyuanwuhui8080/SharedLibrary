package com.share.forum.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageInfo;
import com.share.ControllerUtil.CaptchaController;
import com.share.constant.PageConstant;
import com.share.forum.service.SharedForumCommentService;
import com.share.forum.service.SharedForumService;
import com.share.forum.service.SharedlClassifyService;
import com.share.forum.vo.ForumAndComment;
import com.share.pojo.SharedForum;
import com.share.pojo.SharedForumComment;
import com.share.pojo.SharedUsers;
import com.share.pojo.SharedlClassify;
import com.share.recent_events.Event;
import com.share.users.service.SharedUsersService;
import com.share.util.*;
import com.share.vo.SharedUsersVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private SharedUsersService usersService;
    @Resource
    private RedisUtil redisUtil;


    /**
     * 初始化主页
     *
     * @param pageIndex 起始页
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
     * @param id    帖子id
     * @param model
     * @return
     */
    @RequestMapping("/goForumDetailed/{id}")
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
     * @param sharedForum 传入的实体
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
                         Model model) throws Exception {
        SharedUsers user = usersService.getUserById(userId);
        SharedUsersVO sharedUsersVO = new SharedUsersVO();
        CopyUtils.Copy(user, sharedUsersVO);
        List<SharedForum> foryms = forumService.findForymByUserId(userId, pageIndex);
        List<SharedForumComment> forumComments = forumCommentService.findForymCommentByUserID(userId, pageIndex);
        model.addAttribute("foryms", foryms);
        model.addAttribute("forumComments", forumComments);
        return "reception/user/home";
    }

    /**
     * 转我的帖子页面
     *
     * @return
     */
    @RequestMapping("/goUserIndex")
    public String goUserIndex(@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex
            , Model model) {
        SharedUsers user = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        List<SharedForum> foryms = forumService.findForymByUserId(user.getId(), pageIndex);
        model.addAttribute("foryms", foryms);
        return "reception/user/index";
    }

    /**
     * 转我的消息页面
     *
     * @return
     */
    @RequestMapping("/goMessage")
    public String goMessage(Model model) {
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        boolean key = redisUtil.hasKey(users.getUserName() + "Reply");
        List<Event> list = null;
        if(key){
            String event = redisUtil.sGet(users.getUserName() + "Reply").toString();
            list = (List<Event>) JsonUtils.JSONList(event, Event.class);
        }
        model.addAttribute("Reply", list);
        return "reception/user/message";
    }

    /**
     * 删除说有消息
     *
     * @return
     */
    @RequestMapping("/delAllMessage")
    public String delAllMessage() {
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        redisUtil.del(users.getUserName() + "Reply");
        return "redirect:/sharedForum/goMessage";
    }

    /**
     * 删除单个消息
     *
     * @param eventId
     * @return
     */
    @RequestMapping("/delMessage/{eventId}")
    public String delMessage(@PathVariable String eventId) {
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        String mag = redisUtil.sGet(users.getUserName() + "Reply").toString();
        List<Event> list = (List<Event>) JsonUtils.JSONList(mag, Event.class);
        for (Event e : list) {
            if (e.getEventid().equals(eventId)) {
                String s = JsonUtils.JSONString(e);
                redisUtil.setRemove(users.getUserName() + "Reply", s);
            }
        }
        return "redirect:/sharedForum/goMessage";
    }

}
