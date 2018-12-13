package com.share.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Controller
@RequestMapping("/sharediInvitation")
public class SharediInvitationController {

    @GetMapping("/goIndex")
    public String goIndex(){
        return  "index";
    }

}

