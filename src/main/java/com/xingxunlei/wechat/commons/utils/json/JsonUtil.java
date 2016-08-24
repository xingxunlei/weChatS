/**
 * JsonUtil.java
 * com.xingxunlei.wechat.commons.utils.json
 *
 * Function： json工具类
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-24 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.commons.utils.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * ClassName:JsonUtil 
 * Function: json工具类
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-24 上午11:19:18
 * 
 * @see
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        // 设置默认日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        // 如果值为null,则不需写入json串中
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        // 提供其它默认设置
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
    }

    /**
     * 将对象转换成json字符串格式（默认将转换所有的属性）
     * 
     * @param o
     * @return
     */
    public static String toJsonStr(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("对象解析成json字符串出现异常：" + e.toString());
        }
    }

    /**
     * 将对象转换成json字符串格式
     * 
     * @param value
     *            需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties
     *            需要转换的属性
     */
    public static String toJsonStr(Object value, String[] properties) {
        try {
            return objectMapper.writer(
                    new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class))
                            .toString(), SimpleBeanPropertyFilter.filterOutAllExcept(properties))).writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("对象解析成json字符串出现异常：" + e.toString());
        }

    }

    /**
     * 将对象转换成json字符串格式
     * 
     * @param value
     *            需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param properties2Exclude
     *            需要排除的属性
     */
    public static String toJsonStrWithExcludeProperties(Object value, String[] properties2Exclude) {
        try {
            return objectMapper.writer(
                    new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(value.getClass(), JsonFilter.class))
                            .toString(), SimpleBeanPropertyFilter.serializeAllExcept(properties2Exclude))).writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("对象解析成json字符串出现异常：" + e.toString());
        }

    }

    /**
     * json串转换成对应的java对象
     * 
     * @param <T>
     * @param content
     * @param valueType
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("对象json字符串转换成对象出现异常：" + e.toString());
        }
    }

    /**
     * 得到jackson原始ObjectMapper进行本类未开放api的调用
     * 
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
