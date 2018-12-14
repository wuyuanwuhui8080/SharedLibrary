package com.share.mapper;

import com.share.pojo.SharedFriends;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
public interface SharedFriendsMapper extends BaseMapper<SharedFriends> {

    /**
     * 根据传入的id查询所有好友的id
     * @param userId
     * @return
     */
    List<String> findListByUserId(String userId);
}
