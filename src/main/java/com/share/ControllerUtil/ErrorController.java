package com.share.ControllerUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 *  自定义错误Controller
 *
 * @author 博博
 * @Title: ErrorController
 * @ProjectName SharedLibrary
 * @time 2018/12/14 11:02
 */
@Controller
public class ErrorController {

    @GetMapping("/404")
    public String notFound(){
        return  "404";
    }

    @GetMapping("/500")
    public String serverError(){
        return  "500";
    }

    @GetMapping("/403")
    public String notJurisdiction(){
        return  "403";
    }

}
