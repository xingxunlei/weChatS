/**
 * XssUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： XSS脚本过滤 工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-11 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils;
import java.util.regex.Pattern;

/**
 * XSS脚本过滤 工具类
 *
 * @author   Simon
 * @since    Ver 1.1
 * @Date     2016-1-29      上午11:54:01
 *
 * @see      
 */
public class XssUtil {
    
    /**
     * 清除特殊字符
     */
    public static String cleanXSS(String s) {
        if (s == null || "".equals(s) || s.isEmpty()) {
            return s;
        }
        s = stripCRLF(s);
        if (s == null || "".equals(s) || s.isEmpty()) {
            return s;
        }
        s = xssStrip(s);
        if (s == null || "".equals(s) || s.isEmpty()) {
            return s;
        }
        s = xssEncode(s);
        return s;
    }
    
    /**
     * 移除CRLF(回车、换行符)
     */
    public static String stripCRLF(String s){
        while(true){
            if (//(s.indexOf("CR")==-1)&&
                    (s.indexOf("%0d")==-1)&&
                    (s.indexOf("\r")==-1)&&
                    //(s.indexOf("LF")==-1)&&
                    (s.indexOf("%0a")==-1)&&
                    (s.indexOf("\n")==-1)){
                break;
            }
//            if (s.indexOf("CR")!=-1){
//                s = s.replaceAll("CR", "");
//            }
            if (s.indexOf("%0d")!=-1){
                s = s.replaceAll("%0d", "");
            }
            if (s.indexOf("\r")!=-1){
                s = s.replaceAll("\r", "");
            }
//            if (s.indexOf("LF")!=-1){
//                s = s.replaceAll("LF", "");
//            }
            if (s.indexOf("%0a")!=-1){
                s = s.replaceAll("%0a", "");
            }
            if (s.indexOf("\n")!=-1){
                s = s.replaceAll("\n", "");
            }
        }
      return s.toString();
    }
    
    /**
     * 移除html标签
     */
    public static String xssStrip(String s) {
        if (s != null) {
            s = s.replaceAll("", "");
            Pattern pattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("<iframe>(.*?)</iframe>", Pattern.CASE_INSENSITIVE);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("</iframe>", Pattern.CASE_INSENSITIVE);
            s = pattern.matcher(s).replaceAll("");
            pattern = Pattern.compile("<iframe(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            s = pattern.matcher(s).replaceAll("");
        }
        return s;
    }
    
    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     */
    public static String xssEncode(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '>':
                sb.append('＞');// 全角大于号
                break;
            case '<':
                sb.append('＜');// 全角小于号
                break;
            case '\'':
                sb.append('‘');// 全角单引号
                break;
            case '\"':
                sb.append('“');// 全角双引号
                break;
            case '\\':
                sb.append('＼');// 全角斜线
                break;
            default:
                sb.append(c);
                break;
            }
        }
        return sb.toString();
    }

}