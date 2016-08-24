/**
 * HomeController.java
 * com.xingxunlei.wechat.controller.security
 *
 * Function：HomeController
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-24 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.controller.security;

import com.xingxunlei.wechat.model.cms.security.UserModel;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.xingxunlei.wechat.commons.utils.ProjectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.stereotype.Controller;

import org.apache.log4j.Logger;

/**
 * ClassName:HomeController
 * Function: HomeController
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-24		下午2:22:26
 *
 * @see 	 
 */
@Controller
public class HomeController {
    private Logger LOG = Logger.getLogger(HomeController.class);
    
    @RequestMapping(value = "/home.do", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel) subject.getPrincipal();
        if(LOG.isInfoEnabled()) {
            LOG.info(String.format("%s 用户[%s] access home page", getLogInfo(request), user.getLoginName()));
        }
        return "home";
    }
    
    private static String getLogInfo(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb = ProjectUtil.getLogInfo("");
        sb.append("IP：" + ProjectUtil.getIpAddr(request) + ";");
        return sb.toString();
    }

}

