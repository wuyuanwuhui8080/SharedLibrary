package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
import com.share.mapper.SharedReceiveMailMapper;
import com.share.service.SharedReceiveMailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 邮件收件表
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-03
 */
@Service
public class SharedReceiveMailServiceImpl extends ServiceImpl<SharedReceiveMailMapper, SharedReceiveMail> implements SharedReceiveMailService {
    @Resource
    private SharedReceiveMailMapper mailMapper;

    /**
     * 分页查询数据
     *
     * @param page    分页数据
     * @param wrapper 条件
     * @return 邮件集合
     */
    @Override
    public IPage selectSharedReceiveMailList(Page<SharedReceiveMail> page, Wrapper wrapper) {
        return mailMapper.selectSharedReceiveMailList(page,wrapper);
    }

    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @param state  -1 为没有选中
     * @return 是否成功
     */
    @Override
    public int updateState(List<String> idList, String state) {
        if (idList.size() == 0) {
            return -1;
        }
        return mailMapper.updateState(idList, state);
    }
}
