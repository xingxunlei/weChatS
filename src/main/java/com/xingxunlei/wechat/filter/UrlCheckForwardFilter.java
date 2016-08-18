/**
 * UrlCheckForwardFilter.java
 * com.xingxunlei.wechat.filter
 *
 * Function： Url过滤器
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-18 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.filter;

import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.Filter;

/**
 * ClassName:UrlCheckForwardFilter
 * Function: Url过滤器
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-18		下午1:02:57
 *
 * @see 	 
 */
public class UrlCheckForwardFilter implements Filter {
    private static Logger LOG = Logger.getLogger(UrlCheckForwardFilter.class);
    
    FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}

