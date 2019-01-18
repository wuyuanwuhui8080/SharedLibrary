package com.share.forum.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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

