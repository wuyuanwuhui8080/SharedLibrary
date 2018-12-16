package com.share.mapper;

import com.share.pojo.SharedEmail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
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
    List<SharedEmail> getEmaiListlByUserId(@Param("id") String id);

    /**
     * 根据用户id获取未读邮件数量
     *
     * @param id 用户id
     * @return 未读邮件数量
     */
    int getUnreadEmailCount(@Param("id") String id);

    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @return 是否成功
     */
    int updateState(@Param("idList") List<String> idList);

    /**
     * 根据邮件id获取邮件
     *
     * @param id 邮件id
     * @return 邮件
     */
    SharedEmail getEmailById(@Param("id") String id);
}
