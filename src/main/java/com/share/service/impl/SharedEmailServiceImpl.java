package com.share.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.mapper.SharedEmailMapper;
import com.share.pojo.SharedEmail;
import com.share.service.SharedEmailService;
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
     * 根据用户id获取该用户邮件
     *
     * @param id 用户id
     * @return 邮件集合
     */
    @Override
    public List<SharedEmail> getEmaiListlByUserId(String id) {
        return sharedEmailMapper.getEmaiListlByUserId(id);
    }

    /**
     * 根据邮件id获取邮件
     *
     * @param id 邮件id
     * @return 邮件
     */
    @Override
    public SharedEmail getEmailById(String id) {
        return sharedEmailMapper.getEmailById(id);
    }

    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @return 是否成功
     */
    @Override
    public int updateState(List<String> idList,String state) {
        return sharedEmailMapper.updateState(idList,state);
    }

    /**
     * 根据用户id获取有状态的邮件集合
     *
     * @param id    用户id
     * @param state 状态
     * @return 有状态的邮件集合
     */
    @Override
    public List<SharedEmail> getStateEmail(String id, String state) {
        return sharedEmailMapper.getStateEmail(id,state);
    }
}
