package com.share.users.controller;


import com.github.pagehelper.PageInfo;
import com.share.pojo.SharedFans;
import com.share.pojo.SharedUsers;
import com.share.users.service.SharedFansService;
import com.share.util.ReturnResult;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 粉丝前端控制器
 * </p>
 *
 * @author 牛自豪
 * @since 2018-12-11
 */
@Controller
@RequestMapping("/sharedFans")
public class SharedFansController {

    @Resource
    private SharedFansService sharedFansService;

    /**
     * 我关注的人
     *
     * @param model 传值
     * @return 视图
     */
    @RequestMapping("/findMeattention")
    public String findMeattention(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex, Model model) {
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        PageInfo<SharedFans> pageInfo = sharedFansService.findMeattention(users.getId(), pageIndex);
        model.addAttribute("page", pageInfo);
        //判断从哪里跳转的,根据不同跳转,显示不同的标题
        model.addAttribute("type", 2);
        return "background/fen/fen_Fans";
    }

    /**
     * 查看粉丝
     *
     * @param model 传值
     * @return 视图
     */
    @RequestMapping("/findMeFenList")
    public String findMeFenList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex, Model model) {
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        PageInfo<SharedFans> pageInfo = sharedFansService.findMeFenList(users.getId(), pageIndex);
        model.addAttribute("page", pageInfo);
        //判断从哪里跳转的,根据不同跳转,显示不同的标题
        model.addAttribute("type", 1);
        return "background/fen/fen_Fans";
    }


    /**
     * 关注用户
     *
     * @param userId 被关注者id
     * @return 视图
     */
    @RequestMapping("/addFans/{userId}")
    @ResponseBody
    public ReturnResult addFans(@PathVariable String userId) {
        //获取当前登录的用户
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        return sharedFansService.addFans(users.getId(), userId);
    }

    /**
     * 取消关注用户
     *
     * @param userId 被关注者id
     * @return 视图
     */
    @RequestMapping("/delFans/{userId}")
    @ResponseBody
    public ReturnResult delFans(@PathVariable String userId) {
        //获取当前登录的用户
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        return sharedFansService.delFans(users.getId(), userId);
    }

}

