package com.share.forum.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.constant.PageConstant;
import com.share.forum.mapper.SharedForumMapper;
import com.share.forum.mapper.SharedForumVOReposiory;
import com.share.forum.service.SharedForumService;
import com.share.forum.vo.ForumAndComment;
import com.share.forum.vo.SharedForumVO;
import com.share.pojo.SharedForum;
import com.share.pojo.SharedUsers;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Service
public class SharedForumServiceImpl
		extends ServiceImpl<SharedForumMapper, SharedForum>
		implements SharedForumService {

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
		sharedForum.setUserId(
				((SharedUsers) session.getAttribute("users")).getId());
		// 检验是否添加成功
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
			SharedForumVO save = sharedForumVOReposiory.save(sharedForumVO);
			if (save == null) {
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 个人页面中获取帖子集合
	 *
	 * @param userId
	 *            用户id
	 * @param pageIndex
	 *            分页数
	 * @return
	 */
	public PageInfo<SharedForum> findForymByUserId(String userId,
												   Integer pageIndex) {
		QueryWrapper<SharedForum> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		PageHelper.startPage(pageIndex, PageConstant.FORUMPAGESIZE);
		List<SharedForum> list = super.list(queryWrapper);
		PageInfo<SharedForum> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 查询 pageSize 条帖子
	 *
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            每页的页数
	 * @return
	 */
	@Override
	public PageInfo<SharedForum> findList(Integer typeId,Integer pageIndex, Integer pageSize) {
		// 拦截语句并分页
		PageHelper.startPage(pageIndex, pageSize);
		PageInfo<SharedForum> pageInfo = new PageInfo<>(
				sharedForumMapper.findList(typeId));
		return pageInfo;
	}

	/**
	 * 查询单个帖子
	 * 
	 * @param forumId
	 *            传入的帖子的id
	 * @return
	 */
	@Override
	public ForumAndComment findListByForumId(String forumId) {
		return sharedForumMapper.findListByForumId(forumId);
	}

	/**
	 * 删除帖子
	 * 
	 * @param id
	 *            传入的id
	 * @return
	 */
	@Override
	public boolean deleteForum(String id) {
		if (sharedForumMapper.deleteForum(id) > 0) {
			sharedForumVOReposiory.deleteById(id);
			return true;
		}
		return false;
	}

	/**
	 * 根據文字进行全文引擎搜索
	 * 
	 * @param name
	 *            文字
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            结束
	 * @return
	 */
	@Override
	public List<SharedForumVO> findListByName(String name, Integer pageIndex,
			Integer pageSize) {
		// 初始化分页条件
		Pageable pageable = new PageRequest(pageIndex, pageSize);
		// 使用queryStringQuery完成单字符串查询
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryStringQuery(name)).withPageable(pageable)
				.build();
		return elasticsearchTemplate.queryForList(searchQuery,
				SharedForumVO.class);
	}

	/**
	 * 获取七天内热帖
	 * 
	 * @return
	 */
	@Override
	public List<SharedForum> findWithinSevenDays() {
		return sharedForumMapper.findWithinSevenDays();
	}

	@Override
	public List<SharedForum> findListStipk(Collection<Object> forumId) {
		return sharedForumMapper.findListStick(forumId);
	}
}
