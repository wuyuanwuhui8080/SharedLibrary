package com.share.forum.mapper;

import com.share.pojo.SharedForumComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
public interface SharedForumCommentMapper extends BaseMapper<SharedForumComment> {

    /**
     * 个人页面根据userid获取最近的回帖
     *
     * @param userId        用户Id
     * @param pageIndex     分页起始页
     * @param forumpagesize 每页数量
     * @return 最近的回帖
     */
    List<SharedForumComment> findForymCommentByUserID(@Param("userId")String userId, @Param("pageIndex")Integer pageIndex, @Param("forumpagesize")Integer forumpagesize);
}
