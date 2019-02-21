package com.share.forum.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.share.forum.vo.ForumCollection;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.share.ControllerUtil.CaptchaController;
import com.share.charsocket.bo.ForumMessage;
import com.share.charsocket.util.FriendAndMyIdKey;
import com.share.constant.PageConstant;
import com.share.forum.service.SharedForumCommentService;
import com.share.forum.service.SharedForumService;
import com.share.forum.service.SharedlClassifyService;
import com.share.forum.vo.ForumAndComment;
import com.share.forum.vo.SharedForumVO;
import com.share.pojo.SharedForum;
import com.share.pojo.SharedForumComment;
import com.share.pojo.SharedUsers;
import com.share.pojo.SharedlClassify;
import com.share.users.service.SharedFriendsService;
import com.share.users.service.SharedUsersService;
import com.share.util.*;
import com.share.vo.SharedUsersVO;

/**
 * <p>
 * 帖子前端控制器
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-17
 */
@Controller
@RequestMapping("/sharedForum")
public class SharedForumController {

	@Resource
	private SharedlClassifyService classifyService;
	@Resource
	private SharedForumService forumService;
	@Resource
	private SharedForumCommentService forumCommentService;
	@Resource
	private SharedFriendsService friendsService;
	@Resource
	private SharedUsersService usersService;

	@Resource
	private RedisUtil redisUtil;

	/**
	 * 初始化主页
	 *
	 * @param pageIndex
	 *            起始页
	 * @param model
	 * @return
	 */
	@GetMapping("/goIndex")
	public String goIndex(
			@RequestParam(value = "exitHost", required = false) Integer exitHost,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			Model model) {
		// 执行查询
		PageInfo<SharedForum> page = forumService.findList(null, pageIndex,
				PageConstant.PAGESIZE);
		// 查询回复最多的十二个人
		List<SharedForumComment> forumCommentList = forumCommentService
				.findCountCommentForUser();

		// 查询最近七天的热帖
		List<SharedForum> sevenDays = forumService.findWithinSevenDays();

		String forumStick = RedisKeys.getForumStick();
		List<SharedForum> listStipk = null;
		if (redisUtil.hasKey(forumStick)) {
			Map<Object, Object> objectMap = redisUtil.hmget(forumStick);
			Collection<Object> forumIds = objectMap.values();
			listStipk = forumService.findListStipk(forumIds);
		}
		// 把结果传递到页面
		model.addAttribute("forumCommentList", forumCommentList);
		model.addAttribute("exitHost",
				(exitHost != null && exitHost == 1) ? true : false);
		model.addAttribute("sevenDays", sevenDays);
		model.addAttribute("listStipk", listStipk);
		model.addAttribute("page", page);
		return "reception/index";
	}

	/**
	 * 看帖子详细
	 *
	 * @param id
	 *            帖子id
	 * @param model
	 * @return
	 */
	@GetMapping("/goForumDetailed/{id}")
	public String goForumDetailed(@PathVariable String id, Model model) {
		// 执行查询
		ForumAndComment forumAndComment = forumService.findListByForumId(id);
		SharedUsers sharedUsers = (SharedUsers) SecurityUtils.getSubject()
				.getSession().getAttribute("users");
		if (forumAndComment == null) {
			return "404";
		}
		String forumStick = RedisKeys.getForumStick();
		String stick = RedisKeys.forumStick(id);
		// 如果存在就是顶置
		if (redisUtil.hHasKey(forumStick, stick)) {
			forumAndComment.setExitSpick(true);
		}

		boolean collectionFalg = false;
		if(sharedUsers != null){
			// 获取map key
			String mapKey = RedisKeys.getCollectionMapKey(sharedUsers.getId());
			// 获取map 键
			String collection = RedisKeys.gteCollection(id);
			// 判断是否有收藏
			if(redisUtil.hHasKey(mapKey,collection)){
				collectionFalg = true;
			}else {
				collectionFalg = false;
			}
		}

		// 如果没有回复，就直接弄空，不然前台处问题
		if (forumAndComment.getCommentBOList().get(0).getCommentId() == null) {
			forumAndComment.setCommentBOList(null);
		}
		// 查询最近七天的热帖
		List<SharedForum> sevenDays = forumService.findWithinSevenDays();
		model.addAttribute("forumAndComment", forumAndComment);
		model.addAttribute("collectionFalg", collectionFalg);
		model.addAttribute("sevenDays", sevenDays);
		return "reception/jie/detail";
	}

