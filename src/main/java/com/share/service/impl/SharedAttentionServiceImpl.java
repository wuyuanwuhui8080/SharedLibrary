package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.pojo.SharedAttention;
import com.share.mapper.SharedAttentionMapper;
import com.share.service.SharedAttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-14
 */
@Service
public class SharedAttentionServiceImpl extends ServiceImpl<SharedAttentionMapper, SharedAttention> implements SharedAttentionService {

    /**
     * 根据传入的id查询关注的人数
     * @param userId 传入人的id
     * @return
     */
    @Override
    public Integer getUsersIdAttention(String userId) {
        QueryWrapper<SharedAttention> wrapper = new QueryWrapper<>();

        wrapper.eq("me_id",userId);
        return this.count(wrapper);
    }
}
