package com.share.mapper;

import com.share.pojo.SharedEmail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;import org.apache.ibatis.annotations.Param;

import java.util.List;

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
