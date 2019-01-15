package com.share.forum.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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
    @GetMapping("/goIndex")
    public String goIndex() {

        return "index";
    }

    /**
     * 跳转写论坛
     *
     * @return
     */
    @GetMapping("/goWriteInvitation")
    public String goWriteInvitation() {
        return "reception/write_forum";
    }
}

