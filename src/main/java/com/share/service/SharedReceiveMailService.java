package com.share.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 邮件收件表
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-03
 */
public interface SharedReceiveMailService extends IService<SharedReceiveMail> {
    /**
     * 分页查询数据
     *
     * @param page    分页数据
     * @param wrapper 条件
     * @return 邮件集合
     */
    IPage selectSharedReceiveMailList(Page<SharedReceiveMail> page, Wrapper wrapper) ;

    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @return 是否成功
     */
    int updateState(List<String> idList, String state);
}
