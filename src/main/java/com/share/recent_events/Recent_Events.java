package com.share.recent_events;

import com.share.constant.EventConstant;
import com.share.pojo.SharedUsers;
import com.share.pojo.SharedlFriendRequest;
import com.share.users.mapper.SharedUsersMapper;
import com.share.users.service.SharedlFriendRequestService;
import com.share.util.JsonUtils;
import com.share.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 最近事件工具
 *
 * @author 牛自豪
 * @date 2019-01-08-14:35
 */
@Component
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
        //补全对象字段
        event.setEventid(EventObjectId);
        event.setEventTime(new Date());
        if (EventConstant.FRIEND_EVENT.equals(type)) {
            //好友请求事件
            //强转好友请求对象
            SharedlFriendRequest sharedlFriendRequest = friendRequest.getById(EventObjectId);
            //获取请求用户
            SharedUsers requestUsers = sharedUsersMapper.selectById(sharedlFriendRequest.getRequestId());
            //获取被请求用户
            SharedUsers requestedUsers = sharedUsersMapper.selectById(sharedlFriendRequest.getMeId());
            event.setEventName("添加好友");
            //获取详细信息,调用方法
            event.setEventDescription(getRequestFriendDetails(sharedlFriendRequest, requestedUsers, 1));
            event.setType(type);
            String msg = JsonUtils.JSONString(event);
            //添加进redis
            redisUtil.sSet(requestUsers.getUserName(), msg);
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
        String mag = JsonUtils.JSONString(event);
        //添加进redis
        redisUtil.sSet(RequestedUserName, mag);
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
            sb.append("请求状态:" + (sharedlFriendRequest.getStatus() == 1 ? "未处理" : sharedlFriendRequest.getStatus() == 2 ? "已同意" : "被拒绝"));
        } else if (type == 2) {
            sb.append("用户:" + requestedUsers.getRealName());
            sb.append("(" + requestedUsers.getUserName() + ")希望添加您为好友,");
            //请求状态 请求状态(1.未处理 2.同意，3.拒绝 )
            sb.append("请求状态:" + (sharedlFriendRequest.getStatus() == 1 ? "未处理" : sharedlFriendRequest.getStatus() == 2 ? "已同意" : "被拒绝"));
        }
        return sb.toString();
    }

}
