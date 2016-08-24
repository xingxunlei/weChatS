/**
 * DateSerializer.java
 * com.xingxunlei.wechat.commons.utils.json
 *
 * Function： json日期格式转换
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
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * ClassName:DateSerializer 
 * Function: json日期格式转换
 * 
 * @author Simon
 * @version
 * @since Ver 1.1
 * @Date 2016-8-24 上午11:16:55
 * 
 * @see
 */
public class DateSerializer extends JsonSerializer<Date> {
    
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);

    }
}
