package com.share.Recent_Events;

import com.share.constant.EventConstant;
import com.share.pojo.SharedUsers;
import com.share.pojo.SharedlFriendRequest;
import com.share.users.mapper.SharedUsersMapper;
import com.share.users.service.SharedlFriendRequestService;
import com.share.util.JsonUtils;
import com.share.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 最近事件工具
 *
 * @author 牛自豪
 * @date 2019-01-08-14:35
 */
@Service
public class Recent_Events {

    @Resource
    private SharedUsersMapper sharedUsersMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SharedlFriendRequestService friendRequest;

    /**
     * 添加事件
     *
     * @param type          添加的事件类型
     * @param EventObjectId 事件对象Id
     */
    public void setEvent(String type, String EventObjectId) {
        Event event = new Event();
        if (EventConstant.FRIEND_EVENT.equals(type)) {
            //好友请求事件
            //强转好友请求对象
            SharedlFriendRequest sharedlFriendRequest = friendRequest.getById(EventObjectId);
            //获取请求用户
            SharedUsers requestUsers = sharedUsersMapper.selectById(sharedlFriendRequest.getMeId());
            //获取被请求用户
            SharedUsers requestedUsers = sharedUsersMapper.selectById(sharedlFriendRequest.getRequestId());

            event.setEventName("添加好友");
            event.setEventTime(new Date());
            //获取详细信息,调用方法
            event.setEventDescription(getRequestFriendDetails(sharedlFriendRequest, requestedUsers, 1));
            event.setType(type);
            //添加进redis
            redisUtil.sSet(requestUsers.getUserName(), JsonUtils.JSONString(event));
            //同时添加被请求者事件
            setRequestedFriend(requestedUsers.getUserName(), requestUsers, sharedlFriendRequest);

        } else if (EventConstant.BLOG_EVENT.equals(type)) {
            //博客事件

        } else if (EventConstant.POST_EVENT.equals(type)) {
            //帖子事件
        }

    }

    /**
     * 添加好友请求,添加被请求者缓存
     *
     * @param RequestedUserName    被请求者的UserName
     * @param requestUsers         请求者对象
     * @param sharedlFriendRequest 好友请求事件
     */
    private void setRequestedFriend(String RequestedUserName, SharedUsers requestUsers, SharedlFriendRequest sharedlFriendRequest) {
        Event event = new Event();
        event.setEventName("好友添加请求");
        event.setEventTime(new Date());
        //获取详细信息,调用方法
        event.setEventDescription(getRequestFriendDetails(sharedlFriendRequest, requestUsers, 2));
        event.setType(EventConstant.FRIEND_EVENT);
        //添加进redis
        redisUtil.sSet(RequestedUserName, JsonUtils.JSONString(event));
    }

    /**
     * 获取事件
     *
     * @param userName Redis的Key是用户UserName
     * @return json字符串
     */
    public String getEvent(String userName) {
        String string = JsonUtils.JSONString(redisUtil.sGet(userName));
        return string;
    }

    /**
     * 获取请求好友事件详情
     *
     * @param sharedlFriendRequest 好友请求对象
     * @param requestedUsers       被请求用户
     * @param type                 类型(1:请求者进去,2:被请求者进入)
     * @return 事件详细
     */
    private String getRequestFriendDetails(SharedlFriendRequest sharedlFriendRequest, SharedUsers requestedUsers, int type) {
        StringBuffer sb = new StringBuffer();
        if (type == 1) {
            sb.append("添加用户:" + requestedUsers.getRealName());
            sb.append("(" + requestedUsers.getUserName() + ")为好友,");
            //请求状态 请求状态(1.未处理 2.同意，3.拒绝 )
            sb.append("请求状态:" + (sharedlFriendRequest.getStatus() == 1 ? "未处理" : sharedlFriendRequest.getStatus() == 2 ? "同意" : "拒绝"));
        } else {
            sb.append("用户:" + requestedUsers.getRealName());
            sb.append("(" + requestedUsers.getUserName() + ")希望添加您为好友,");
            //请求状态 请求状态(1.未处理 2.同意，3.拒绝 )
            sb.append("请求状态:" + (sharedlFriendRequest.getStatus() == 1 ? "未处理" : sharedlFriendRequest.getStatus() == 2 ? "同意" : "拒绝"));
        }
        return sb.toString();
    }


}