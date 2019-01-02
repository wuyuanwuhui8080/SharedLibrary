package com.share.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.ControllerUtil.EmailPage;
import com.share.pojo.SharedEmail;
import com.baomidou.mybatisplus.extension.service.IService;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 牛自豪
 * @since 2018-12-13
 */
public interface SharedEmailService extends IService<SharedEmail> {


    /**
     * 根据选中的邮箱,更改邮件状态
     *
     * @param idList 选中的邮箱id
     * @return 是否成功
     */
    int updateState(List<String> idList, String state);

    /**
     * 分页查询数据
     *
     * @param page    分页数据
     * @param wrapper 条件
     * @return 邮件集合
     */
    IPage selectSharedEmailList(Page<SharedEmail> page, Wrapper wrapper) ;


}
