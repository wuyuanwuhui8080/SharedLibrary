package com.share.users.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
import com.share.users.mapper.SharedReceiveMailMapper;
import com.share.users.service.SharedReceiveMailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.util.StringUtils;
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
public class SharedReceiveMailServiceImpl
        extends ServiceImpl<SharedReceiveMailMapper, SharedReceiveMail>
        implements SharedReceiveMailService {
    @Resource
    private SharedReceiveMailMapper mailMapper;

    /**
     * 分页查询数据
     *
     * @param userId    用户id
     * @param status    状态
     * @param pageIndex 起始页
     * @param pageSize  每页页数
     * @return
     */
    @Override
    public PageInfo<SharedReceiveMail> selectSharedReceiveMailList(
            String userId, String status, Integer pageIndex, Integer pageSize) {
        LambdaQueryWrapper<SharedReceiveMail> wrapper = new LambdaQueryWrapper<>();
        // 判断状态是否为空
        if (StringUtils.isNotNull(userId)) {
            wrapper.eq(SharedReceiveMail::getReceiveId, userId);
        }
        // 判断发件人id是否为空
        if (StringUtils.isNotNull(status)) {
            wrapper.eq(SharedReceiveMail::getState, status);
        }
        wrapper.orderByDesc(SharedReceiveMail::getCreationDate);
        // 拦截条件
        PageHelper.startPage(pageIndex, pageSize);
        PageInfo<SharedReceiveMail> pageInfo = new PageInfo<>(super.list(wrapper));
        return pageInfo;
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
