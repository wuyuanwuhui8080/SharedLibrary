package com.share.users.mapper;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.SharedlFriendRequest;

/**
 * 好友请求dao数据层
 *
 * @author 博博大人
 * @time 2019/1/3 19:43
 */
public interface SharedlFriendRequestMapper
        extends BaseMapper<SharedlFriendRequest> {

    /**
     * 根据用户id 查询所有请求
     *
     * @param userId 传入的用户id
     * @return
     */
    List<SharedlFriendRequest> findLstByUserId(@Param("userId") String userId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /**
     * 查看所有自己请求加为好友的请求
     *
     * @param userId 传入的用户id
     * @return
     */
    List<SharedlFriendRequest> findRequestFriendByUserId(@Param("userId") String userId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);
}
