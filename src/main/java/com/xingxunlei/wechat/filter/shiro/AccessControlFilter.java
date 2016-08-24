/**
 * AccessControlFilter.java
 * com.xingxunlei.wechat.filter.shiro
 *
 * Function： 重写shiro's AccessControlFilter
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.filter.shiro;

import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * ClassName:AccessControlFilter
 * Function: 重写shiro's AccessControlFilter
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午5:40:04
 *
 * @see 	 org.apache.shiro.web.filter.AccessControlFilter
 */
public abstract class AccessControlFilter extends PathMatchingFilter {
    
    public static final String DEFAULT_LOGIN_URL = "/login.do";

    public static final String GET_METHOD = "GET";

    public static final String POST_METHOD = "POST";

    private String loginUrl = DEFAULT_LOGIN_URL;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        return SecurityUtils.getSubject();
    }

    protected abstract boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception;

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return onAccessDenied(request, response);
    }

    protected abstract boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception;

    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
    }

    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return pathsMatch(getLoginUrl(), request);
    }

    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        saveRequest(request);
        redirectToLogin(request, response);
    }
    
    protected void saveRequestAndRedirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        saveRequest(request);
        redirectToLogin(request, response);
    }

    protected void saveRequest(ServletRequest request) {
        WebUtils.saveRequest(request);
    }

    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String loginUrl = getLoginUrl();
        WebUtils.issueRedirect(request, response, loginUrl);
    }
    
    protected void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loginUrl = getLoginUrl();
        if(!StringUtils.isEmpty(request.getHeader("referer"))) {
            loginUrl = loginUrl + "?last=" + request.getHeader("referer");
        }
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}

