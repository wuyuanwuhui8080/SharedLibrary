package com.share.service.impl;

import com.share.mapper.SharedFansMapper;
import com.share.pojo.SharedFans;
import com.share.pojo.SharedFriends;
import com.share.mapper.SharedFriendsMapper;
import com.share.service.SharedFriendsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Service
public class SharedFriendsServiceImpl extends ServiceImpl<SharedFriendsMapper, SharedFriends> implements SharedFriendsService {

    @Resource
    private SharedFriendsMapper friendsMapper;

    @Override
    public List<String> getListUsersId(String userId) {
        return friendsMapper.findListByUserId(userId);
    }
}
