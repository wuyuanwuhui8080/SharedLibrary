package com.share.mapper;

import com.share.pojo.SharedFriends;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 好友数据访问接口
 * @author 博博大人
 * @time 2018/12/15 15:45
 */
public interface SharedFriendsMapper extends BaseMapper<SharedFriends> {

    /**
     * 根据传入的id查询所有好友的id
     * @param usersId
     * @return
     */
    List<String> findListByUserId(@Param("usersId") String usersId);
}
