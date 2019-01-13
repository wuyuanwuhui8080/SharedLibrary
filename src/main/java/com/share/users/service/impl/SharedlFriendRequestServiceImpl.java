package com.share.users.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.pojo.SharedlFriendRequest;
import com.share.users.mapper.SharedFriendsMapper;
import com.share.users.mapper.SharedlFriendRequestMapper;
import com.share.users.service.SharedlFriendRequestService;

/**
 * 好友请求实现类
 *
 * @author 博博大人
 * @time 2018/12/16 21:38
 */
@Service
public class SharedlFriendRequestServiceImpl
        extends ServiceImpl<SharedlFriendRequestMapper, SharedlFriendRequest>
        implements SharedlFriendRequestService {

    @Resource
    private SharedlFriendRequestMapper friendRequest;

    @Resource
    private SharedFriendsMapper friendsMapper;

    /**
     * 添加好友请求
     *
     * @param friendRequest
     * @return
     */
    @Override
    public Boolean saveFriendRequst(SharedlFriendRequest friendRequest) {
        friendRequest.setCreationDate(new Date());
        return super.save(friendRequest);
    }

    /**
     * 是否存在一样的请求
     *
     * @param friendRequest
     * @return
     */
    @Override
    public Boolean getFriendRequstEixt(SharedlFriendRequest friendRequest) {
        QueryWrapper<SharedlFriendRequest> wrapper = new QueryWrapper<>();
        wrapper.eq("me_id", friendRequest.getMeId());
        wrapper.eq("request_id", friendRequest.getRequestId());
        if (super.count(wrapper) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据传入的用户ID查询所有请求
     *
     * @param userId 传入的用户id
     * @return
     */
    @Override
    public PageInfo<SharedlFriendRequest> findFriendRequestByUserId(String userId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<SharedlFriendRequest> lstByUserId = friendRequest.findLstByUserId(userId, pageIndex, pageSize);
        PageInfo pageInfo = new PageInfo(lstByUserId);
        return pageInfo;
    }

    /**
     * 查看所有自己请求加为好友的请求
     *
     * @param userId 传入的用户id
     * @return
     */
    @Override
    public PageInfo<SharedlFriendRequest> requestFindFriendByUserId(String userId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<SharedlFriendRequest> lstByUserId = friendRequest.findLstByUserId(userId, pageIndex, pageSize);
        PageInfo pageInfo = new PageInfo(lstByUserId);
        return pageInfo;
    }

    /**
     * 修改请求状态的方法
     *
     * @param friendRequest 传入实体
     * @return
     */
    @Override
    public boolean updateFriendRequestByStatus(
            SharedlFriendRequest friendRequest) {
        friendRequest.setMeId(null);
        friendRequest.setRequestId(null);
        return super.updateById(friendRequest);
    }
}
