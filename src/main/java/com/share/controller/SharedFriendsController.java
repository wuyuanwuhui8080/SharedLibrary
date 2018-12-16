package com.share.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * 好友前端控制器
 * @author 博博大人
 * @time 2018/12/16 14:07
 */
@Controller
@RequestMapping("/sharedFriends")
public class SharedFriendsController {


    @GetMapping("/goSearchFriend")
    public String goSearchFriend(){
        return  "background/users/friends";
    }

}

