package com.share.controller;


import com.share.ControllerUtil.CaptchaController;
import com.share.pojo.ShareBlogs;
import com.share.pojo.SharedFans;
import com.share.pojo.SharedUsers;
import com.share.service.ShareBlogsService;
import com.share.service.SharedAttentionService;
import com.share.service.SharedFansService;
import com.share.service.SharedUsersService;
import com.share.util.ReturnResult;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 对用户页面进行数据传输
 *
 * @author 博博大人
 * @time 2018/12/13 8:47
 */
@Log4j2
@Controller
@RequestMapping("/sharedUsers")
public class SharedUsersController {

    @Resource
    private SharedUsersService usersService;

    @Resource
    private SharedFansService fansService;

    @Resource
    private SharedAttentionService attentionService;

    @Resource
    private ShareBlogsService blogsService;

    @GetMapping("/adminUser")
    public String index() {
        return "background/index";
    }

    /**
     * 重定向到主页
     *
     * @return
     */
    @GetMapping("/goIndex")
    public String goIndex() {
        return "redirect:/sharedUsers/adminUser";
    }

    /**
     * 执行登录操作
     *
     * @param userName 用户名
     * @param password 密码
     * @param captcha  验证码
     * @param session  成功后放进session
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    public ReturnResult doLogin(String userName, String password, String captcha, HttpSession session) {
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        //校验验证码
        //session中的验证码
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            return ReturnResult.error("验证码错误！");
        }

        // 把账号密码塞入UsernamePasswordToken里
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        // 允许缓存
//        usernamePasswordToken.setRememberMe(true);

        try {
            // 执行登录
            subject.login(usernamePasswordToken);

            // 查看是否认证成功 成功 true 否则false
            if (subject.isAuthenticated()) {
                SharedUsers users = usersService.getSharedUsersByUserName(userName);
                session.setAttribute("users", users);
                return ReturnResult.ok();
            } else {
                return ReturnResult.error("登录失败！");
            }
        } catch (IncorrectCredentialsException e) {
            return ReturnResult.error("账号或者密码错误！");
        } catch (UnknownAccountException e) {
            return ReturnResult.error(e.getMessage());
        }
    }

    /**
     * 跳转到登录页
     *
     * @return String
     */
    @GetMapping("/goLogin")
    public String goLogin() {
        return "background/users/login";
    }

    /**
     * 跳转到注册页
     *
     * @param users
     * @return String
     */
    @GetMapping("/goRegister")
    public String goRegister(@ModelAttribute("users") SharedUsers users) {
        return "background/users/register";
    }

    /**
     * 注册用户
     *
     * @param users 用户实体
     * @return
     */
    @PostMapping("/saveNorsalUsers")
    @ResponseBody
    public ReturnResult saveNorsalUsers(SharedUsers users) {
        if (usersService.saveNorsalSharedUsers(users)) {
            return ReturnResult.ok();
        }
        return ReturnResult.error("添加失败！");
    }

    /**
     * 校验用户名是否重复
     *
     * @param userName
     * @return
     */
    @GetMapping("/getUserName")
    @ResponseBody
    public ReturnResult getUserName(String userName) {
        if (usersService.getSharedUsersGetUserName(userName)) {
            return ReturnResult.error();
        } else {
            return ReturnResult.ok();
        }
    }

    @GetMapping("/goUserList")
    public String goUserList() {
        return "background/users/userList";
    }


    /**
     * 查看用户资料
     *
     * @return
     */
    @GetMapping("/lookProfile/{usersId}")
    public String lookProfile(@PathVariable String usersId, Model model) {
        // 查询多少个粉丝
        Integer getFensCuont = fansService.getFensCount(usersId);
        // 查询多少个关注的
        Integer getAttention = attentionService.getUsersIdAttention(usersId);
        // 查询发了多少个博客
        Integer getBlogs = blogsService.getCountForBlogsByUsersId(usersId);
        // 查询博客
        List<ShareBlogs> blogsList = blogsService.findListFriendsByUsersId(usersId);

        // 数据统一发送到页面
        model.addAttribute("getFensCuont",getFensCuont);
        model.addAttribute("blogsList",blogsList);
        model.addAttribute("getBlogs",getBlogs);
        model.addAttribute("getAttention",getAttention);
        return "background/users/profile";
    }

    /**
     * 执行用户注销操作
     * 注销成功后重定向到登录页面
     *
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/sharedUsers/goLogin";
    }


    @GetMapping("/GoIndex")
    public String GoIndex(){
        return  "background/users/timeline";
    }

}

