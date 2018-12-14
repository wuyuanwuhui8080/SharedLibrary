package com.share.service;

import com.share.pojo.SharedEmail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bean
 * @since 2018-12-13
 */
public interface SharedEmailService extends IService<SharedEmail> {

    /**
     * 根据用户id获取该用户邮件
     * @param id 用户id
     * @return  邮件集合
     */
    List<SharedEmail> getEmail(String id);


    int getUnreadEmailCount(String id);
}
