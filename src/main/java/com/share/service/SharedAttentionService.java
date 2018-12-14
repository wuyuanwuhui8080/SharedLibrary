package com.share.service;

import com.share.pojo.SharedAttention;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bean
 * @since 2018-12-14
 */
public interface SharedAttentionService extends IService<SharedAttention> {

    /**
     * 获取传入用的关注人的总记录数
     * @param userId 传入人的id
     * @return
     */
    Integer getUsersIdAttention(String userId);

}
