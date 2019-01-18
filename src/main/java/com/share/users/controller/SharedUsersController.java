package com.share.users.controller;

import java.io.FileNotFoundException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.share.recent_events.Event;
import com.share.recent_events.Recent_Events;
import com.share.constant.PageConstant;
import com.share.constant.PositionConstant;
import com.share.util.*;
import com.share.vo.SharedUsersJSONVO;
import com.share.vo.SharedUsersVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.share.ControllerUtil.CaptchaController;
import com.share.blogs.service.ShareBlogsService;
import com.share.constant.HTTPStutusConstant;
import com.share.pojo.ShareBlogs;
import com.share.pojo.SharedUsers;
import com.share.pojo.SharedlPosition;
import com.share.users.service.SharedAttentionService;
import com.share.users.service.SharedFansService;
import com.share.users.service.SharedUsersService;
import com.share.users.service.SharedlPositionService;

import lombok.extern.log4j.Log4j2;

/**
 * 对用户页面进行数据传输
 *
 * @author 博博大人
 * @time 2018/12/13 8:47
 */
@Log4j2
@Controller
@RequestMapping("/sharedUsers")
public class SharedUsersController {

    @Resource
    private SharedUsersService usersService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SharedFansService fansService;

    @Resource
    private SharedAttentionService attentionService;

    @Resource
    private ShareBlogsService blogsService;

    @Resource
    private SharedlPositionService positionServicel;

    @GetMapping("/adminUser")
    public String index() {
        return "background/index";
    }

    /**
     * 重定向到主页
     *
     * @return
     */
    @GetMapping("/goIndex")
    public String goIndex() {
        return "redirect:/sharedUsers/adminUser";
    }

    /**
     * 执行登录操作
     *
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    public ReturnResult doLogin(String userName, String password,
                                String captcha, HttpSession session) {
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 校验验证码
        // session中的验证码
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession()
                .getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            return ReturnResult.error("验证码错误！");
        }
        // 把账号密码塞入UsernamePasswordToken里
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                userName, password);
        // 允许缓存
        // usernamePasswordToken.setRememberMe(true);

        try {
            // 执行登录
            subject.login(usernamePasswordToken);
            // 查看是否认证成功 成功 true 否则false
            if (subject.isAuthenticated()) {
                SharedUsers users = usersService
                        .getSharedUsersByUserName(userName);
                session.setAttribute("users", users);
                return ReturnResult.ok();
            } else {
                return ReturnResult.error("登录失败！");
            }
        } catch (IncorrectCredentialsException e) {
            return ReturnResult.error("账号或者密码错误！");
        } catch (UnknownAccountException e) {
            return ReturnResult.error(e.getMessage());
        }
    }

    /**
     * 跳转到登录页
     *
     * @return String
     */
    @GetMapping("/goLogin")
    public String goLogin() {
        return "background/users/login";
    }

    /**
     * 跳转到注册页
     *
     * @param users
     * @return String
     */
    @GetMapping("/goRegister")
    public String goRegister(@ModelAttribute("users") SharedUsers users) {
        return "background/users/register";
    }

    /**
     * 注册用户
     *
     * @param users 用户实体
     * @return
     */
    @PostMapping("/saveNorsalUsers")
    @ResponseBody
    public ReturnResult saveNorsalUsers(SharedUsers users) {
        if (usersService.saveNorsalSharedUsers(users)) {
            return ReturnResult.ok();
        }
        return ReturnResult.error("添加失败！");
    }

    /**
     * 校验用户名是否重复
     *
     * @param userName
     * @return
     */
    @GetMapping("/getUserName")
    @ResponseBody
    public ReturnResult getUserName(String userName) {
        if (usersService.getSharedUsersGetUserName(userName)) {
            return ReturnResult.error();
        } else {
            return ReturnResult.ok();
        }
    }

    /**
     * 根据用户名或者真实姓名判断
     *
     * @param name
     * @return
     */
    @GetMapping("/getUserNamorRealName")
    @ResponseBody
    public ReturnResult getUserNamorRealName(String name) {
        if (usersService.getUserByUserNameOrRealName(name)) {
            return ReturnResult.error();
        } else {
            return ReturnResult.ok();
        }
    }

