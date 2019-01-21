package com.share.users.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.constant.PageConstant;
import com.share.pojo.SharedFans;
import com.share.users.mapper.SharedFansMapper;
import com.share.users.service.SharedFansService;
import com.share.util.ReturnResult;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 粉丝业务的实现类
 *
 * @author 博博大人
 * @time 2018/12/14 15:15
 */
@Service
public class SharedFansServiceImpl extends ServiceImpl<SharedFansMapper, SharedFans> implements SharedFansService {

    @Resource
    private SharedFansMapper sharedFansMapper;

    /**
     * 查找我关注的用户
     *
     * @param fenId 当前用户id
     * @return 关注的用户
     */
    public PageInfo<SharedFans> findMeattention(String fenId, Integer pageIndex) {
        QueryWrapper<SharedFans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fans_id", fenId);
        PageInfo<SharedFans> pageInfo = new PageInfo<>(sharedFansMapper.findMeattention(fenId, pageIndex, PageConstant.FENPAGESIZE));
        return pageInfo;
    }

    /**
     * 查看我的粉丝
     *
     * @param userId 当前用户id
     * @return 粉丝集合
     */
    public PageInfo<SharedFans> findMeFenList(String userId, Integer pageIndex) {
        QueryWrapper<SharedFans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("me_id", userId);
        PageInfo<SharedFans> pageInfo = new PageInfo<>(sharedFansMapper.findMeFenList(userId, pageIndex, PageConstant.FENPAGESIZE));
        return pageInfo;
    }

    /**
     * 获取粉丝数量
     *
     * @param usersId 用户id
     * @return
     */
    @Override
    public Integer getFensCount(String usersId) {
        QueryWrapper<SharedFans> wrapper = new QueryWrapper<>();
        wrapper.eq("me_id", usersId);
        return super.count(wrapper);
    }

    /**
     * 添加粉丝
     *
     * @param fanId  粉丝id,取当前用户id
     * @param userId 被粉id,取被查看用户的id
     * @return 返回处理结果
     */
    public ReturnResult addFans(String fanId, String userId) {
        SharedFans sharedFans = new SharedFans();
        //补全对象
        sharedFans.setCreationDate(new Date());
        sharedFans.setUpdateDate(new Date());
        sharedFans.setFansId(fanId);
        sharedFans.setMeId(userId);
        if (sharedFans.insert()) {
            //添加成功
            return ReturnResult.ok();
        } else {
            //添加失败
            return ReturnResult.error("关注失败!请稍后重试...");
        }
    }

    /**
     * 取消粉丝
     *
     * @param fanId  粉丝id,取当前用户id
     * @param userId 被粉id,取被查看用户的id
     * @return 返回处理结果
     */
    public ReturnResult delFans(String fanId, String userId) {
        QueryWrapper<SharedFans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("me_id", userId);
        queryWrapper.eq("fans_id", fanId);
        boolean remove = super.remove(queryWrapper);
        if (remove) {
            return ReturnResult.ok();
        }
        return ReturnResult.error("取消关注失败,请稍后再试.....");

    }


    /**
     * 查询当前登录用户是否是查看用户的粉丝
     *
     * @param fanId  当前用户
     * @param userId 查看的用户
     */
    public boolean IsFans(String fanId, String userId) {
        QueryWrapper<SharedFans> wrapper = new QueryWrapper<>();
        wrapper.eq("me_id", userId);
        wrapper.eq("fans_id", fanId);
        int count = super.count(wrapper);
        return count > 0;
    }
}

