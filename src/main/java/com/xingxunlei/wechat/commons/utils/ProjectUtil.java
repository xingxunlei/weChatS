/**
 * ProjectUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： 封装一些常用的方法
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-23 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.commons.utils;

import com.xingxunlei.wechat.model.cms.security.UserModel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * ClassName:ProjectUtil 
 * Function: 封装一些常用的方法
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-23 下午4:07:42
 * 
 * @see
 */
public class ProjectUtil {

    /**
     * getLogInfo:获取日志信息
     * 
     * @since  CodingExample　Ver 1.1
     */
    public static StringBuilder getLogInfo(String sign) {
        Thread current = Thread.currentThread();
        // 格式化线程号，%#010X--转换成固定长度为10位的16进制，不够长度补0， 前两位固定为0X表示是16进制
        // 格式如：0X00000064
        String threadId = String.format("%#010X", current.getId());
        StringBuilder st = new StringBuilder();
        st.append("线程号：" + threadId + ";").append(sign);
        return st;
    }

    /**
     * getAccessLogInfo:获取用户的访问信息
     * 
     * @since CodingExample　Ver 1.1
     */
    public static StringBuilder getAccessLogInfo(HttpServletRequest request) {
        StringBuilder st = new StringBuilder();
        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel) subject.getPrincipal();
        if (user != null) {
            st.append("[" + user.getLoginName() + "]").append("[").append(getIpAddr(request)).append("]").append("[").append(getBrower(request)).append("]");
        }
        return st;
    }

    /**
     * getIpAddr:获取用户ip地址
     * 
     * @since CodingExample　Ver 1.1
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        String pstr = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(pstr);
        Matcher m = p.matcher(ip);
        while (m.find()) {
            list.add(m.group(0));
        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return "unknown";
        }
    }

    /**
     * getBrower:获取浏览器信息
     * 
     * @since CodingExample　Ver 1.1
     */
    public static String getBrower(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        String brower = "";
        try {
            // 能匹配IE6~IE11，chrome，火狐浏览器
            Pattern p = Pattern
                    .compile("((?i)(msie) ([\\d.]+))|((?i)(trident)\\/([\\d.]+); (?i)(rv):([\\d.]+))|((?i)(chrome)\\/([\\d.]+))|((?i)(firefox)\\/([\\d.]+))");
            Matcher m = p.matcher(agent);
            if (m.find()) {
                brower = m.group().replaceAll("((?i)(msie))|((?i)(trident)\\/([\\d.]+); (?i)(rv))", "IE");
                return brower;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agent;
    }

}
