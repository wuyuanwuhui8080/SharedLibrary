package com.share.service;

import com.share.pojo.SharedUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Users业务接口
 * @author 博博大人
 * @time 2018/12/13 14:21
 */
public interface SharedUsersService extends IService<SharedUsers> {

    /**
     * 添加用户
     * @param users 传入的用户实体
     * @return Boolean
     */
    public Boolean saveNorsalSharedUsers(SharedUsers users);

    /**
     * 判断用户名是否重复
     * @param userName 传入的用户名
     * @return Boolean
     */
    public Boolean getSharedUsersGetUserName(String userName);

    /**
     *  根据用户名 查询是否存在
     * @param userName 传入的用户名
     * @return SharedUsers
     */
    public SharedUsers getSharedUsersByUserName(String userName);

}
