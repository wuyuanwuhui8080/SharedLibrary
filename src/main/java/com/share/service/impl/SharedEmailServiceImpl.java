package com.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.ControllerUtil.EmailPage;
import com.share.mapper.SharedEmailMapper;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
import com.share.service.SharedEmailService;
import javafx.scene.control.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-13
 */
@Service
public class SharedEmailServiceImpl extends ServiceImpl<SharedEmailMapper, SharedEmail> implements SharedEmailService {
    /**
     * 发送邮件
     *
     * @param email       发件对象
     * @param receiveMail 收件对象
     * @return 成功或失败
     */
    @Override
    public boolean saveEmail(SharedEmail email, SharedReceiveMail receiveMail ) {
        boolean insert = email.insertOrUpdate();
        //添加发件表
        if (insert) {
            //进入逻辑添加成功,再添加收件表
            boolean flg = receiveMail.insertOrUpdate();
            if (flg) {
                //进入逻辑收件表添加成功,返回true
                insert = true;
            } else {
                insert = false;
            }
        } else {
            insert = false;
        }
        return insert;
    }
}
