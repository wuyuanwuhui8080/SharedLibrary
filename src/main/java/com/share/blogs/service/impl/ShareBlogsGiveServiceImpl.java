package com.share.blogs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.blogs.mapper.ShareBlogsGiveMapper;
import com.share.blogs.service.ShareBlogsGiveService;
import com.share.pojo.ShareBlogsGive;

/**
 * 点赞表业务实现层
 * 
 * @author 博博大人
 * @time 2018/12/21 23:58
 */
@Service
public class ShareBlogsGiveServiceImpl
		extends ServiceImpl<ShareBlogsGiveMapper, ShareBlogsGive>
		implements ShareBlogsGiveService {

	@Resource
	private ShareBlogsGiveMapper blogsGiveMapper;

	/**
	 * 添加点赞记录
	 * 
	 * @param shareBlogsGive
	 *            传入的实体
	 * @return
	 */
	@Override
	public Boolean saveBlogGive(ShareBlogsGive shareBlogsGive) {
		return blogsGiveMapper.saveBlogGive(shareBlogsGive) == 1 ? true : false;
	}

	/**
	 * 删除一条点赞记录
	 * 
	 * @param id
	 *            id
	 * @return
	 */
	@Override
	public Boolean deleteBlogGiveById(String id) {
		return super.removeById(id);
	}

	/**
	 * 根据博客id查询点赞数
	 * 
	 * @param blogId
	 * @return
	 */
	@Override
	public Integer getCount(String blogId) {
		return super.count(new QueryWrapper<ShareBlogsGive>().lambda()
				.eq(ShareBlogsGive::getBlogsId, blogId));
	}
}
