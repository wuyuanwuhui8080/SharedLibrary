package com.share.users.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
import com.share.users.mapper.SharedEmailMapper;
import com.share.users.service.SharedEmailService;
import com.share.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Bean
 * @since 2018-12-13
 */
@Service
public class SharedEmailServiceImpl
		extends ServiceImpl<SharedEmailMapper, SharedEmail>
		implements SharedEmailService {
	/**
	 * 发送邮件
	 *
	 * @param email
	 *            发件对象
	 * @param receiveMail
	 *            收件对象
	 * @return 成功或失败
	 */
	@Override
	public boolean saveEmail(SharedEmail email, SharedReceiveMail receiveMail) {
		boolean insert = email.insertOrUpdate();
		// 添加发件表
		if (insert) {
			// 进入逻辑添加成功,再添加收件表
			boolean flg = receiveMail.insertOrUpdate();
			if (flg) {
				// 进入逻辑收件表添加成功,返回true
				insert = true;
			} else {
				insert = false;
			}
		} else {
			insert = false;
		}
		return insert;
	}

	/**
	 *
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
	@Override
	public PageInfo<SharedEmail> findListEmail(String hairId, String status,
                                               Integer pageInex, Integer pageSize) {
		LambdaQueryWrapper<SharedEmail> wrapper = new LambdaQueryWrapper<>();
		// 判断状态是否为空
		if (StringUtils.isNotNull(status)) {
			wrapper.eq(SharedEmail::getState, status);
		}
		// 判断发件人id是否为空
		if (StringUtils.isNotNull(hairId)) {
			wrapper.eq(SharedEmail::getHairId, hairId);
		}
        wrapper.orderByDesc(SharedEmail::getCreationDate);
		PageHelper.startPage(pageInex, pageSize);
		PageInfo<SharedEmail> info = new PageInfo<>(super.list(wrapper));
		return info;
	}
}
