package com.caisl.ap.system.util.log;

import com.alibaba.fastjson.JSON;

/**
 * JsonLogFormat
 *
 * @author caisl
 * @since 2019-02-18
 */
public abstract class JsonLogFormat extends AbstractLogFormat {
    private static final String JSON_FORMAT = "json:";

    protected JsonLogFormat(String title) {
        super(title);
    }

    protected String format(Object obj) {
        return "json:" + (obj == null ? "{}" : JSON.toJSONString(obj));
    }
}
