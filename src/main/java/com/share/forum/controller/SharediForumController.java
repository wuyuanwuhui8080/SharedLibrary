package com.share.forum.controller;


import com.share.pojo.SharedUsers;
import com.share.users.controller.SharedUsersController;
import com.share.users.service.SharedUsersService;
import com.share.util.ReturnResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-15
 */
@Controller
@RequestMapping("/sharediForum")
public class SharediForumController {

    @Resource
    private SharedUsersController sharedUsersController;

    @RequestMapping("/tologin")
    public String goLogin() {
        return "reception/user/login";
    }

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping("/goIndex")
    public String goIndex() {
        return "reception/index";
    }

    /**
     * 跳转写论坛
     *
     * @return
     */
    @RequestMapping("/goWriteForum")
    public String goWriteForum() {
        return "reception/jie/add";
    }
}

