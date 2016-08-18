/**
 * UrlUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： Url常用工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-18 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * Url常用工具类
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-18		下午1:07:24
 *
 * @see 	 
 */
public class UrlUtil {
    private static Logger LOG = Logger.getLogger(UrlUtil.class);
    
    /**
     * parseSuffix:获取URL的后缀名
     *
     * @param  url   url地址
     * @return String url后缀名
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String parseSuffix(String url){
        Pattern pattern = Pattern.compile("\\S*[?]\\S*");
        Matcher matcher = pattern.matcher(url);
        
        String[] spUrl = url.split("/");
        String endUrl = spUrl[spUrl.length - 1];
        
        if(matcher.find()) {
            String[] spEndUrl = endUrl.split("\\?");
            return spEndUrl[0].split("\\.")[1];
        }
        
        return endUrl.split("\\.")[1];
    }
    
    /**
     * urlEncode:Url编码,默认utf-8编码
     *
     * @param  url 要进行编码的url地址
     * @return String    编码后的地址
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String urlEncode(String url){
        return urlEncode(url, "utf-8");
    }
    
    /**
     * urlEncode:Url编码
     *
     * @param  url 要进行编码的url地址
     * @param  code 字符编码集名称
     * @return String    编码后的地址
     * @throws 编码异常时返回值为null
     * @since  CodingExample　Ver 1.1
     */
    public static String urlEncode(String url, String code){
        try {
            return URLEncoder.encode(url, code);
        } catch (UnsupportedEncodingException e) {
            LOG.error("urlEncode failed," + e);
            return null;
        }
    }
    
    /**
     * urlDecode:Url解码,默认utf-8编码
     *
     * @param  url 要解码的url
     * @return String  解码后的url
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String urlDecode(String url){
        return urlDecode(url, "utf-8");
    }
    
    /**
     * urlDecode:Url解码
     *
     * @param  url 要解码的url
     * @param  code 字符编码集名称
     * @return String    解码后的url
     * @throws 解码异常时返回值为null
     * @since  CodingExample　Ver 1.1
     */
    public static String urlDecode(String url, String code){
        try {
            return URLDecoder.decode(url, code);
        } catch (UnsupportedEncodingException e) {
            LOG.error("urlDecode failed," + e);
            return null;
        }
    }

}

