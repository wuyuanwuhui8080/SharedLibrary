package com.share.users.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
/**
 * 邮件业务接口
 *
 * @author 牛子豪
 * @time 2018/12/15 15:38
 */
public interface SharedEmailService extends IService<SharedEmail> {


    /**
     * 发送邮件
     * @param email 发件对象
     * @param receiveMail 收件对象
     * @return 成功或失败
     */
    boolean saveEmail(SharedEmail email, SharedReceiveMail receiveMail);


}
