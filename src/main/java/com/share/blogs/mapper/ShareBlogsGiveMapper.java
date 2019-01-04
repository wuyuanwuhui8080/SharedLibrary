package com.share.blogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.pojo.ShareBlogsGive;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
public interface ShareBlogsGiveMapper extends BaseMapper<ShareBlogsGive> {

	/**
	 * 添加一个点赞操作
	 * 
	 * @param shareBlogsGive
	 * @return
	 */
	Integer saveBlogGive(ShareBlogsGive shareBlogsGive);

}
