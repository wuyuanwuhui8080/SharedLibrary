package com.share.blogs.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

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

	/**
	 * 添加点赞记录
	 * 
	 * @param shareBlogsGive
	 *            传入的实体
	 * @return
	 */
	@Override
	public Boolean saveBlogGive(ShareBlogsGive shareBlogsGive) {
		shareBlogsGive.setCreationDate(new Date());
		return super.save(shareBlogsGive);
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
}
