package com.share.users.service;

import com.github.pagehelper.PageInfo;
import com.share.pojo.SharedReceiveMail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 邮件收件表
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-03
 */
public interface SharedReceiveMailService extends IService<SharedReceiveMail> {
	/**
	 * 分页查询数据
	 * 
	 * @param userId
	 *            用户id
	 * @param status
	 *            状态
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            每页页数
	 * @return
	 */
	PageInfo<SharedReceiveMail> selectSharedReceiveMailList(String userId,
			String status, Integer pageIndex, Integer pageSize);

	/**
	 * 根据选中的邮箱,更改邮件状态
	 *
	 * @param idList
	 *            选中的邮箱id
	 * @return 是否成功
	 */
	int updateState(List<String> idList, String state);


}
