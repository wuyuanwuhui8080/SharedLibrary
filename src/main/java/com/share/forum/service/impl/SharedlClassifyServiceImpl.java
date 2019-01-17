package com.share.forum.service.impl;

import com.share.pojo.SharedlClassify;
import com.share.forum.mapper.SharedlClassifyMapper;
import com.share.forum.service.SharedlClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-15
 */
@Service
public class SharedlClassifyServiceImpl extends ServiceImpl<SharedlClassifyMapper, SharedlClassify> implements SharedlClassifyService {

    @Override
    public List<SharedlClassify> findSharedlClassifyList() {
        return super.list();
    }
}
