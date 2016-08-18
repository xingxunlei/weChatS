/**
 * CookieUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： Cookie操作工具类 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-17 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie操作工具类
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-17		上午10:22:07
 *
 * @see 	 
 */
public class CookieUtil {
    
    /**
     * getCookie:获得Cookie值
     * 从cookie中取值
     * @param req     HttpServletRequest
     * @param name    Cookie Key
     * @return String Cookie Value
     */
    public static String getCookie(HttpServletRequest req, String name) {
        Cookie[] cks = req.getCookies();
        String value = null;
        if(cks != null) {
            for (int i = 0; i < cks.length; i++) {
                if(cks[i].getName().equals(name)) {
                    value = cks[i].getValue();
                    break;
                }
            }
        }
        value = XssUtil.cleanXSS(value);
        return value;
    }
    
    /**
     * getCookie:获得Cookie值
     * 先取url参数，若无则取cookie中的值
     * @param req          HttpServletRequest
     * @param name         Cookie Key
     * @param defaultValue Cookie defaultValue
     * @return String      Cookie Value
     */
    public static String getCookie(HttpServletRequest req, String name, String defaultValue) {
        String value = req.getParameter(name);
        if ("".equals(value) || value == null || value.length() == 0) {
            value = CookieUtil.getCookie(req, name);
        }
        if ("".equals(value) || value == null || value.length() == 0) {
            value = defaultValue;
        }
        value = XssUtil.cleanXSS(value);
        return value;
    }
    
    /**
     * setCookie:设置Cookie
     *
     * @param  resp   HttpServletResponse
     * @param  name   Cookie Key
     * @param  value  Cookie Value
     * @param  age    有效期(-1 浏览器关闭马上失效, 0 不失效, 正数多少秒后失效)
     */
    public static void setCookie(HttpServletResponse resp,String name,String value,int age) {
        setCookie(resp,  name, value, age, null, null);
    }
    
    /**
     * setCookie:设置Cookie
     *
     * @param  resp   HttpServletResponse
     * @param  name   Cookie Key
     * @param  value  Cookie Value
     * @param  age    有效期(-1 浏览器关闭马上失效, 0 不失效, 正数多少秒后失效)
     * @param  domain 作用域
     * @param  path   作用路径
     */
    public static void setCookie(HttpServletResponse resp,String name,String value,int age, String domain, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(age);
        if(domain != null) {
            cookie.setDomain(domain);
        }
        if(path != null) {
            cookie.setPath(path);
        } else {
            cookie.setPath("/");
        }
        resp.addCookie(cookie);
    }

}

