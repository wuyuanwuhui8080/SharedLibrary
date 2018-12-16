package com.share.service;

import com.share.pojo.SharedlPosition;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 职位业务接口
 *
 * @author cll 陈留领
 * @time 2018/12/15 16:44
 */
public interface SharedlPositionService extends IService<SharedlPosition> {

    /**
     * 查询所有职位
     * @return
     */
    List<SharedlPosition> findList();
}
