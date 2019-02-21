package com.share.forum.service;

import com.share.pojo.SharedlClassify;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * 添加分类
     * @param className
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    boolean saveClassfy(String className);


    /**
     * 校验名称是否重复
     * @param className
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    boolean getClassfyByName(String className);
}
