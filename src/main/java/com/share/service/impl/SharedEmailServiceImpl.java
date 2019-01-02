package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.ControllerUtil.EmailPage;
import com.share.mapper.SharedEmailMapper;
import com.share.pojo.SharedEmail;
import com.share.service.SharedEmailService;
import javafx.scene.control.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-13
 */
@Service
public class SharedEmailServiceImpl extends ServiceImpl<SharedEmailMapper, SharedEmail> implements SharedEmailService {
    @Resource
    private SharedEmailMapper sharedEmailMapper;

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
        return sharedEmailMapper.updateState(idList, state);
    }

    /**
     * 分页查询数据
     *
     * @param page    分页数据
     * @param wrapper 条件
     * @return 邮件集合
     */
    @Override
    public IPage selectSharedEmailList(Page<SharedEmail> page, Wrapper wrapper) {
        return sharedEmailMapper.selectSharedEmailList(page, wrapper);
    }
}
