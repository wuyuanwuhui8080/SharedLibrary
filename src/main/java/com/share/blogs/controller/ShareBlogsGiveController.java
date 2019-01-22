package com.share.blogs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.share.blogs.service.ShareBlogsGiveService;
import com.share.pojo.ShareBlogsGive;
import com.share.util.ReturnResult;
import com.share.util.StringUtils;

/**
 * 点赞表前端控制器
 *
 * @author 博博大人
 * @time 2018/12/22 0:00
 */
@RestController
@RequestMapping("/shareBlogsGive")
public class ShareBlogsGiveController {

    @Resource
    private ShareBlogsGiveService blogsGiveService;

    /**
     * 点赞记录管理
     *
     * @param shareBlogsGive
     * @return
     */
    @PostMapping("/getGiveNum")
    public ReturnResult getGiveNum(ShareBlogsGive shareBlogsGive,
                                   @RequestParam("giveId") String giveId) {
        Map<String, Object> myMap = new HashMap<>();
        if (giveId.equals("0")) {
            String uuid = StringUtils.randomUUID();
            shareBlogsGive.setId(uuid);
            if (blogsGiveService.saveBlogGive(shareBlogsGive)) {
                Integer count = blogsGiveService
                        .getCount(shareBlogsGive.getBlogsId());
                myMap.put("blogGiveId", shareBlogsGive.getId());
                myMap.put("blogId", shareBlogsGive.getBlogsId());
                myMap.put("count", count);
                return ReturnResult.ok(myMap);
            } else {
                return ReturnResult.error("点赞失败!");
            }
        } else {
            if (blogsGiveService.deleteBlogGiveById(giveId)) {
                Integer count = blogsGiveService
                        .getCount(shareBlogsGive.getBlogsId());
                myMap.put("count", count);
                myMap.put("blogId", shareBlogsGive.getBlogsId());
                return ReturnResult.ok(myMap);
            } else {
                return ReturnResult.error("取消点赞失败!");
            }
        }
    }

}
