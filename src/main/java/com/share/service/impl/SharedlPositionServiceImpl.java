package com.share.service.impl;

import com.share.pojo.SharedlPosition;
import com.share.mapper.SharedlPositionMapper;
import com.share.service.SharedlPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Service
public class SharedlPositionServiceImpl extends ServiceImpl<SharedlPositionMapper, SharedlPosition> implements SharedlPositionService {

    /**
     * 查询所有职位
     * @return
     */
    @Override
    public List<SharedlPosition> findList() {
        return super.list();
    }
}
