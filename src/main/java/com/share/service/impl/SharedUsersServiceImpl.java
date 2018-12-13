package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.constant.PositionConstant;
import com.share.constant.StateConstant;
import com.share.pojo.SharedUsers;
import com.share.mapper.SharedUsersMapper;
import com.share.service.SharedUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.util.ShiroMd5;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户实现类，负责用户的一切操作
 * @author 博博大人
 * @time 2018/12/13 14:27
 */
@Service
public class SharedUsersServiceImpl extends ServiceImpl<SharedUsersMapper, SharedUsers> implements SharedUsersService {

    /**
     * 执行添加普通用户 用户操作，使用shiro md5进行 1024次加密
     * @param users 传入的用户实体
     * @return
     */
    @Override
    public Boolean saveNorsalSharedUsers(SharedUsers users) {
        String pwd = ShiroMd5.hashMd5(users.getUserName(),users.getPassword());
        users.setCreationDate(new Date());
        users.setPositionId(PositionConstant.NORMAL_USER);
        users.setStateId(StateConstant.NORMAL_USER);
        users.setPassword(pwd);
        users.setHeadImg("bd978735b33f496792673949e70fb2eb!400x400.jpeg");
        return this.save(users);
    }

    /**
     * 根据传入的用户名 校验是否存在 y ：true n ： false
     * @param userName 传入的用户名
     * @return Boolean
     */
    @Override
    public Boolean getSharedUsersGetUserName(String userName) {
        QueryWrapper<SharedUsers> wrapper = new QueryWrapper<>();
        wrapper.eq("userName",userName);
        if(super.count(wrapper) > 0){
            return  true;
        }else {
            return false;
        }
    }

    /**
     *  根据传入的用户名 校验是否存在 存在返回SharedUsers 不存在返回null
     * @param userName 传入的用户名
     * @return SharedUsers
     */
    @Override
    public SharedUsers getSharedUsersByUserName(String userName) {
        QueryWrapper<SharedUsers> wrapper = new QueryWrapper<>();
        wrapper.eq("userName",userName);
        return super.getOne(wrapper);
    }
}
