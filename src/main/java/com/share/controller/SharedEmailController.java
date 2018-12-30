package com.share.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedUsers;
import com.share.service.SharedEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 牛自豪
 * @since 2018-12-13
 */
@Controller
@RequestMapping("/sharedEmail")
public class SharedEmailController {

    @Resource
    private SharedEmailService sharedEmailService;

    /**
     * 看邮件列表
     *
     * @return
     */
    @GetMapping("/emailIndex")
    public String emailIndex(HttpSession session, Model model) {
        SharedUsers users = (SharedUsers) session.getAttribute("users");
        //查询邮箱集合
        // List<SharedEmail> emailList = sharedEmailService.list(new QueryWrapper<SharedEmail>().eq("me_id", users.getId()));
        IPage<SharedEmail> page = new Page<>(1, 8);
        page= sharedEmailService.page(page,
                new QueryWrapper<SharedEmail>()
                .eq("me_id", users.getId())
                .orderByDesc("creation_date")
        );
        page.setTotal((long) page.getRecords().size());

        //未读邮件数量
        int emailSum = sharedEmailService.count(
                new QueryWrapper<SharedEmail>()
                        .eq("me_id", users.getId())
                        .eq("state", 2)
        );
        //删除邮件数量
        int emailDelSum = sharedEmailService.count(
                new QueryWrapper<SharedEmail>()
                        .eq("me_id", users.getId())
                        .eq("state", 4)
        );
        //重要邮件数量
        int emailMajorSum = sharedEmailService.count(
                new QueryWrapper<SharedEmail>()
                        .eq("me_id", users.getId())
                        .eq("state", 3)
        );
        model.addAttribute("page", page);
        model.addAttribute("state", 0);
        session.setAttribute("emailSum", emailSum);
        session.setAttribute("emailDelSum", emailDelSum);
        session.setAttribute("emailMajorSum", emailMajorSum);
        return "background/email/email_Index";
    }

    /**
     * 更改选中邮箱状态
     * 返回页面无法显示
     *
     * @param ids id
     * @return
     */
    @GetMapping("/emailUpState/{ids}/{state}")
    public String emailUpState(@PathVariable(value = "ids") String[] ids, @PathVariable String state, Model model) {
        List<String> idList = Arrays.asList(ids);
        int flg = sharedEmailService.updateState(idList, state);
        if (flg > 0) {//成功
            return "redirect:/sharedEmail/emailIndex";
        } else if (flg == -1) {
            model.addAttribute("errer", "!");
            return "forward:/sharedEmail/emailIndex";
        } else {//失败
            model.addAttribute("errer", "标记已读失败!");
            return "forward:/sharedEmail/emailIndex";
        }
    }


    /**
     * 写邮件
     *
     * @return
     */
    @GetMapping("/emailAdd")
    public String emailAdd() {
        return "background/email/email_detail";
    }


    /**
     * 看详细邮件
     *
     * @return
     */
    @RequestMapping("/emailLook/{id}")
    public String emailLook(@PathVariable(value = "id") String id, Model model) {
        //SharedEmail email = sharedEmailService.getEmailById(id);
        SharedEmail email = sharedEmailService.getById(id);
        //是未读邮件改变状态
        if (email.getState() == 2) {
            email.setState(1);
            sharedEmailService.update(email, new QueryWrapper<SharedEmail>().eq("id", id));
        }
        model.addAttribute("email", email);
        return "background/email/email_detail";
    }

    /**
     * 获取有状态邮件集合
     *
     * @return 邮件集合
     */
    @RequestMapping("/emailState/{state}")
    public String getMajorEmail(@PathVariable String state, HttpSession session, Model model) {
        SharedUsers users = (SharedUsers) session.getAttribute("users");
        //查询重要邮箱集合
        List<SharedEmail> emailList = sharedEmailService.list(new QueryWrapper<SharedEmail>()
                .eq("me_id", users.getId())
                .eq("state", state)
        );
        String bt = "";
        model.addAttribute("emailList", emailList);
        if ("3".equals(state)) {
            //3:重要
            bt = "重要";
        } else if ("4".equals(state)) {
            //4:删除
            bt = "删除";
        } else if ("5".equals(state)) {
            //5:草稿
            bt = "草稿";
        }
        model.addAttribute("state", 1);
        model.addAttribute("bt", bt);
        return "background/email/email_Index";
    }

    @RequestMapping("/findEmail/{text}")
    public String findEmail(@PathVariable String text) {
        Page<SharedEmail> page = new Page<>(1, 2);

        sharedEmailService.pageMaps(page, new QueryWrapper<SharedEmail>().eq("", text));


        return "";
    }


}

