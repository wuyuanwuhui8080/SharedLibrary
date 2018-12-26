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
     * 根据用户id获取该用户邮件
     *
     * @param id 用户id
     * @return 邮件集合
     */
    List<SharedEmail> getEmaiListlByUserId(String id);

    /**
     * 根据邮件id获取邮件
     *
     * @param id 邮件id
     * @return 邮件
     */
    SharedEmail getEmailById(String id);

    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @return 是否成功
     */
    int updateState(List<String> idList,String state);


    /**
     * 根据用户id获取有状态的邮件集合
     * @param id 用户id
     * @return 有状态的邮件集合
     */
    List<SharedEmail> getStateEmail(String id,String state);
}
