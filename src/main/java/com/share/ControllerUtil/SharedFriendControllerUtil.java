package com.share.ControllerUtil;

import com.share.users.service.SharedFriendsService;
import com.share.users.service.SharedUsersService;
import com.share.util.JsonUtils;
import com.share.util.RedisUtil;
import com.share.util.StringUtils;
import com.share.vo.SharedUsersVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 好友表查询工具
 *
 * @author 博博
 * @Title: SharedFriendControllerUtil
 * @ProjectName SharedLibrary
 * @time 2018/12/19 17:51
 */
@Log4j2
@RestController
@RequestMapping("friendUtil")
public class SharedFriendControllerUtil {

    @Resource
    private SharedUsersService usersService;

    @Resource
    private SharedFriendsService friendsService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 用来帮助搜索建议的
     *
     * @param userId
     * @param name
     * @return
     */
    @GetMapping("/listFirendUsers")
    public String listFirendUsers(String userId,
                                  @RequestParam(value = "name", required = false) String name) {
        // 缓存key
        String likeFriendKey = "SHARED_FRIEND_USERSID" + userId;
        // 判断key是否存在
        if (redisUtil.hasKey(likeFriendKey)) {
            String sss = redisUtil.get(likeFriendKey).toString();
            System.out.println(redisUtil.get(likeFriendKey).toString());
            List<SharedUsersVO> findlist = (List<SharedUsersVO>) JsonUtils
                    .JSONList(redisUtil.get(likeFriendKey).toString(),
                            SharedUsersVO.class);
            if (StringUtils.isNotNull(name)) {
                // 不为空进行stream流操作 判断姓名或者用户名模糊查询
                return JsonUtils.JSONString(findlist.stream()
                        .filter(li -> li.getRealName().contains(name)
                                || li.getUserName().contains(name))
                        .collect(toList()));
            } else {
                // 第一次访问初始化
                return JsonUtils.JSONString(findlist);
            }
        }
        List<String> userListId = friendsService.getListUsersId(userId);
        String msg = JsonUtils.JSONString(usersService.findListByUsersIdForFriends(userListId));
        // 赛值进reds缓存中
        redisUtil.set(likeFriendKey, msg, 60);
        log.info(msg);
        return msg;
    }
}
