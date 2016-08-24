/**
 * LoginController.java
 * com.xingxunlei.wechat.controller.security
 *
 * Function： 登录后台管理系统Controller
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-23 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.controller.security;

import com.xingxunlei.wechat.commons.utils.ProjectUtil;
import com.xingxunlei.wechat.commons.utils.sign.Sha1Util;
import com.xingxunlei.wechat.model.cms.security.UserModel;
import com.xingxunlei.wechat.service.security.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:LoginController Function: 登录后台管理系统Controller
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-23 下午3:55:32
 * 
 * @see
 */
@Controller
public class LoginController {
    private Logger LOG = Logger.getLogger(LoginController.class);

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String returnUrl = request.getParameter("last");
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            LOG.error(String.format("%s is not authen", ProjectUtil.getAccessLogInfo(request)));
            return "login";
        }

        if (!StringUtils.isEmpty(returnUrl) && !StringUtils.equals("null", returnUrl)) {
            return "redirect:" + returnUrl;
        }

        return "redirect:/home.do";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public String login(UserModel userModel, HttpSession session, HttpServletRequest request) {
        String code = (String) session.getAttribute("validatecode");
        String submitCode = WebUtils.getCleanParam(request, "validatecode");

        // 获取日志信息
        StringBuilder logInfo = ProjectUtil.getLogInfo("");
        logInfo.append("IP：" + ProjectUtil.getIpAddr(request) + ";");

        // 判断用户名是否为空
        if (StringUtils.isEmpty(userModel.getLoginName())) {
            logInfo.append("用户：").append(userModel.getLoginName()).append(";用户名为空，登录失败");
            LOG.error(logInfo.toString());
            return "userError";
        }
        // 判断验证码是否正确
        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code, submitCode.toLowerCase())) {
            logInfo.append("用户：").append(userModel.getLoginName()).append(";验证码错误，登录失败");
            LOG.error(logInfo.toString());
            return "validateError";
        }

        UsernamePasswordToken token = null;
        Subject subject = SecurityUtils.getSubject();
        try {
            UserModel user = userService.getByAccount(userModel.getLoginName());
            if (user == null) {
                // 用户名错误
                logInfo.append("用户：").append(userModel.getLoginName()).append(";用户名错误，登录失败");
                LOG.error(logInfo.toString());
                return "userError";
            }

            // 用加密因子对密码进行加密
            Sha1Util sha1 = new Sha1Util();
            String hasPasswd = sha1.encryptBySalt(String.valueOf(userModel.getPassword()), user.getSalt());

            // 封装令牌
            token = new UsernamePasswordToken(userModel.getLoginName(), hasPasswd);
            token.setRememberMe(false);
            subject.login(token);

            // 获取用户信息
            UserModel principal = (UserModel) subject.getPrincipal();

            // 更新最新一次的登录时间
            userService.updateLoginTime(principal.getId());
            logInfo.append("用户：").append(userModel.getLoginName()).append("；口令登录成功");
            LOG.info(logInfo.toString());
            
            return "loginSuccess";
        } catch (AuthenticationException e) {
            token.clear();
            subject.logout();
            logInfo.append("用户：").append(userModel.getLoginName()).append("用户名或者密码错误，登录失败");
            LOG.error(logInfo.toString());
            return "userError";
        }

    }

    @RequestMapping(value = "/logout")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }

}