	/**
	 * 跳转到编辑帖子的页面
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/goWriteForum")
	public String goWriteForum(Model model) {
		// 查询分类列表
		List<SharedlClassify> sharedlClassifyList = classifyService
				.findSharedlClassifyList();
		model.addAttribute("list", sharedlClassifyList);
		return "reception/jie/add";
	}

	/**
	 * 发帖操作
	 *
	 * @param sharedForum
	 *            传入的实体
	 * @return
	 */
	@PostMapping("/saveForum")
	@ResponseBody
	public ReturnResult saveForum(SharedForum sharedForum, String typeIds,
			String captcha) {
		Session session = SecurityUtils.getSubject().getSession();
		// session中的验证码
		String sessionCaptcha = (String) session
				.getAttribute(CaptchaController.KEY_CAPTCHA);
		if (null == captcha || !captcha.equalsIgnoreCase(sessionCaptcha)) {
			return ReturnResult.error("验证码错误！");
		}
		// 判断传入的type是否是空 如果不是空，就代表是公告之类的，就塞入值
		if (StringUtils.isNotNull(typeIds)) {
			sharedForum.setTypeId(Integer.parseInt(typeIds));
		}
		if (forumService.saveForum(sharedForum)) {
			return ReturnResult.ok(sharedForum.getId());
		}
		return ReturnResult.error("发布失败！");
	}

	/**
	 * 前台页面跳转个人主页
	 *
	 * @return
	 */
	@RequestMapping("/gohome/{userId}")
	public String gohome(@PathVariable String userId,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
			Model model) throws Exception {
		SharedUsers user = usersService.getUserById(userId);
		SharedUsersVO sharedUsersVO = new SharedUsersVO();
		CopyUtils.Copy(user, sharedUsersVO);
		List<SharedForum> foryms = forumService
				.findForymByUserId(userId, pageIndex).getList();
		Integer firendCount = 0;
		SharedUsers sharedUsers = (SharedUsers) SecurityUtils.getSubject()
				.getSession().getAttribute("users");
		if (sharedUsers != null) {
			firendCount = friendsService
					.getCountByMeIdAndFriendId(sharedUsers.getId(), userId);
		} else {
			firendCount = 0;
		}
		List<SharedForumComment> forumComments = forumCommentService
				.findForymCommentByUserID(userId, pageIndex);
		model.addAttribute("foryms", foryms);
		model.addAttribute("firendCount", firendCount);
		model.addAttribute("users", sharedUsersVO);
		model.addAttribute("forumComments", forumComments);
		return "reception/user/home";
	}

	/**
	 * 前台页面跳转个人主页
	 * 
	 * @return
	 */
	@RequestMapping("/goAthome/{userName}")
	public String goAthome(@PathVariable String userName,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
			Model model) throws Exception {
		SharedUsers name = usersService.getSharedUsersByUserName(userName);
		if (name == null) {
			return "404";
		}
		SharedUsers user = usersService.getUserById(name.getId());
		SharedUsersVO sharedUsersVO = new SharedUsersVO();
		CopyUtils.Copy(user, sharedUsersVO);
		Integer firendCount = 0;
		SharedUsers sharedUsers = (SharedUsers) SecurityUtils.getSubject()
				.getSession().getAttribute("users");
		if (sharedUsers != null) {
			firendCount = friendsService.getCountByMeIdAndFriendId(
					sharedUsers.getId(),
					usersService.getSharedUsersByUserName(userName).getId());
		} else {
			firendCount = 0;
		}
		List<SharedForum> foryms = forumService
				.findForymByUserId(name.getId(), pageIndex).getList();
		List<SharedForumComment> forumComments = forumCommentService
				.findForymCommentByUserID(name.getId(), pageIndex);
		model.addAttribute("foryms", foryms);
		model.addAttribute("users", sharedUsersVO);
		model.addAttribute("firendCount", firendCount);
		model.addAttribute("forumComments", forumComments);
		return "reception/user/home";
	}

