/**
 * CustomUserFilter.java
 * com.xingxunlei.wechat.filter.shiro
 *
 * Function： 自定义userFilter
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.filter.shiro;

import com.xingxunlei.wechat.commons.utils.ProjectUtil;
import com.xingxunlei.wechat.model.cms.security.UserModel;
import com.xingxunlei.wechat.service.security.UserService;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * ClassName:CustomUserFilter
 * Function: 自定义userFilter
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午5:51:10
 *
 * @see 	 
 */
public class CustomUserFilter extends UserFilter {
    private Logger LOG = Logger.getLogger(CustomUserFilter.class);
    
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        
        //获取用户凭证
        UserModel user = (UserModel)subject.getPrincipal();
        
        //判断是否认证 
        if(!subject.isAuthenticated() || user == null) {
            LOG.error(String.format("%s is not authen", ProjectUtil.getAccessLogInfo((HttpServletRequest)request)));
            return false;
        }
        
        //查看用户是否允许多处同时登录，若是 则允许同时登录，否则 踢出
        if(user.allowBothLogin()) {
            return super.isAccessAllowed(request, response, mappedValue);
        }
        
        //可在此做 是否允许同账户多处同时登录的校验
        UserModel user2 = userService.getByAccount(user.getUserName());
         
        //将session时间和数据库中的登录时间进行比较
        if(StringUtils.equals(user.getLoginTime().toString(), user2.getLoginTime().toString())) {
            subject.logout();
            return false;
        }
        
        return super.isAccessAllowed(request, response, mappedValue);
        
    }

}

