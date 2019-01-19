package com.share.forum.mapper;

import com.share.pojo.SharedForum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 帖子持久层
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumMapper extends BaseMapper<SharedForum> {

    /**
     * 查询全部帖子
     * @return
     */
    List<SharedForum> findList();

}
