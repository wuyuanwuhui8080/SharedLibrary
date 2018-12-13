package com.share.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;


/**
 *
 * 对用户页面进行数据传输
 *
 * @author 博博大人
 * @time 2018/12/13 8:47
 */
@Controller
@RequestMapping("/sharedUsers")
public class SharedUsersController {

    @GetMapping("/")
    public String index(){
        return  "background/index";
    }

    @GetMapping("/goLogin")
    public String goLogin(){
        return  "background/users/login";
    }

}

