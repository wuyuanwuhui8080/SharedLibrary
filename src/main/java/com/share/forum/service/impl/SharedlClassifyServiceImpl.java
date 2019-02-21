package com.share.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.share.pojo.SharedlClassify;
import com.share.forum.mapper.SharedlClassifyMapper;
import com.share.forum.service.SharedlClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-15
 */
@Service
public class SharedlClassifyServiceImpl
		extends ServiceImpl<SharedlClassifyMapper, SharedlClassify>
		implements SharedlClassifyService {

	/**
	 * 查询所有分类
	 * 
	 * @return
	 */
	@Override
	public List<SharedlClassify> findSharedlClassifyList() {
		return super.list();
	}

	/**
	 * 添加分类
	 * 
	 * @param className
	 * @return
	 */
	@Override
	public boolean saveClassfy(String className) {
		return super.save(new SharedlClassify(null, className));
	}

	/**
	 * 校验名称是否重复
	 * 
	 * @param className
	 * @return
	 */
	@Override
	public boolean getClassfyByName(String className) {
		LambdaQueryWrapper<SharedlClassify> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(SharedlClassify::getClassifyName, className);
		return super.count(wrapper) > 0 ? true : false;
	}
}
