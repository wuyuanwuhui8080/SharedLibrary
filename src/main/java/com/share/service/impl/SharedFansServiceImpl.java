package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.EmptyWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.pojo.SharedFans;
import com.share.mapper.SharedFansMapper;
import com.share.service.SharedFansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 粉丝业务的实现类
 * @author 博博大人
 * @time 2018/12/14 15:15
 */
@Service
public class SharedFansServiceImpl extends ServiceImpl<SharedFansMapper, SharedFans> implements SharedFansService {

    /**
     * 获取粉丝数量
     * @param usersId 用户id
     * @return
     */
    @Override
    public Integer getFensCount(String usersId) {
        QueryWrapper<SharedFans> wrapper = new QueryWrapper<>();
        wrapper.eq("me_id",usersId);
        return super.count(wrapper);
    }
}
