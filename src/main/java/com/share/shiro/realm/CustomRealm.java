package com.share.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.share.constant.PositionConstant;
import com.share.constant.PositionNameConstant;
import com.share.pojo.SharedUsers;
import com.share.users.service.SharedUsersService;

import lombok.extern.log4j.Log4j2;

/**
 * shiro 自定义 realm
 *
 * @author 博博
 * @Title: CustomRealm
 * @ProjectName SharedLibrary
 * @time 2018/12/13 15:58
 */
@Log4j2
public class CustomRealm extends AuthorizingRealm {


    @Resource
    private SharedUsersService usersService;


    /**
     * 执行认证操作
     *
     * @param authenticationToken
     * @return AuthorizationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("进入认证方法.....");
        // 从AuthenticationToken获取controller传入的UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        String userName = usernamePasswordToken.getUsername();

        // 执行查询
        SharedUsers users = usersService.getSharedUsersByUserName(userName);

        if (users == null) {
            throw new UnknownAccountException("用户名不存在!");
        } else {
            ByteSource source = ByteSource.Util.bytes(userName);
            // 执行认证
            log.info("开始执行认证.....");
            //密码传入的需要是users加密的
            return new SimpleAuthenticationInfo(users.getUserName(), users.getPassword(), source, getName());
        }
    }

    /**
     * 执行权限回调
     *
     * @param principalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进入授权方法.....");
        // 从当前登录对象中获取用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SharedUsers users = usersService.getSharedUsersByUserName(userName);
        Set<String> roles = new HashSet<>();
        if (users.getPositionId() == PositionConstant.NORMAL_USER) {
            roles.add(PositionNameConstant.NORMAL_NAME_USER);
        } else if (users.getPositionId() == PositionConstant.SWEEPING_USER) {
            roles.add(PositionNameConstant.SWEEPING_NAME_USER);
        } else if (users.getPositionId() == PositionConstant.ADMIN_USER) {
            roles.add(PositionNameConstant.ADMIN_NAME_USER);
        }
        log.info("授权完成！当前用户是{},权限是{}", userName, roles.toArray());
        return new SimpleAuthorizationInfo(roles);
    }
}
