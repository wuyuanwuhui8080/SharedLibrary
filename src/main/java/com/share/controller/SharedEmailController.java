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
import java.util.ArrayList;
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
        //未读邮件数量
        int emailSum = sharedEmailService.getUnreadEmailCount(users.getId());
        //删除邮件数量
        int emailDelSum = sharedEmailService.getDelEmailCount(users.getId());
        //重要邮件数量
        int emailMajorSum = sharedEmailService.getMajorEmailCount(users.getId());
        model.addAttribute("emailList", emailList);
        model.addAttribute("state", 0);
        session.setAttribute("emailSum", emailSum);
        session.setAttribute("emailDelSum", emailDelSum);
        session.setAttribute("emailMajorSum", emailMajorSum);
        return "background/email/email_Index";
    }

    /**
     * 更改选中邮箱状态为已读
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
        SharedEmail email = sharedEmailService.getEmailById(id);
        //是未读邮件改变状态
        if (email.getState() == 2) {
            sharedEmailService.updateState(Arrays.asList(id), Integer.toString(1));
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
        List<SharedEmail> emailList = sharedEmailService.getStateEmail(users.getId(), state);
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


}

