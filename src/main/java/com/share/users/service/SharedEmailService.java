package com.share.users.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
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
	 * 
	 * @param email
	 *            发件对象
	 * @param receiveMail
	 *            收件对象
	 * @return 成功或失败
	 */
	boolean saveEmail(SharedEmail email, SharedReceiveMail receiveMail);

	/**
	 * 查询邮件
	 * 
	 * @param hairId
	 *            发送人的id
	 * @param status
	 *            状态
	 * @param pageInex
	 *            起始页
	 * @param pageSize
	 *            每页的页数
	 * @return
	 */
	PageInfo<SharedEmail> findListEmail(String hairId, String status,
                                        Integer pageInex, Integer pageSize);

}
