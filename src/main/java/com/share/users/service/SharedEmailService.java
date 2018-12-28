package com.share.users.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedEmail;

/**
 * 邮件业务接口
 *
 * @author 牛子豪
 * @time 2018/12/15 15:38
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
