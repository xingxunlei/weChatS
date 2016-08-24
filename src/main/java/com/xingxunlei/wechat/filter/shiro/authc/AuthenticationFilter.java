/**
 * AuthenticationFilter.java
 * com.xingxunlei.wechat.filter.shiro.authc
 *
 * Function： 重写shiro's AuthenticationFilter
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.filter.shiro.authc;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.xingxunlei.wechat.filter.shiro.AccessControlFilter;

/**
 * ClassName:AuthenticationFilter
 * Function: 重写shiro's AuthenticationFilter
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午5:46:28
 *
 * @see 	 org.apache.shiro.web.filter.authc.AuthenticationFilter
 */
public abstract class AuthenticationFilter extends AccessControlFilter {

    public static final String DEFAULT_SUCCESS_URL = "/";

    private String successUrl = DEFAULT_SUCCESS_URL;

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }


    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
    }

}