	/**
	 * 删除帖子
	 *
	 * @param forumId
	 *            帖子id
	 * @return
	 */
	@PostMapping("/deleteForum/{forumId}")
	@ResponseBody
	public ReturnResult deleteForum(@PathVariable String forumId) {
		if (forumService.deleteForum(forumId)) {
			return ReturnResult.ok();
		}
		return ReturnResult.error("删除失败！");
	}

	/**
	 * 转我的帖子页面
	 *
	 * @return
	 */
	@RequestMapping("/goUserIndex")
	public String goUserIndex(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
			Model model) {
		SharedUsers user = (SharedUsers) SecurityUtils.getSubject().getSession()
				.getAttribute("users");
		PageInfo<SharedForum> page = forumService
				.findForymByUserId(user.getId(), pageIndex);
		String collection = RedisKeys.getCollectionMapKey(user.getId());
		Map<Object, Object> objectMap = null;
		if (redisUtil.hasKey(collection)) {
			objectMap = redisUtil.hmget(collection);
		}
		model.addAttribute("page", page);
		model.addAttribute("listSize", (objectMap == null || objectMap.equals("") ? 0 : objectMap.size()));
		model.addAttribute("list",
				objectMap == null ? "{}"
						: JSON.toJSONString(JsonUtils.JSONList(
								objectMap.values().toString(),
								ForumCollection.class)));
		return "reception/user/index";
	}

	/**
	 * 转我的消息页面
	 *
	 * @return
	 */
	@RequestMapping("/goMessage")
	public String goMessage(Model model) {
		SharedUsers users = (SharedUsers) SecurityUtils.getSubject()
				.getSession().getAttribute("users");
		String userKey = FriendAndMyIdKey.ForumKey(users.getUserName());
		boolean key = redisUtil.hasKey(userKey);
		List<ForumMessage> list = null;
		if (key) {
			String event = redisUtil.sGet(userKey).toString();
			list = (List<ForumMessage>) JsonUtils.JSONList(event,
					ForumMessage.class);
		}
		model.addAttribute("list", list);
		return "reception/user/message";
	}

	/**
	 * 根据传入的条件查询，用于搜索引擎
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/findListSarch")
	@ResponseBody
	public List<SharedForumVO> findListSarch(String name) {
		List<SharedForumVO> list = forumService.findListByName(name,
				PageConstant.PAGEINDEX, PageConstant.FORUMPAGESIZE);
		return list;
	}

	/**
	 * 删除单个消息
	 * 
	 * @param msg
	 *            消息体
	 * @param date
	 *            消息时间
	 * @return
	 */
	@PostMapping("/deleteMessage/{msg}/{date}/{userName}")
	@ResponseBody
	public ReturnResult deleteMessage(@PathVariable String msg,
			@PathVariable String date, @PathVariable String userName) {
		// 初始化删除数据
		ForumMessage forumMessage = new ForumMessage(msg, date);
		String key = FriendAndMyIdKey.ForumKey(userName);
		Object toJSON = JSONObject.toJSON(forumMessage);
		if (redisUtil.sHasKey(key, toJSON)) {
			long remove = redisUtil.setRemove(key, toJSON);
			if (remove > 0) {
				return ReturnResult.ok();
			}
		} else {
			return ReturnResult.error("当前消息似乎不存在了..");
		}
		return ReturnResult.error("删除失败！");
	}

	/**
	 * 删除全部消息
	 * 
	 * @param userName
	 * @return
	 */
	@PostMapping("/deleteAll/{userName}")
	@ResponseBody
	public ReturnResult deleteAll(@PathVariable String userName) {
		String key = FriendAndMyIdKey.ForumKey(userName);
		if (redisUtil.hasKey(key)) {
			if (redisUtil.del(key)) {
				return ReturnResult.ok();
			} else {
				return ReturnResult.error("删除失败！");
			}
		} else {
			return ReturnResult.error("暂时没有消息可以清除.");
		}
	}

