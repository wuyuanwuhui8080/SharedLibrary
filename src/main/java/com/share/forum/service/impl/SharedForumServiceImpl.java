package com.share.forum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.forum.mapper.SharedForumVOReposiory;
import com.share.forum.vo.SharedForumVO;
import com.share.pojo.SharedForum;
import com.share.forum.mapper.SharedForumMapper;
import com.share.forum.service.SharedForumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.pojo.SharedUsers;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Service
public class SharedForumServiceImpl extends ServiceImpl<SharedForumMapper, SharedForum> implements SharedForumService {

    @Resource
    private SharedForumVOReposiory sharedForumVOReposiory;

    @Resource
    private SharedForumMapper sharedForumMapper;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 添加数据库数据并且添加es数据
     *
     * @param sharedForum
     * @return
     */
    @Override
    public Boolean saveForum(SharedForum sharedForum) {
        Session session = SecurityUtils.getSubject().getSession();
        // 设置当前时间
        sharedForum.setCreationDate(new Date());
        sharedForum.setUserId(((SharedUsers) session.getAttribute("users")).getId());
        // 检验是否添加成功
        try {
            if (super.save(sharedForum)) {
                // 组装es数据
                SharedForumVO sharedForumVO = new SharedForumVO();
                sharedForumVO.setId(sharedForum.getId());
                sharedForumVO.setContent(sharedForum.getContent());
                sharedForumVO.setClassId(sharedForum.getClassId());
                sharedForumVO.setCreationDate(LocalDateTime.now());
                sharedForumVO.setTitle(sharedForum.getTitle());
                sharedForumVO.setUserId(sharedForum.getUserId());
                // 判断是否添加成功！ 只要没有发生异常就代表添加成功
                sharedForumVOReposiory.save(sharedForumVO);
                return true;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            return false;
        }
        return false;
    }

    /**
     * 查询 pageSize 条帖子
     *
     * @param pageIndex 起始页
     * @param pageSize  每页的页数
     * @return
     */
    @Override
    public PageInfo<SharedForum> findList(Integer pageIndex, Integer pageSize) {
        // 拦截语句并分页
        PageHelper.startPage(pageIndex,pageSize);

        PageInfo<SharedForum> pageInfo = new PageInfo<>(sharedForumMapper.findList());
        return pageInfo;
    }
}
