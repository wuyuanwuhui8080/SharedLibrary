package com.share.service;

import com.share.pojo.SharedEmail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
