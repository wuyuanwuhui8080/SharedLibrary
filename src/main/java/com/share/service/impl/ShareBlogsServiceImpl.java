package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.mapper.SharedFansMapper;
import com.share.mapper.SharedFriendsMapper;
import com.share.pojo.ShareBlogs;
import com.share.mapper.ShareBlogsMapper;
import com.share.service.ShareBlogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客业务实现层
 *
 * @author 博博大人
 * @time 2018/12/14 16:12
 */
@Service
public class ShareBlogsServiceImpl extends ServiceImpl<ShareBlogsMapper, ShareBlogs> implements ShareBlogsService {

    @Resource
    private ShareBlogsMapper blogsMapper;

    @Resource
    private SharedFriendsMapper friendsMapper;

    /**
     * 根据传入的usersid查询博客表的数据总数
     *
     * @param userId 传入的用户id
     * @return Integer
     */
    @Override
    public Integer getCountForBlogsByUsersId(String userId) {
        QueryWrapper<ShareBlogs> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return super.count(wrapper);
    }

    /**
     * 根据传入的用户id查询博客集合
     *
     * @param userId 传入用户的id
     * @return List<ShareBlogs>
     */
    @Override
    public List<ShareBlogs> getFindListForBlogsByUsersId(String userId) {
        QueryWrapper<ShareBlogs> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return super.list(wrapper);
    }

    /**
     * 查询自己和自己的所有好友的博客
     *
     * @param userId 自己的id
     * @return
     */
    @Override
    public List<ShareBlogs> findListFriendsByUsersId(String userId) {
        List<String> findList = friendsMapper.findListByUserId(userId);
        findList.add(userId);
        return blogsMapper.findListByUserId(findList);
    }

}
