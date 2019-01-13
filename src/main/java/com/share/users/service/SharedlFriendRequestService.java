package com.share.users.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedlFriendRequest;

/**
 * 好友请求接口类
 *
 * @author 博博大人
 * @time 2018/12/16 21:35
 */
public interface SharedlFriendRequestService
        extends IService<SharedlFriendRequest> {

    /**
     * 发送好友请求
     *
     * @param meId     发送者的id
     * @param friendId 接受者的id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    Boolean saveFriendRequst(SharedlFriendRequest friendRequest);

    /**
     * 校验是否存在该记录
     *
     * @param friendRequest
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Boolean getFriendRequstEixt(SharedlFriendRequest friendRequest);

    /**
     * 查看所有好友请求
     *
     * @param userId 传入的用户id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    PageInfo<SharedlFriendRequest> findFriendRequestByUserId(String userId, int pageIndex, int pageSize);

    /**
     * 查看所有自己请求加为好友的请求
     *
     * @param userId 传入的用户id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    PageInfo<SharedlFriendRequest> requestFindFriendByUserId(String userId, int pageIndex, int pageSize);

    /**
     * 用来修改请求状态
     *
     * @param friendRequest 传入实体
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateFriendRequestByStatus(SharedlFriendRequest friendRequest);
}
