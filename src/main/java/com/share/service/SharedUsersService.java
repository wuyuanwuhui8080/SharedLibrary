package com.share.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedUsers;

/**
 * Users业务接口
 *
 * @author 博博大人
 * @time 2018/12/13 14:21
 */
public interface SharedUsersService extends IService<SharedUsers> {

    /**
     * 添加用户
     *
     * @param users 传入的用户实体
     * @return Boolean
     */
    Boolean saveNorsalSharedUsers(SharedUsers users);

    /**
     * 判断用户名是否重复
     *
     * @param userName 传入的用户名
     * @return Boolean
     */
    Boolean getSharedUsersGetUserName(String userName);

    /**
     * 根据用户名 查询是否存在
     *
     * @param userName 传入的用户名
     * @return SharedUsers
     */
    SharedUsers getSharedUsersByUserName(String userName);

    /**
     *根据用户名或真实姓名查询用户列表
     * @author cll 陈留领
     * @time 2018/12/15 16:05
     */
	List<SharedUsers> findUsersListByUserNameOrRealName(String name,
			Integer position);

	/**
	 * 修改头像
	 * 
	 * @param users
	 * @return
	 */
	Boolean updateUserHeadImg(SharedUsers users);
}
