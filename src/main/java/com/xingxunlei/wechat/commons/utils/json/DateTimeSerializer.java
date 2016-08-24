/**
 * DateTimeSerializer.java
 * com.xingxunlei.wechat.commons.utils.json
 *
 * Function： 如果是日期时间，就直接返回一个long的数字
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016-8-24 		Simon
 *
 * Copyright (c) 2016, 91Bee All Rights Reserved.
 */

package com.xingxunlei.wechat.commons.utils.json;

import java.io.IOException;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * ClassName:DateTimeSerializer 
 * Function: 如果是日期时间，就直接返回一个long的数字
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-24 上午11:18:06
 * 
 * @see
 */
public class DateTimeSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeNumber(value.getTime());
    }
}
