package com.share.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.pojo.SharedReceiveMail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 邮件收件表
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-03
 */
public interface SharedReceiveMailMapper extends BaseMapper<SharedReceiveMail> {
    /**
     * 分页查询数据
     *
     * @param page    分页数据
     * @param wrapper 条件
     * @return 邮件集合
     */
    IPage selectSharedReceiveMailList(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @param state  改变的状态
     * @return 是否成功
     */
    int updateState(@Param("idList") List<String> idList, @Param("state") String state);
}
