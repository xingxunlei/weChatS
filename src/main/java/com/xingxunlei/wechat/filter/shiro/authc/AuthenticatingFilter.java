/**
 * AuthenticatingFilter.java
 * com.xingxunlei.wechat.filter.shiro.authc
 *
 * Function： 重写shiro's AuthenticatingFilter
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-22 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.filter.shiro.authc;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**
 * ClassName:AuthenticatingFilter
 * Function: 重写shiro's AuthenticatingFilter
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-22		下午5:42:51
 *
 * @see 	 org.apache.shiro.web.filter.authc.AuthenticatingFilter
 */
public abstract class AuthenticatingFilter extends AuthenticationFilter {

    public static final String PERMISSIVE = "permissive";

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    protected abstract AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception;

    protected AuthenticationToken createToken(String username, String password,
                                              ServletRequest request, ServletResponse response) {
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return createToken(username, password, rememberMe, host);
    }

    protected AuthenticationToken createToken(String username, String password,
                                              boolean rememberMe, String host) {
        return new UsernamePasswordToken(username, password, rememberMe, host);
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        return false;
    }

    protected String getHost(ServletRequest request) {
        return request.getRemoteHost();
    }

    protected boolean isRememberMe(ServletRequest request) {
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue) ||
                (!isLoginRequest(request, response) && isPermissive(mappedValue));
    }

    protected boolean isPermissive(Object mappedValue) {
        if(mappedValue != null) {
            String[] values = (String[]) mappedValue;
            return Arrays.binarySearch(values, PERMISSIVE) >= 0;
        }
        return false;
    }

    @Override
    protected void cleanup(ServletRequest request, ServletResponse response, Exception existing) throws ServletException, IOException {
        if (existing instanceof UnauthenticatedException || (existing instanceof ServletException && existing.getCause() instanceof UnauthenticatedException))
        {
            try {
                onAccessDenied(request, response);
                existing = null;
            } catch (Exception e) {
                existing = e;
            }
        }
        super.cleanup(request, response, existing);

    }

}

