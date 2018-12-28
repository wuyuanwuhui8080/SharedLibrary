package com.share.users.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.pojo.SharedState;
import com.share.users.mapper.SharedStateMapper;
import com.share.users.service.SharedStateService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Service
public class SharedStateServiceImpl extends ServiceImpl<SharedStateMapper, SharedState> implements SharedStateService {

}
