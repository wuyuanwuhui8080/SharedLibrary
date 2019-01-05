package com.share.users.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
import com.share.pojo.SharedUsers;
import com.share.users.service.SharedEmailService;
import com.share.users.service.SharedUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
	private SharedUsersService sharedUsersService;
	@Resource
	private SharedEmailService sharedEmailService;

	@RequestMapping("/dele/{id}")
	public String DeleEmail(@PathVariable String id, Model model) {
		SharedEmail email = sharedEmailService.getById(id);
		boolean delete = email.deleteById();
		if (delete) {
			return "forward:/sharedReceiveMail/emailIndex";
		} else {
			model.addAttribute("email", email);
			model.addAttribute("state", 2);
			return "background/email/email_Index";
		}
	}

	/**
	 * 草稿邮件再点击到编辑页面
	 *
	 * @param id
	 *            草稿邮件id
	 * @param model
	 *            数据传递
	 * @return 视图
	 */
	@RequestMapping("/DraftToCompese/{id}")
	public String DraftToCompese(@PathVariable String id, Model model) {
		SharedEmail email = sharedEmailService.getById(id);
		model.addAttribute("email", email);
		model.addAttribute("state", 2);
		return "background/email/email_Index";
	}

	/**
	 * 查看已经发送的邮件
	 *
	 * @return
	 */
	@RequestMapping("/LookHairEmail/{id}")
	public String LookHairEmail(@PathVariable String id, Model model) {
		SharedEmail email = sharedEmailService.getById(id);
		model.addAttribute("email", email);
		model.addAttribute("state", 4);
		model.addAttribute("type", 1);
		return "background/email/email_Index";
	}

	/**
	 * 写邮件
	 *
	 * @param session
	 *            获取用户
	 * @param email
	 *            页面表单
	 * @param draft
	 *            是否是草稿
	 * @param model
	 *            传回页面
	 * @return 视图
	 */
	@RequestMapping("/emailAdd")
	public String emailAdd(HttpSession session, SharedEmail email, String draft,
			Model model) {
		// 获取当前用户ID
		SharedUsers users = (SharedUsers) session.getAttribute("users");
		// 获取发送人对象
		SharedUsers friend = sharedUsersService
				.getOne(new QueryWrapper<SharedUsers>().eq("userName",
						users.getUserName()));

		// 补全发件表信息
		email.setReceiveName(friend.getUserName());
		email.setReceiveId(friend.getId());
		email.setCreationDate(new Date());
		email.setState(2);
		email.setHairId(users.getId());
		if (!"".equals(draft)) {
			email.setState(5);
			boolean insert = email.insertOrUpdate();
			if (insert) {
				return "forward:/sharedReceiveMail/emailState/5";
			} else {
				model.addAttribute("error", "存为草稿失败");
				model.addAttribute("email", email);
				model.addAttribute("state", 2);
				return "background/email/email_Index";
			}
		}
		// 补全收件表信息
		SharedReceiveMail receiveMail = new SharedReceiveMail();
		receiveMail.setHairId(users.getId());
		receiveMail.setHairName(users.getUserName());
		receiveMail.setReceiveId(friend.getId());
		receiveMail.setCreationDate(new Date());
		receiveMail.setEmailContent(email.getEmailContent());
		receiveMail.setEmailDigest(email.getEmailDigest());
		receiveMail.setState(2);
		boolean flg = sharedEmailService.saveEmail(email, receiveMail);
		if (flg) {
			return "forward:/sharedReceiveMail/emailState/1";
		} else {
			model.addAttribute("error", "发送失败");
			model.addAttribute("email", email);
			model.addAttribute("state", 2);
			return "background/email/email_Index";
		}

	}

}
