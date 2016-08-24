/**
 * ControllerInterceptor.java
 * com.xingxunlei.wechat.interceptor
 *
 * Function： Controller访问log拦截器
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-23 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.interceptor;

import com.xingxunlei.wechat.commons.utils.ProjectUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ClassName:ControllerInterceptor 
 * Function: Controller访问log拦截器
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-23 下午4:24:37
 * 
 * @see
 */
public class ControllerInterceptor extends HandlerInterceptorAdapter {

    private static Logger LOG = Logger.getLogger(ControllerInterceptor.class);

    public ControllerInterceptor() {

    }

    /**
     * 在Controller之前调用，打印请求开始日志
     * (non-Javadoc)
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String p = request.getServletPath();
        if (p.toLowerCase().matches(".*?(\\.do)$")) {
            StringBuilder logInfo = ProjectUtil.getLogInfo("start:");
            logInfo.append("请求：" + p + ";");
            logInfo.append("IP：" + ProjectUtil.getIpAddr(request) + ";");
            LOG.info(logInfo.toString());
        }
        return true;
    }

    /**
     * 在Controller之前调用，打印请求结束日志
     * (non-Javadoc)
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mav) throws Exception {
        String p = request.getServletPath();
        if (p.toLowerCase().matches(".*?(\\.do)$")) {
            StringBuilder logInfo = ProjectUtil.getLogInfo("end:");
            logInfo.append("请求：" + p + ";");
            logInfo.append("IP：" + ProjectUtil.getIpAddr(request) + ";");
            LOG.info(logInfo.toString());
        }
    }

    /**
     * 整个请求完成之后调用，打印请求异常日志
     * (non-Javadoc)
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception excptn) throws Exception {
        String p = request.getServletPath();

        if (excptn != null) {
            if (p.toLowerCase().matches(".*?(\\.do)$")) {
                StringBuilder logInfo = ProjectUtil.getLogInfo("error:");
                logInfo.append("请求：" + p + "发生错误;");
                logInfo.append("IP：" + ProjectUtil.getIpAddr(request) + ";");
                LOG.error(logInfo.toString(), excptn);
            }
        }

    }

}
