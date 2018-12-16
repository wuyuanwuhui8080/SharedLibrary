package com.share.ControllerUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 访问默认页的控制器
 *
 * @author 博博
 * @Title: IndexController
 * @ProjectName SharedLibrary
 * @time 2018/12/16 17:06
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String Index() {
		return "index";
	}

}
