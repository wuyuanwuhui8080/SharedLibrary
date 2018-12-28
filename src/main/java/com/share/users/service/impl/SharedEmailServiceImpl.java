package com.share.users.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.pojo.SharedEmail;
import com.share.users.mapper.SharedEmailMapper;
import com.share.users.service.SharedEmailService;

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

    @Override
    public List<SharedEmail> getEmail(String id) {
        return sharedEmailMapper.getEmail(id);
    }

    @Override
    public int getUnreadEmailCount(String id) {
        return sharedEmailMapper.getUnreadEmailCount(id);
    }
}
