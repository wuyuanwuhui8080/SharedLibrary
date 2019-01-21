package com.share.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.share.pojo.SharedFans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
public interface SharedFansMapper extends BaseMapper<SharedFans> {
    /**
     * 查找我关注的用户数量
     *
     * @param fenId 当前用户id
     * @return 关注的用户
     */
    Integer findMeattentionCount(@Param("fenId") String fenId);

    /**
     * 查看我的粉丝数量
     *
     * @param userId 当前用户id
     * @return 粉丝集合
     */
    Integer findMeFenListCount(@Param("userId") String userId);

    /**
     * 查找我关注的用户
     *
     * @param fenId 当前用户id
     * @return 关注的用户
     */
    List<SharedFans> findMeattention(@Param("fenId") String fenId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查看我的粉丝
     *
     * @param userId 当前用户id
     * @return 粉丝集合
     */
    List<SharedFans> findMeFenList(@Param("userId") String userId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);


}
