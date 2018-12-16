package com.share.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedFriends;

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
