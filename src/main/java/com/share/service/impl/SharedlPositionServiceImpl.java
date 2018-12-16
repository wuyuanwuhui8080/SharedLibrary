package com.share.service.impl;

import com.share.pojo.SharedlPosition;
import com.share.mapper.SharedlPositionMapper;
import com.share.service.SharedlPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位业务实现类
 * @author cll 陈留领
 * @time 2018/12/16 16:49
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
