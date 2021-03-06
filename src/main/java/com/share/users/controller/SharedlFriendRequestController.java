package com.share.users.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;
import com.share.recent_events.Recent_Events;
import com.share.constant.EventConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.share.enums.FriendStatusEnums;
import com.share.pojo.SharedFriends;
import com.share.pojo.SharedlFriendRequest;
import com.share.users.service.SharedFriendsService;
import com.share.users.service.SharedlFriendRequestService;
import com.share.util.ReturnResult;

/**
 * 好友请求前端控制器
 *
 * @author 博博大人
 * @time 2018/12/16 21:45
 */
@Controller
@RequestMapping("/sharedlFriendRequest")
public class SharedlFriendRequestController {

    @Resource
    private SharedlFriendRequestService friendRequestService;

    @Resource
    private SharedFriendsService friendsService;

    @Resource
    private Recent_Events recent_events;

    /**
     * 处理好友请求
     *
     * @param meId      传入的自己的id
     * @param requestId 好友的id
     * @return
     */
    @GetMapping("/toFriendsRTS")
    @ResponseBody
    public ReturnResult toFriendsRTS(String meId, String requestId) {
        SharedlFriendRequest friendRequest = new SharedlFriendRequest();
        friendRequest.setMeId(requestId);
        friendRequest.setRequestId(meId);
        // 校验是否发送重复请求
        if (friendRequestService.getFriendRequstEixt(friendRequest)) {
            return ReturnResult.error("您已经发送过请求了,不能在发送请求了!");
        }
        // 没有发送过好友请求，进行好友请求发送
        if (friendRequestService.saveFriendRequst(friendRequest)) {
            //添加好友请求最近事件
            recent_events.setEvent(EventConstant.FRIEND_EVENT, friendRequest.getId());
            return ReturnResult.ok();
        } else {
            return ReturnResult.error("发送好友请求失败!");
        }
    }

    /**
     * 跳转到好友请求请求页面
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/goFirendRequest/{userId}/{pageIndex}")
    public String goFirendRequest(@PathVariable String userId,
                                  @PathVariable Integer pageIndex, Model model) {
        // 查询所有好友请求
        PageInfo<SharedlFriendRequest> friendRequestByUserId = friendRequestService
                .findFriendRequestByUserId(userId, pageIndex, 6);
        model.addAttribute("page", friendRequestByUserId);
        return "background/users/friend_request";
    }

    /**
     * 跳转到加为好友请求页面
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/goRequestFirend/{userId}/{pageIndex}")
    public String goRequestFirend(@PathVariable String userId,
                                  @PathVariable Integer pageIndex, Model model) {
        // 查询自己请求加为好友的请求
        PageInfo<SharedlFriendRequest> pageInfo = friendRequestService
                .findRequestFriendByUserId(userId, pageIndex, 6);
        model.addAttribute("page", pageInfo);
        return "background/users/request_friend";
    }

    /**
     * 处理请求状态 （1. 未处理 2.同意 3.拒绝）
     *
     * @param sharedlFriendRequest 传入实体
     * @return
     */
    @PostMapping("/updateFriendRequest")
    @ResponseBody
    public ReturnResult updateFriendRequest(
            SharedlFriendRequest sharedlFriendRequest) {
        // 判断是否是请求同意的
        if (sharedlFriendRequest
                .getStatus() == FriendStatusEnums.HAS_AGREED_TO_2.getStatus()) {
            // 拼装数据
            SharedFriends friends = new SharedFriends();
            friends.setFriendsId(sharedlFriendRequest.getRequestId());
            friends.setMeId(sharedlFriendRequest.getMeId());
            friends.setCreationDate(new Date());
            SharedFriends friends1 = new SharedFriends();
            friends1.setFriendsId(sharedlFriendRequest.getMeId());
            friends1.setMeId(sharedlFriendRequest.getRequestId());
            friends1.setCreationDate(new Date());
            // 执行批量添加
            if (friendsService.saveFirends(friends, friends1)) {

            } else {
                return ReturnResult.error();
            }
        }
        // 修改请求字段
        if (friendRequestService
                .updateFriendRequestByStatus(sharedlFriendRequest)) {
            //调用添加事件方法
            recent_events.setEvent(EventConstant.FRIEND_EVENT, sharedlFriendRequest.getId());
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }
}
