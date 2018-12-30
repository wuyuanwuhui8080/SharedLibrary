package com.share.service;

import com.share.pojo.SharedEmail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 牛自豪
 * @since 2018-12-13
 */
public interface SharedEmailService extends IService<SharedEmail> {


    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @return 是否成功
     */
    int updateState(List<String> idList,String state);

}