    /**
     * 根据条件查询 有则查询条件， 无则查询所有
     *
     * @author cll 马汇博
     * @time 2018/12/15 15:49
     */
    @GetMapping("/goUserList")
    public String goUserList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "position", required = false) Integer position,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            Model model) {
        // 查询所有职位
        List<SharedlPosition> positionList = positionServicel.findList();

        // 查询用户
        PageInfo<SharedUsersJSONVO> page = usersService
                .findUsersListByUserNameOrRealName(name, position, pageIndex,
                        PageConstant.PAGESIZE);
        model.addAttribute("page", page);
        model.addAttribute("positionList", positionList);
        model.addAttribute("position", position);
        model.addAttribute("name", name);
        return "background/users/userList";
    }

    /**
     * 用来json查询的方法
     *
     * @param name      用户名或者真实姓名
     * @param position  职位
     * @param pageIndex 起始页
     * @param model
     * @return
     */
    @GetMapping("/jsonUserList")
    @ResponseBody
    public ReturnResult jsonUserList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "position", required = false) Integer position,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
            Model model) {
        // 查询用户
        PageInfo<SharedUsersJSONVO> page = usersService
                .findUsersListByUserNameOrRealName(name, position, pageIndex,
                        PageConstant.PAGESIZE);
        Map<String, Object> map = new HashMap<>();
        // 回显数据
        map.put("page", page);
        map.put("name", name);
        map.put("position", position);
        map.put("pageIndex", pageIndex);
        return ReturnResult.ok(map);
    }

    /**
     * 查看用户资料
     *
     * @return
     */
    @GetMapping("/lookProfile/{usersId}")
    public String lookProfile(@PathVariable String usersId, Model model) {
        // 查询多少个粉丝
        Integer getFensCuont = fansService.getFensCount(usersId);
        // 查询多少个关注的
        Integer getAttention = attentionService.getUsersIdAttention(usersId);
        // 查询发了多少个博客
        Integer getBlogs = blogsService.getCountForBlogsByUsersId(usersId);
        // 查询博客
        List<ShareBlogs> blogsList = blogsService
                .findListFriendsByUsersId(usersId);
        SharedUsers users = usersService.getUserById(usersId);

        // 数据统一发送到页面
        model.addAttribute("getFensCuont", getFensCuont);
        model.addAttribute("blogsList", blogsList);
        model.addAttribute("users", users);
        model.addAttribute("getBlogs", getBlogs);
        model.addAttribute("getAttention", getAttention);
        return "background/users/profile";
    }

    /**
     * 执行用户注销操作 注销成功后重定向到登录页面
     *
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/sharediForum/goIndex";
    }

    /**
     * 跳转主页面
     *
     * @param model 页面传输
     * @return 视图
     */
    @GetMapping("/GoIndex")
    public String GoIndex(Model model) {
        //获取登录用户
        SharedUsers users = (SharedUsers) SecurityUtils.getSubject().getSession().getAttribute("users");
        String event = redisUtil.sGet(users.getUserName()).toString();
        List<Event> list = (List<Event>) JsonUtils.JSONList(event, Event.class);
        model.addAttribute("events", list);
        return "background/users/timeline";
    }


    /**
     * 转到修改头像的页面
     *
     * @return
     */
    @GetMapping("/goUpload")
    public String goUpload() {
        return "background/users/user_headImg";
    }

    /**
     * 提交修改头像的操作
     *
     * @param userId 当前用户id
     * @param image  传入的base64
     * @return
     */
    @PostMapping("/uploadHeadImg")
    @ResponseBody
    public ReturnResult uploadHeadImg(String userId, String userImg,
                                      String image, HttpSession session) throws FileNotFoundException {
        // 把base64 字符串转换成MultipartFile
        MultipartFile file = BASE64DecodedMultipartFile
                .base64ToMultipart(image);
        // 执行上传操作
        ReturnResult returnResult = FileUtil.fileUpload(file, userImg);
        if (returnResult.getStatus() != HTTPStutusConstant.SUCCESS_STRUTS) {
            return returnResult;
        } else {
            // 实例化users实体并把id和头像名称塞入
            SharedUsers users = new SharedUsers();
            users.setId(userId);
            users.setHeadImg(returnResult.getList().get(0).toString());
            if (usersService.updateUserHeadImg(users)) {
                // 移除session的users值
                session.removeAttribute("users");
                // 重新把最新的塞入
                SharedUsers users1 = usersService.getById(userId);
                session.setAttribute("users", users1);
                return ReturnResult
                        .okAndList(Arrays.asList(users1.getHeadImg()));
            } else {
                return ReturnResult.error("修改失败!");
            }
        }
    }

    /**
     * 跳转修改页面
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/goUpdateUsers")
    public String goUpdateUsers(String userId, Model model) {
        // 根据id查询用户信息
        SharedUsers users = usersService.getUserById(userId);
        model.addAttribute("users", users);
        return "background/users/user_modifier";
    }

    /**
     * 修改个人资料
     *
     * @param users
     * @return
     */
    @PostMapping("/updateUsers")
    @ResponseBody
    public ReturnResult updateUsers(SharedUsers users, String captcha) {
        // session中的验证码
        String sessionCaptcha = (String) SecurityUtils.getSubject().getSession()
                .getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            return ReturnResult.error("验证码错误！");
        }
        if (usersService.updateUsers(users)) {
            Session session = SecurityUtils.getSubject().getSession();
            SharedUsers users1 = (SharedUsers) session.getAttribute("users");
            if (users1 != null) {
                session.removeAttribute("users");
            }
            SharedUsers users2 = usersService.getUserById(users.getId());
            session.setAttribute("users", users2);
            return ReturnResult.ok();
        }
        return ReturnResult.error("修改失败");
    }

    /**
     * 跳转到旧密码页面
     *
     * @param userId
     * @return
     */
    @GetMapping("/goOldPassword/{userId}")
    public String goOldPassword(String userId) {
        return "background/users/oldPassword";
    }

    /**
     * 校验输入的老密码是否和当前登录用户的密码一致
     *
     * @param password
     * @param userName
     * @return
     */
    @PostMapping("/verifyOdlPaassword/{password}/{userName}/{captcha}")
    @ResponseBody
    public ReturnResult verifyOdlPaassword(@PathVariable String password,
                                           @PathVariable String userName, @PathVariable String captcha) {
        Session session = SecurityUtils.getSubject().getSession();
        // session中的验证码
        String sessionCaptcha = (String) session
                .getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            return ReturnResult.error("验证码错误！");
        }
        // 把传入的用户密码和用户名用md5加密
        String pwd1 = ShiroMd5.hashMd5(userName, password);
        SharedUsers users = (SharedUsers) session.getAttribute("users");
        if (pwd1.equals(users.getPassword())) {
            return ReturnResult.ok(users.getId());
        }
        return ReturnResult.error("旧密码输入错误!");
    }

    /**
     * 修改新密码
     *
     * @param users   传入的实体
     * @param captcha 验证码
     * @return
     */
    @PostMapping("/updateNewPassword")
    @ResponseBody
    public ReturnResult updateNewPassword(SharedUsers users, String captcha) {
        Session session = SecurityUtils.getSubject().getSession();
        // session中的验证码
        String sessionCaptcha = (String) session
                .getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            return ReturnResult.error("验证码错误！");
        }
        String pwd = ShiroMd5.hashMd5(users.getUserName(), users.getPassword());
        users.setPassword(pwd);
        if (usersService.updateUserPassword(users)) {
            return ReturnResult.ok();
        }
        return ReturnResult.error("修改失败！");
    }

    /**
     * 查看用户详细
     *
     * @param userid
     * @return
     */
    @GetMapping("/showUsers/{userid}")
    public String showUsers(@PathVariable String userid, Model model)
            throws Exception {
        // 根据id查询用户信息
        SharedUsers users = usersService.getUserById(userid);
        SharedUsersVO sharedUsersVO = new SharedUsersVO();
        CopyUtils.Copy(users, sharedUsersVO);
        model.addAttribute("user", sharedUsersVO);
        return "background/users/show_user";
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @PostMapping("/deleteUsers/{userId}")
    @ResponseBody
    public ReturnResult deleteUsers(@PathVariable String userId) {
        // 先根据用户id查询这个用户的状态
        SharedUsers users = usersService.getUserById(userId);
        // 判断当前删除的用户是不是管理员，如果是管理员，提示不能删除
        if (users.getPositionId() == PositionConstant.ADMIN_USER) {
            return ReturnResult.error("您没有权限删除管理员用户！");
            // 不是管理员
        } else {
            // 判断封号次数是不是大于三
            if (users.getStopNum() <= 3) {
                return ReturnResult.error("该用户封号次数没有大于3次不能删除！");
                // 大于三 可以删除
            } else {
                if (usersService.deleteUsers(userId)) {
                    return ReturnResult.ok();
                }
            }
        }
        return ReturnResult.error("删除失败！");
    }

    /**
     * 跳转到修改页面
     *
     * @param userId 用户id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/goUpdate/{userId}")
    public String goUpdate(@PathVariable("userId") String userId, Model model)
            throws Exception {
        SharedUsers sharedUsers = usersService.getUserById(userId);
        SharedUsersVO usersVO = new SharedUsersVO();
        CopyUtils.Copy(sharedUsers, usersVO);
        model.addAttribute("users", usersVO);
        return "background/users/user_list_modifier";
    }

    /**
     * 修改用户资料
     *
     * @param sharedUsers 传入对象
     * @return
     */
    @PostMapping("/updateUser")
    @ResponseBody
    public ReturnResult updateUser(SharedUsers sharedUsers, String captcha) {
        Session session = SecurityUtils.getSubject().getSession();
        // session中的验证码
        String sessionCaptcha = (String) session
                .getAttribute(CaptchaController.KEY_CAPTCHA);
        if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            return ReturnResult.error("验证码错误！");
        }
        // 判断传入的用户是不是管理员
        if (sharedUsers.getPositionId() == PositionConstant.ADMIN_USER) {
            return ReturnResult.error("您没有权限更改管理员的资料");
        } else {
            sharedUsers.setUserName(null);
            if (usersService.updateUsers(sharedUsers)) {
                return ReturnResult.ok();
            }
        }
        return ReturnResult.error("更新错误！");
    }

    @GetMapping("/goSaveUser")
    public String goSaveUser() {
        return "background/users/add_users";
    }

}
