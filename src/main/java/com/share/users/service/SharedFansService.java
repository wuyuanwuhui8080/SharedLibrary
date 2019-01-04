package com.share.users.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedFans;

/**
 * 粉丝的业务管理
 * @author 博博大人
 * @time 2018/12/14 15:12
 */
public interface SharedFansService extends IService<SharedFans> {

    /**
     * 获取某个用户底下的所有粉丝数量
     * @param usersId 用户id
     * @return
     */
    Integer getFensCount(String usersId);

}
