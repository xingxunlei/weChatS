/**
 * HttpUtil.java
 * com.xingxunlei.wechat.commons.utils
 *
 * Function： 模拟http请求工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-18 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * <p>
 * ClassName:HttpUtil</br>
 * Function: 模拟http请求工具类</br>
 * <p>
 * 为系统提供通用Http访问操作方法：</br> 1、发送GET请求</br> 2、发送POST请求
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-18 下午7:07:05
 * 
 * @see
 */
public class HttpUtil {
    private static Logger LOG = Logger.getLogger(HttpUtil.class);

    /**
     * getByte:发送get请求
     * 
     * @param url
     *            请求url
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] getByte(String url) {
        return getByte(url, null, null, 0);
    }

    /**
     * getByte:发送get请求
     * 
     * @param url
     *            请求url
     * @param headerMap
     *            请求头参数
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] getByte(String url, Map<String, String> headerMap) {
        return HttpUtil.getByte(url, headerMap, null, 0);
    }

    /**
     * getByte:发送get请求
     * 
     * @param url
     *            请求url
     * @param proxyUrl
     *            代理服务器地址
     * @param proxyPort
     *            代理服务器端口
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] getByte(String url, String proxyUrl, int proxyPort) {
        return HttpUtil.getByte(url, null, proxyUrl, proxyPort);
    }

    /**
     * getString:发送get请求
     * 
     * @param url
     *            请求url
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String getString(String url) {
        return new String(getByte(url, null, null, 0));
    }

    /**
     * getString:发送get请求
     * 
     * @param url
     *            请求url
     * @param headerMap
     *            请求头参数
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String getString(String url, Map<String, String> headerMap) {
        return new String(getByte(url, headerMap, null, 0));
    }

    /**
     * getByte:发送get请求
     * 
     * @param url
     *            请求url
     * @param proxyUrl
     *            代理服务器地址
     * @param proxyPort
     *            代理服务器端口
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String getString(String url, String proxyUrl, int proxyPort) {
        return new String(getByte(url, null, proxyUrl, proxyPort));
    }

    /**
     * getString:发送get请求
     * 
     * @param url
     *            请求url
     * @param headerMap
     *            请求头参数
     * @param proxyUrl
     *            代理服务器地址
     * @param proxyPort
     *            代理服务器端口
     * @return String 与当前请求对应的响应内容字节串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String getString(String url, Map<String, String> headerMap, String proxyUrl, int proxyPort) {
        return new String(getByte(url, headerMap, proxyUrl, proxyPort));
    }

    /**
     * getByte:发送get请求
     * 
     * @param url
     *            请求url
     * @param headerMap
     *            请求头参数
     * @param proxyUrl
     *            代理服务器地址
     * @param proxyPort
     *            代理服务器端口
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] getByte(String url, Map<String, String> headerMap, String proxyUrl, int proxyPort) {
        byte[] content = null;
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);

        if (headerMap != null) {
            Iterator<Entry<String, String>> iterator = headerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = (Entry<String, String>) iterator.next();
                getMethod.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        if (StringUtils.isNotBlank(proxyUrl)) {
            httpClient.getHostConfiguration().setProxy(proxyUrl, proxyPort);
        }

        // 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        // getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
        // new DefaultHttpMethodRetryHandler());

        InputStream inputStream = null;
        try {
            if (httpClient.executeMethod(getMethod) == HttpStatus.SC_OK) {
                inputStream = getMethod.getResponseBodyAsStream();
                content = IOUtils.toByteArray(inputStream);
            } else {
                LOG.error(String.format("Do get [%s] failed: %s", url, getMethod.getStatusLine()));
            }
        } catch (IOException ex) {
            LOG.error(String.format("Do get [%s] IOException: %s", url, ex));
        } finally {
            IOUtils.closeQuietly(inputStream);
            getMethod.releaseConnection();
        }
        return content;
    }

    /**
     * postString:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param parameterMap
     *            请求参数容器
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String postString(String url, Map<String, String> parameterMap) {
        return postString(url, null, parameterMap, null, null, 0);
    }

    /**
     * postString:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String postString(String url, Map<String, String> parameterMap, String paramCharset) {
        return postString(url, null, parameterMap, paramCharset, null, 0);
    }

    /**
     * postString:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param headerMap
     *            请求头参数容器
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String postString(String url, Map<String, String> headerMap, Map<String, String> parameterMap, String paramCharset) {
        return postString(url, headerMap, parameterMap, paramCharset, null, 0);
    }

    /**
     * postString:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @param proxyUrl
     *            代理服务器地址
     * @param proxyPort
     *            代理服务器端口
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String postString(String url, Map<String, String> parameterMap, String paramCharset, String proxyUrl, int proxyPort) {
        return postString(url, null, parameterMap, paramCharset, proxyUrl, 0);
    }

    /**
     * postString:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param headerMap
     *            请求头参数容器
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @param proxyUrl
     *            代理服务器地址
     * @param proxyPort
     *            代理服务器端口
     * @return String 与当前请求对应的响应内容字符串
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static String postString(String url, Map<String, String> headerMap, Map<String, String> parameterMap, String paramCharset, String proxyUrl,
            int proxyPort) {
        return new String(postByte(url, headerMap, parameterMap, paramCharset, proxyUrl, proxyPort));
    }

    /**
     * postByte:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param parameterMap
     *            请求参数容器
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] postByte(String url, Map<String, String> parameterMap) {
        return postByte(url, null, parameterMap, null, null, 0);
    }

    /**
     * postByte:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] postByte(String url, Map<String, String> parameterMap, String paramCharset) {
        return postByte(url, null, parameterMap, paramCharset, null, 0);
    }

    /**
     * postByte:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param headerMap
     *            请求头参数容器
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] postByte(String url, Map<String, String> headerMap, Map<String, String> parameterMap, String paramCharset) {
        return postByte(url, headerMap, parameterMap, paramCharset, null, 0);
    }

    /**
     * postByte:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @param proxyUrl
     *            代理服务器地址
     * @param proxyPort
     *            代理服务器端口
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] postByte(String url, Map<String, String> parameterMap, String paramCharset, String proxyUrl, int proxyPort) {
        return postByte(url, null, parameterMap, paramCharset, proxyUrl, 0);
    }

    /**
     * postByte:发送POST请求
     * 
     * @param url
     *            请求地址
     * @param headerMap
     *            请求头参数容器
     * @param parameterMap
     *            请求参数容器
     * @param paramCharset
     *            参数字符集
     * @param proxyUrl
     *            代理服务器地址 代理服务器地址
     * @param proxyPort
     *            代理服务器端口号
     * @return byte[] 与当前请求对应的响应内容字节数组
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public static byte[] postByte(String url, Map<String, String> headerMap, Map<String, String> parameterMap, String paramCharset, String proxyUrl,
            int proxyPort) {
        byte[] content = null;
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        if (StringUtils.isNotBlank(paramCharset)) {
            postMethod.getParams().setContentCharset(paramCharset);
            postMethod.getParams().setHttpElementCharset(paramCharset);
        }

        if (headerMap != null) {
            Iterator<Entry<String, String>> iterator = headerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = (Entry<String, String>) iterator.next();
                postMethod.addRequestHeader(entry.getKey(), entry.getValue());
            }
        }

        Iterator<String> iterator = parameterMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            postMethod.addParameter(key, parameterMap.get(key));
        }

        if (StringUtils.isNotBlank(proxyUrl)) {
            httpClient.getHostConfiguration().setProxy(proxyUrl, proxyPort);
        }

        // 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        // postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
        // new DefaultHttpMethodRetryHandler());

        InputStream inputStream = null;
        try {
            if (httpClient.executeMethod(postMethod) == HttpStatus.SC_OK) {
                inputStream = postMethod.getResponseBodyAsStream();
                content = IOUtils.toByteArray(inputStream);
            } else {
                LOG.error(String.format("Do post [%s] failed: %s", url, postMethod.getStatusLine()));
            }
        } catch (IOException ex) {
            LOG.error(String.format("Do post [%s] IOException: %s", url, ex));
        } finally {
            IOUtils.closeQuietly(inputStream);
            postMethod.releaseConnection();
        }
        return content;
    }

}
