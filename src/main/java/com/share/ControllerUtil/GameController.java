package com.share.ControllerUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 博博
 * @Title: GameController
 * @ProjectName SharedLibrary
 * @time 2018/12/27 11:31
 */
@Controller
public class GameController {

	@GetMapping("/goFragment")
	public String goFragment() {
		return "background/youxi/fragment";
	}

	@GetMapping("/goAAutomobile")
	public String goAAutomobile() {
		return "background/youxi/automobile";
	}

	@GetMapping("/gogluttonous")
	public String gogluttonous() {
		return "background/youxi/gluttonous";
	}

	@GetMapping("/gozombie")
	public String gozombie() {
		return "background/youxi/zombie";
	}

	@GetMapping("/godonkeyJump")
	public String godonkeyJump() {
		return "background/youxi/donkeyJump";
	}
}
