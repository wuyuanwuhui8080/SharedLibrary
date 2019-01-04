package com.share.users.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedlPosition;

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
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    List<SharedlPosition> findList();
}
