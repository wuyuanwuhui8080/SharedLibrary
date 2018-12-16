package com.share.shiro;

import com.jagregory.shiro.freemarker.UserTag;
import freemarker.template.SimpleHash;

/**
 *
 * shiro 整合freemark用的
 *
 * @author 博博
 * @Title: ShiroTags
 * @ProjectName SharedLibrary
 * @time 2018/12/13 21:15
 */
public class ShiroTags extends SimpleHash {
    public ShiroTags() {
        this.put("authenticated", new com.jagregory.shiro.freemarker.AuthenticatedTag());
        this.put("guest", new com.jagregory.shiro.freemarker.GuestTag());
        this.put("hasAnyRoles", new com.jagregory.shiro.freemarker.HasAnyRolesTag());
        this.put("hasPermission", new com.jagregory.shiro.freemarker.HasPermissionTag());
        this.put("hasRole", new com.jagregory.shiro.freemarker.HasRoleTag());
        this.put("lacksPermission", new com.jagregory.shiro.freemarker.LacksPermissionTag());
        this.put("lacksRole", new com.jagregory.shiro.freemarker.LacksRoleTag());
        this.put("notAuthenticated", new com.jagregory.shiro.freemarker.NotAuthenticatedTag());
        this.put("principal", new com.jagregory.shiro.freemarker.PrincipalTag());
        this.put("user", new UserTag());
    }
}
