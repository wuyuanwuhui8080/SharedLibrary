package com.share.controller;


import com.share.pojo.SharedEmail;
import com.share.pojo.SharedUsers;
import com.share.service.SharedEmailService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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
        List<SharedEmail> emailList = sharedEmailService.getEmaiListlByUserId(users.getId());
        model.addAttribute("emailList", emailList);
        int emailSum = sharedEmailService.getUnreadEmailCount(users.getId());
        session.removeAttribute("emailSum");
        session.setAttribute("emailSum", emailSum);
        return "background/email/email_List";
    }

    /**
     * 更改选中邮箱状态为已读
     * 未完成
     * 返回页面无法显示
     *
     * @param ids     id
     * @param session
     * @return
     */
    @GetMapping("/emailUpState")
    public String emailUpState(String[] ids, HttpSession session) {
        SharedUsers users = (SharedUsers) session.getAttribute("users");
        List<String> idList = Arrays.asList(ids);
        int flg = sharedEmailService.updateState(idList);
        if (flg > 0) {//成功
            //查询邮箱集合
            List<SharedEmail> emailList = sharedEmailService.getEmaiListlByUserId(users.getId());
            StringBuffer msg = new StringBuffer();
            for (SharedEmail email : emailList) {
                msg.append("<tr class='read'>");
                msg.append("<td class='check-mail'>");
                msg.append("<input type='checkbox' name='email_id' value='" + email.getId() + "' class='i-checks'></td>");
                msg.append("<td class='mail-ontact'><a href='email_detail.ftl'>" + email.getFriend_name() + "</a>");
                msg.append("<#if (" + email.getState() + "==2)>");
                msg.append("<span class='label label-warning pull-right'>未读邮件</span>");
                msg.append("<#else>");
                msg.append(" < span class='label label-warning pull-right' ></span > ");
                msg.append("</#if>");
                msg.append("</td>");
                msg.append("<td class='mail-subject'><a href='email_detail.ftl'>" + email.getEmailContent() + "</a></td>");
                msg.append("<td class=''></td>");
                msg.append("<td class='text-right mail-date'>" + email.getCreationDate() + "?date</td>");
                msg.append("</tr>");
            }
            return msg.toString();
        } else {//失败
            return "标记已读失败!";
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
    String emailLook(@PathVariable(value = "id") String id, Model model) {
        SharedEmail email = sharedEmailService.getEmailById(id);
        model.addAttribute("email", email);
        return "background/email/email_detail";
    }

}

