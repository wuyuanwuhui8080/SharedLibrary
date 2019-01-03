package com.share.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.ControllerUtil.EmailPage;
import com.share.pojo.SharedEmail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedReceiveMail;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

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
     * 发送邮件
     * @param email 发件对象
     * @param receiveMail 收件对象
     * @return 成功或失败
     */
    boolean saveEmail(SharedEmail email, SharedReceiveMail receiveMail);


}
