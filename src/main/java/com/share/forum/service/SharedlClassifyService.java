package com.share.forum.service;

import com.share.pojo.SharedlClassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 论坛分类业务层
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-15
 */
public interface SharedlClassifyService extends IService<SharedlClassify> {

    /**
     * 查询所有分类
     *
     * @return
     */
    List<SharedlClassify> findSharedlClassifyList();

}
