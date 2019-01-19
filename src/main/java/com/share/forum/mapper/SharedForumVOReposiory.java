package com.share.forum.mapper;

import com.share.forum.vo.SharedForumVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 博博
 * @Title: SharedForumVOReposiory
 * @ProjectName SharedLibrary
 * @time 2019/1/18 12:02
 */
public interface SharedForumVOReposiory extends ElasticsearchRepository<SharedForumVO, String> {
}
