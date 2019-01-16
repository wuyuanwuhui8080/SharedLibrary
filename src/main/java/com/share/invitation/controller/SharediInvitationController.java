package com.share.invitation.controller;


import com.share.invitation.service.SharedlClassifyService;
import com.share.pojo.SharedlClassify;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Controller
@RequestMapping("/sharediInvitation")
public class SharediInvitationController {

    @Resource
    private SharedlClassifyService classifyService;

    @GetMapping("/goIndex")
    public String goIndex() {
        return "index";
    }

    @GetMapping("/goWriteInvitation")
    public String goWriteInvitation(Model model) {
        List<SharedlClassify> classifyList = classifyService.findSharedlClassifyList();
        model.addAttribute("classifyList",classifyList);
        return "reception/write_forum";
    }

}

