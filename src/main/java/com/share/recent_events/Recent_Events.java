package com.share.recent_events;

import com.share.blogs.mapper.ShareBlogsMapper;
import com.share.constant.EventConstant;
import com.share.pojo.ShareBlogs;
import com.share.pojo.SharedUsers;
import com.share.pojo.SharedlFriendRequest;
import com.share.users.mapper.SharedFriendsMapper;
import com.share.users.mapper.SharedUsersMapper;
import com.share.users.mapper.SharedlFriendRequestMapper;
import com.share.util.JsonUtils;
import com.share.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 最近事件工具
 *
 * @author 牛自豪
 * @date 2019-01-08-14:35
 */
@Component
public class Recent_Events {

    /**
     * redis工具
     */
    @Resource
    private RedisUtil redisUtil;

    /**
     * 用户mapper
     */
    @Resource
    private SharedUsersMapper sharedUsersMapper;

    /**
     * 好友请求mapper
     */
    @Resource
    private SharedlFriendRequestMapper friendRequestMapper;

    /**
     * 博客mapper
     */
    @Resource
    private ShareBlogsMapper blogsMapper;

    /**
     * 好友mapper
     */
    @Resource
    private SharedFriendsMapper friendsMapper;


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
            //添加好友事件
            setFriendRequest(type, EventObjectId, event);
        } else if (EventConstant.BLOG_EVENT.equals(type)) {
            //添加博客事件
            setBlog(type, EventObjectId, event);
        } else if (EventConstant.POST_EVENT.equals(type)) {
            //论坛事件
            event.setEventName("论坛动态");
            event.setType(type);
        }
    }

    /**
     * 添加好友类型事件
     *
     * @param type          事件类型
     * @param EventObjectId 对象ID
     * @param event         事件
     */
    private void setFriendRequest(String type, String EventObjectId, Event event) {
        event.setEventName("添加好友");
        event.setType(type);
        //好友请求事件
        //强转好友请求对象
        SharedlFriendRequest sharedlFriendRequest = friendRequestMapper.selectById(EventObjectId);
        //获取请求用户
        SharedUsers requestUsers = sharedUsersMapper.selectById(sharedlFriendRequest.getRequestId());
        //获取被请求用户
        SharedUsers requestedUsers = sharedUsersMapper.selectById(sharedlFriendRequest.getMeId());
        //获取详细信息,调用方法
        event.setEventDescription(getRequestFriendDetails(sharedlFriendRequest, requestedUsers, 1));
        String msg = JsonUtils.JSONString(event);
        //添加进redis,有序集合,获取此集合最大值+1
        redisUtil.sSet(requestUsers.getUserName(), msg);
        //同时添加被请求者事件
        setRequestedFriend(requestedUsers.getUserName(), requestUsers, sharedlFriendRequest);
    }

    /**
     * 添加博客事件
     *
     * @param type          事件类型
     * @param EventObjectId 对象ID
     * @param event         事件对象
     */
    private void setBlog(String type, String EventObjectId, Event event) {
        //博客事件
        event.setEventName("博客动态");
        event.setType(type);
        //获得博客对象
        ShareBlogs shareBlogs = blogsMapper.selectById(EventObjectId);
        //获取用户对象,并放入博客对象中
        shareBlogs.setUsers(sharedUsersMapper.selectById(shareBlogs.getUserId()));
        //拼接详细信息
        event.setEventDescription(getBlog(shareBlogs));
        //获取是否有好友
        List<String> listByUserId = friendsMapper.findListByUserId(shareBlogs.getUserId());
        //根据好友Id获取UserName
        List<String> userNames = sharedUsersMapper.findUserNameByUserId(listByUserId);
        String msg = JsonUtils.JSONString(event);
        redisUtil.sSet(shareBlogs.getUsers().getUserName(), msg);
        //判断是否有好友
        if (userNames.size() != 0) {
            //有好友,将好友的最近事件都塞进去
            userNames.forEach(userName -> redisUtil.sSet(userName, msg));
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
        //添加进redis,有序集合,获取此集合最大值+1
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
        StringBuilder sb = new StringBuilder();
        if (type == 1) {
            sb.append("添加用户:").append(requestedUsers.getRealName());
            sb.append("(").append(requestedUsers.getUserName()).append(")为好友,");
            //请求状态 请求状态(1.未处理 2.同意，3.拒绝 )
            sb.append("请求状态:").append(sharedlFriendRequest.getStatus() == 1 ? "未处理" : sharedlFriendRequest.getStatus() == 2 ? "已同意" : "被拒绝");
        } else if (type == 2) {
            sb.append("用户:").append(requestedUsers.getRealName());
            sb.append("(").append(requestedUsers.getUserName()).append(")希望添加您为好友,");
            //请求状态 请求状态(1.未处理 2.同意，3.拒绝 )
            sb.append("请求状态:").append(sharedlFriendRequest.getStatus() == 1 ? "未处理" : sharedlFriendRequest.getStatus() == 2 ? "已同意" : "被拒绝");
        }
        return sb.toString();
    }

    /**
     * 获取博客详细描述
     *
     * @param shareBlogs 博客对象
     * @return 博客详细描述
     */
    private String getBlog(ShareBlogs shareBlogs) {
        StringBuilder sb = new StringBuilder();
        //拼接信息
        sb.append(shareBlogs.getUsers().getRealName()).append("(").append(shareBlogs.getUsers().getUserName()).append("):发表了新博客:");
        sb.append(shareBlogs.getContent());
        return sb.toString();
    }
}
