package com.share.service;

import com.share.pojo.SharedFriends;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 好友业务接口
 * @author 博博大人
 * @time 2018/12/15 15:39
 */
public interface SharedFriendsService extends IService<SharedFriends> {

    /**
     * 根据用户id查询所有好友的id
     * @param userId 用户ID
     * @return
     */
    List<String> getListUsersId(String userId);

}
