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
 * @since 2018-12-13
 */
@Controller
@RequestMapping("/sharedEmail")
public class SharedEmailController {

    @GetMapping("/emailIndex")
    public String emailIndex(){
        return  "background/email/email_List";
    }

    @GetMapping("/emailAdd")
    public String emailAdd(){
        return  "background/email/email_detail";
    }

}

