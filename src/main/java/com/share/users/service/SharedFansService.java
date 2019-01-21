package com.share.users.service;

import com.github.pagehelper.PageInfo;
import com.share.util.ReturnResult;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.pojo.SharedFans;

import java.util.List;

/**
 * 粉丝的业务管理
 *
 * @author 博博大人
 * @time 2018/12/14 15:12
 */
public interface SharedFansService extends IService<SharedFans> {

    /**
     * 查找我关注的用户
     *
     * @param fenId 当前用户id
     * @return 关注的用户
     */
    PageInfo<SharedFans> findMeattention(String fenId, Integer pageIndex);

    /**
     * 查看我的粉丝
     *
     * @param userId 当前用户id
     * @return 粉丝集合
     */
    PageInfo<SharedFans> findMeFenList(String userId, Integer pageIndex);

    /**
     * 获取某个用户底下的所有粉丝数量
     *
     * @param usersId 用户id
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Integer getFensCount(String usersId);

    /**
     * 添加粉丝
     *
     * @param fanId  粉丝id,取当前用户id
     * @param userId 被粉id,取被查看用户的id
     * @return 返回处理结果
     */
    ReturnResult addFans(String fanId, String userId);

    /**
     * 取消粉丝
     *
     * @param fanId  粉丝id,取当前用户id
     * @param userId 被粉id,取被查看用户的id
     * @return 返回处理结果
     */
    ReturnResult delFans(String fanId, String userId);

    /**
     * 查询当前登录用户是否是查看用户的粉丝
     *
     * @param fanId  当前用户
     * @param userId 查看的用户
     */
    boolean IsFans(String fanId, String userId);


}
