package com.share.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.ControllerUtil.EmailPage;
import com.share.pojo.SharedEmail;
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
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @param state  改变的状态
     * @return 是否成功
     */
    int updateState(@Param("idList") List<String> idList, @Param("state") String state);

    /**
     * 分页查询数据
     *
     * @param page    分页数据
     * @param wrapper 条件
     * @return 邮件集合
     */
    IPage selectSharedEmailList(Page page, @Param("ew") Wrapper wrapper);
}
