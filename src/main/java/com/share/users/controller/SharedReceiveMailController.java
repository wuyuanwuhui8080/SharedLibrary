package com.share.users.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.share.pojo.SharedEmail;
import com.share.pojo.SharedReceiveMail;
import com.share.pojo.SharedUsers;
import com.share.users.service.SharedEmailService;
import com.share.users.service.SharedReceiveMailService;

/**
 * <p>
 * 邮件收件表
 * </p>
 *
 * @author 牛自豪
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/sharedReceiveMail")
public class SharedReceiveMailController {
	@Resource
	private SharedReceiveMailService sharedReceiveMailService;
	@Resource
	private SharedEmailService sharedEmailService;

	/**
	 * 看邮件列表
	 *
	 * @return
	 */
	@GetMapping("/emailIndex")
	public String emailIndex(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			Model model) {
		Session session = SecurityUtils.getSubject().getSession();
		SharedUsers users = (SharedUsers) session.getAttribute("users");
		PageInfo<SharedReceiveMail> pageInfo = sharedReceiveMailService
				.selectSharedReceiveMailList(users.getId(), null, pageIndex, 5);
		// 查询状态邮件的数量
		SelStateEmailSum(session);
		model.addAttribute("page", pageInfo);
		model.addAttribute("state", 0);
		return "background/email/email_Index";
	}

	/**
	 * 更改选中邮箱状态
	 *
	 * @param ids
	 *            id
	 * @return
	 */
	@GetMapping("/emailUpState/{ids}/{state}")
	public String emailUpState(@PathVariable(value = "ids") String[] ids,
			@PathVariable String state, Model model) {
		List<String> idList = Arrays.asList(ids);
		int flg = sharedReceiveMailService.updateState(idList, state);
		if (flg > 0) {// 成功
		} else if (flg == -1) {
			model.addAttribute("errer", "没有选择!");
		} else {// 失败
			model.addAttribute("errer", "标记已读失败!");
		}
		return "forward:/sharedReceiveMail/emailIndex";
	}

	/**
	 * 看详细邮件
	 *
	 * @return
	 */
	@RequestMapping("/emailLook/{id}")
	public String emailLook(@PathVariable(value = "id") String id,
			Model model) {
		SharedReceiveMail email = sharedReceiveMailService.getById(id);
		// 是未读邮件改变状态
		if (email.getState() == 2) {
			email.setState(1);
			sharedReceiveMailService.update(email,
					new QueryWrapper<SharedReceiveMail>().eq("id", id));
		}
		model.addAttribute("email", email);
		model.addAttribute("state", 4);
		model.addAttribute("type", 0);
		return "background/email/email_Index";
	}

	/**
	 * 获取有状态邮件集合
	 *
	 * @return 邮件集合
	 */
	@RequestMapping("/emailState/{state}")
	public String getMajorEmail(@PathVariable String state, Session session,
			Model model,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex) {
		SharedUsers users = (SharedUsers) session.getAttribute("users");
		PageInfo<SharedEmail> iPage = null;
		// 判断显示那个页面的标记
		int flg = 0;
		// 重新加载邮件数量
		SelStateEmailSum(session);
		// 获取状态邮箱集合
		iPage = (PageInfo<SharedEmail>) getiPage(state, pageIndex, 5, users);
		String bt = "";
		if ("3".equals(state)) {
			// 3:重要
			bt = "重要";
			flg = 1;
		} else if ("4".equals(state)) {
			// 4:删除
			bt = "删除";
			flg = 1;
		} else if ("5".equals(state)) {
			// 5:草稿
			bt = "草稿";
			flg = 3;
		} else if ("1".equals(state)) {
			// 1:发件
			bt = "发送";
			flg = 3;
		}
		model.addAttribute("page", iPage);
		model.addAttribute("state", flg);
		model.addAttribute("bt", bt);
		return "background/email/email_Index";
	}

	/**
	 * 查找邮件
	 *
	 * @param text
	 * @return
	 */
	@RequestMapping("/findEmail/{text}")
	public String findEmail(@PathVariable String text) {
		Page<SharedEmail> page = new Page<>(1, 2);

		sharedEmailService.pageMaps(page,
				new QueryWrapper<SharedEmail>().eq("", text));

		return "";
	}

	/**
	 * 跳转发邮件页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/emailCompose")
	public String toEmailCompose(Model model) {
		Session session = SecurityUtils.getSubject().getSession();
		SharedEmail sharedEmail = new SharedEmail();
		sharedEmail.setReceiveName("");
		sharedEmail.setEmailContent("");
		sharedEmail.setEmailDigest("");
		sharedEmail.setId("");
		SelStateEmailSum(session);
		model.addAttribute("state", 2);
		model.addAttribute("email", sharedEmail);
		return "background/email/email_Index";
	}

	/**
	 * 获取状态邮件数量
	 *
	 * @param session
	 */
	private void SelStateEmailSum(Session session) {
		SharedUsers users = (SharedUsers) session.getAttribute("users");
		// 未读邮件数量
		int emailSum = sharedReceiveMailService
				.count(new QueryWrapper<SharedReceiveMail>()
						.eq("receive_id", users.getId()).eq("state", 2));
		// 删除邮件数量
		int emailDelSum = sharedReceiveMailService
				.count(new QueryWrapper<SharedReceiveMail>()
						.eq("receive_id", users.getId()).eq("state", 4));
		// 重要邮件数量
		int emailMajorSum = sharedReceiveMailService
				.count(new QueryWrapper<SharedReceiveMail>()
						.eq("receive_id", users.getId()).eq("state", 3));

		// 草稿邮件数量
		int emailDraftSum = sharedEmailService
				.count(new QueryWrapper<SharedEmail>()
						.eq("hair_id", users.getId()).eq("state", 5));
		session.setAttribute("emailSum", emailSum);
		session.setAttribute("emailDraftSum", emailDraftSum);
		session.setAttribute("emailDelSum", emailDelSum);
		session.setAttribute("emailMajorSum", emailMajorSum);
	}

	/**
	 * 获取集合
	 * 
	 * @param state
	 *            状态
	 * @param pageIndex
	 *            起始页
	 * @param pageSize
	 *            每页的页数
	 * @param users
	 *            session 用户对象
	 * @return
	 */
	private PageInfo<?> getiPage(@PathVariable String state, Integer pageIndex,
			Integer pageSize, SharedUsers users) {
		PageInfo<?> iPage;// 草稿
		if ("5".equals(state)) {
			iPage = sharedEmailService.findListEmail(users.getId(), state,
					pageIndex, pageSize);
		} else if ("1".equals(state)) {// 自己发送的邮件
			iPage = sharedEmailService.findListEmail(users.getId(), null,
					pageIndex, pageSize);
		} else {
			// 查询重要邮箱集合
			iPage = sharedReceiveMailService
					.selectSharedReceiveMailList(users.getId(), state, 0, 15);
		}
		return iPage;
	}

}
