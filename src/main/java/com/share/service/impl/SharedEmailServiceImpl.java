package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.pojo.SharedEmail;
import com.share.mapper.SharedEmailMapper;
import com.share.pojo.SharedUsers;
import com.share.service.SharedEmailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public List<SharedEmail> getEmaiListlByUserId(String id) {
        return sharedEmailMapper.getEmaiListlByUserId(id);
    }

    @Override
    public SharedEmail getEmailById(String id) {

        return sharedEmailMapper.getEmailById(id);
    }

    @Override
    public int getUnreadEmailCount(String id) {
        return sharedEmailMapper.getUnreadEmailCount(id);
    }


    @Override
    public int updateState(List<String> idList) {
        return sharedEmailMapper.updateState(idList);
    }
}
