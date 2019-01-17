package com.share.charsocket.controller;

import com.share.charsocket.util.FriendAndMyIdKey;
import com.share.util.RedisUtil;
import com.share.util.ReturnResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;
import java.util.TreeSet;

/**
 * 聊天记录控制器
 *
 * @author 博博
 * @Title: CharController
 * @ProjectName SharedLibrary
 * @time 2019/1/12 22:17
 */
@RestController
@RequestMapping("/char")
public class CharController {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 用于初始化聊天记录的方法
     *
     * @param myId       自己的id
     * @param receiverId 朋友的id
     * @return
     */
    @GetMapping("/friendAndUsersList/{myId}/{receiverId}")
    public Set<Object> friendAndUsersList(@PathVariable String myId,
                                          @PathVariable String receiverId) {
        // 生成key
        String charKey = FriendAndMyIdKey.CharKey(myId, receiverId);
        return redisUtil.sGet(charKey);
    }

    @PostMapping("/deleteMessage/{myId}/{receiverId}")
    public ReturnResult deleteMessage(@PathVariable String myId,
                                      @PathVariable String receiverId) {
        // 生成key
        String charKey = FriendAndMyIdKey.CharKey(myId, receiverId);
        return redisUtil.del(charKey) == true ? ReturnResult.ok() : ReturnResult.error();
    }


}
