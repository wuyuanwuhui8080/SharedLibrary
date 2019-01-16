package com.share.invitation.service;

import com.share.pojo.SharedlClassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 论坛分类业务层
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-08
 */
public interface SharedlClassifyService extends IService<SharedlClassify> {

    /**
     * 查询所有分类
     *
     * @return
     */
    List<SharedlClassify> findSharedlClassifyList();

}
