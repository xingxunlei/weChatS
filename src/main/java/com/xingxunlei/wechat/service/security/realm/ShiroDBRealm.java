/**
 * ShiroDBRealm.java
 * com.xingxunlei.wechat.service.security.realm
 *
 * Function： ShiroDBRealm
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-23 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.service.security.realm;

import com.xingxunlei.wechat.model.cms.security.UserModel;
import com.xingxunlei.wechat.service.security.RoleService;
import com.xingxunlei.wechat.service.security.UserService;
import java.util.Date;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * ClassName:ShiroDBRealm 
 * Function: shiro 域
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-23 上午10:30:15
 * 
 * @see
 */
public class ShiroDBRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("roleServiceImpl")
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserModel user = (UserModel) super.getAvailablePrincipal(principals);
        List<String> permissions = null;

        if (user == null) {
            throw new AuthorizationException();
        }

        // 加载用户权限
        permissions = roleService.getHasPermissByUserID(user.getId());

        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 根据账号获取用户信息
        UserModel user = userService.getByAccount(token.getUsername());

        if (user == null) {
            return null;
        }

        // 设置登录时间
        user.setLoginTime(new Date());

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());

    }

}
