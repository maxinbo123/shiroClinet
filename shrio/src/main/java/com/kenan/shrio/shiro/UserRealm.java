package com.kenan.shrio.shiro;

import com.google.common.collect.Sets;
import com.kenan.shrio.entity.UserData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

        log.info(">>>>>>>>>>>  shrio, 权限信息查询");
        UserData userInfo  = (UserData)arg0.getPrimaryPrincipal();
        // 角色列表
        Set<String> roles = Sets.newHashSet();
        // 功能列表
        Set<String> menus = Sets.newHashSet();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if ("1".equals(userInfo.getType())) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            roles.add("opt");
            info.setRoles(roles);
            menus.add("add");
            menus.add("query");
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info(">>>>>>>>>>>  shrio, 登录认证");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        UserData user = null;
        if("123456".equals(password)){
            user = new UserData();
            user.setName(username);
            user.setPass(password);
            if("admin".equals(username)){
                user.setType("1");
            }else {
                user.setType("2");
            }
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