	/**
	 * 添加置顶操作
	 * 
	 * @param forumId
	 *            帖子id
	 * @return
	 */
	@PostMapping("/doStick")
	@ResponseBody
	public ReturnResult doStick(String forumId) {
		// 获取hash表的名称
		String forumStick = RedisKeys.getForumStick();
		// 拼接key
		String stick = RedisKeys.forumStick(forumId);
		// 最大置顶
		int maxStick = 4;
		Map<Object, Object> objectMap = redisUtil.hmget(forumStick);
		if (objectMap.size() >= maxStick) {
			return ReturnResult.error("最多只能四个置顶!");
		}
		if (redisUtil.hset(forumStick, stick, forumId)) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error("添加置顶失败！");
		}
	}

	/**
	 * 取消置顶
	 * 
	 * @param forumId
	 *            帖子id
	 * @return
	 */
	@PostMapping("/cancelStick")
	@ResponseBody
	public ReturnResult cancelStick(String forumId) {
		// 获取hash表的名称
		String forumStick = RedisKeys.getForumStick();
		// 拼接key
		String stick = RedisKeys.forumStick(forumId);
		if (redisUtil.hHasKey(forumStick, stick)) {
			// 执行删除
			redisUtil.hdel(forumStick, stick);
			return ReturnResult.ok();
		} else {
			return ReturnResult.error("该条置顶已经被别人取消了!");
		}
	}

	/**
	 * 点击查看更多，跳转到更多页面
	 * 
	 * @param pageIndex
	 *            起始页
	 * @param model
	 * @return
	 */
	@GetMapping("/toListForum")
	public String toListForum(
			@RequestParam(value = "exitHost", required = false) Integer exitHost,
			@RequestParam(value = "typeId", required = false, defaultValue = "0") Integer typeId,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			Model model) {
		PageInfo<SharedForum> page = forumService.findList(typeId, pageIndex,
				PageConstant.PAGESIZE);
		// 查询最近七天的热帖
		List<SharedForum> sevenDays = forumService.findWithinSevenDays();
		model.addAttribute("page", page);
		model.addAttribute("typeId", typeId);
		model.addAttribute("sevenDays", sevenDays);
		model.addAttribute("exitHostNmb", exitHost);
		model.addAttribute("exitHost",
				(exitHost != null && exitHost == 1) ? true : false);
		return "reception/jie/index";
	}

	/**
	 * 确认收藏
	 * 
	 * @param forumId
	 *            帖子id
	 * @param userId
	 *            用户id
	 * @return
	 */
	@PostMapping("/confirmTheCollection")
	@ResponseBody
	public ReturnResult confirmTheCollection(String forumId,
			HttpServletRequest request, String userId, String title) {
		// 获取map 键key
		String collectionKey = RedisKeys.gteCollection(forumId);
		String mapKey = RedisKeys.getCollectionMapKey(userId);
		// 组装数据
		ForumCollection forumCollection = new ForumCollection(title,
				DateUtils.date2String(new Date()), forumId);
		if (redisUtil.hset(mapKey, collectionKey,
				JSON.toJSON(forumCollection))) {
			return ReturnResult.ok();
		}
		return ReturnResult.error("收藏失败！");
	}

	/**
	 * 取消收藏
	 * 
	 * @param forumId
	 *            帖子id
	 * @return
	 */
	@PostMapping("/cancelTheCollection")
	@ResponseBody
	public ReturnResult cancelTheCollection(String forumId) {
		Session session = SecurityUtils.getSubject().getSession();
		SharedUsers users = (SharedUsers) session.getAttribute("users");
		// 获取map key
		String mapKey = RedisKeys.getCollectionMapKey(users.getId());
		// 获取map 键
		String collection = RedisKeys.gteCollection(forumId);
		// 判断是否存在
		if(redisUtil.hHasKey(mapKey,collection)){
			redisUtil.hdel(mapKey,collection);
			return ReturnResult.ok();
		}else{
			return ReturnResult.error("该收藏似乎已经被取消了！");
		}
	}


}
