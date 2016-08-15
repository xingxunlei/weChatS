/**
 * JsonBigDecimalValueProcessor.java
 * com.xingxunlei.wechat.commons.utils.json
 *
 * Function： ajax返回json格式数据时，BigDecimal类型数据格式化
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-11 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
*/

package com.xingxunlei.wechat.commons.utils.ajax.json;

import java.text.DecimalFormat;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * ClassName:JsonBigDecimalValueProcessor
 * Function:  ajax返回json格式数据时，BigDecimal类型数据格式化
 *
 * @author   Simon
 * @version  
 * @since    Ver 1.1
 * @Date	 2016-8-11		下午1:28:15
 *
 * @see 	 
 */
public class JsonBigDecimalValueProcessor implements JsonValueProcessor {

private String valuePattern = "#.00";
    
    public JsonBigDecimalValueProcessor() {
        super();
    }
    
    public JsonBigDecimalValueProcessor(String format) {
        super();
        this.valuePattern = format;
    }
    
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }
    
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }
    
    private Object process(Object value) {
        try {
            if (value instanceof Double) {
                DecimalFormat df = new DecimalFormat(valuePattern);
                return df.format(value);
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }

    }

    public String getValuePattern() {
        return valuePattern;
    }

    public void setValuePattern(String pValuePattern) {
        valuePattern = pValuePattern;
    }

}

