package com.share.mapper;

import com.share.pojo.ShareBlogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客数据接口
 *
 * @author 博博大人
 * @time 2018/12/15 15:46
 */
public interface ShareBlogsMapper extends BaseMapper<ShareBlogs> {

    /**
     * 根据查询的list集合查询博客
     * @param userId
     * @return
     */
    List<ShareBlogs> findListByUserId(List<String> userId);
}
