/**
 * AjaxReturnUtils.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： Ajax返回值工具类 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-11 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils.ajax;

import com.xingxunlei.wechat.commons.utils.ajax.json.JsonBigDecimalValueProcessor;
import com.xingxunlei.wechat.commons.utils.ajax.json.JsonDateValueProcessor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;

/**
 * Ajax返回值工具类
 * </br>提供json格式以及html格式返回值
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-11		下午1:23:40
 *
 * @see 	 
 */
public class AjaxReturnUtils {
    private static Logger LOG = Logger.getLogger(AjaxReturnUtils.class);
    
    public static <T> void jsonCallBack(JSONObject object, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        
        String json = object.toString();
        String callBack = request.getParameter("callback");
        if(callBack != null) {
            json = callBack + "(" + json + ")";
        }
        
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(json);
            out.flush();
        } catch (IOException e) {
            LOG.error("Exception happens when write json response back : ", e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
    
    public static void jsonpCallBack(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        jsonpCallBack(map,request,response,Date.class);
    }
    
    public static <T> void jsonpCallBack(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, Class<T> t) {
        response.setContentType("application/json;charset=utf-8");
        
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(t,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        
        String json = JSONObject.fromObject(map, jsonConfig).toString();
        String callBack = request.getParameter("callback");
        if(callBack != null) {
            json = callBack + "(" + json + ")";
        }
        
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(json);
            out.flush();
        } catch (IOException e) {
            LOG.error("Exception happens when write json response back : ", e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
    
    public static <T, B> void jsonpCallBack(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, Class<T> t, Class<B> b) {
        response.setContentType("application/json;charset=utf-8");
        
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(t,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        jsonConfig.registerJsonValueProcessor(b,new JsonBigDecimalValueProcessor());
        
        String json = JSONObject.fromObject(map, jsonConfig).toString();
        String callBack = request.getParameter("callback");
        if(callBack != null) {
            json = callBack + "(" + json + ")";
        }
        
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(json);
            out.flush();
        } catch (IOException e) {
            LOG.error("Exception happens when write json response back : ", e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
    
    public static void htmlCallBack(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        htmlCallBack(map,request,response,Date.class);
    }
    
    public static <T> void htmlCallBack(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, Class<T> t) {
        response.setContentType("text/html;charset=utf-8");
        
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(t,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        
        String json = JSONObject.fromObject(map, jsonConfig).toString();
        String callBack = request.getParameter("callback");
        if(callBack != null) {
            json = callBack + "(" + json + ")";
        }
        
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(json);
            out.flush();
        } catch (IOException e) {
            LOG.error("Exception happens when write json response back : ", e);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

}

