package com.share.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.SharedEmail;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bean
 * @since 2018-12-13
 */
public interface SharedEmailMapper extends BaseMapper<SharedEmail> {
    /**
     * 获取邮箱
     *
     * @param id
     * @return
     */
    List<SharedEmail> getEmail(@Param("id")String id);

    int getUnreadEmailCount(@Param("id")String id);
}
