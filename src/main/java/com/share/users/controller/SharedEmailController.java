package com.share.users.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.share.pojo.SharedEmail;
import com.share.pojo.SharedUsers;
import com.share.users.service.SharedEmailService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Bean
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
        List<SharedEmail> emailList = sharedEmailService.getEmail(users.getId());
        model.addAttribute("emailList", emailList);
        return "background/email/email_List";
    }


    /**
     * 看详细邮件
     *
     * @return
     */
    @GetMapping("/emailAdd")
    public String emailAdd() {
        return "background/email/email_detail";
    }


    /**
     * 写邮件
     *
     * @return
     */
    @GetMapping("/emailLook")
    public String emailLook() {
        return "background/email/email_simditor";
    }

}

