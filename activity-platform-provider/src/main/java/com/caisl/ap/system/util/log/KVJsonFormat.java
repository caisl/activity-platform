package com.caisl.ap.system.util.log;

import java.util.HashMap;
import java.util.Map;

/**
 * KVJsonFormat
 *
 * @author caisl
 * @since 2019-02-18
 */
public class KVJsonFormat extends JsonLogFormat {
    private Map<String, Object> map = new HashMap();

    private KVJsonFormat(String title) {
        super(title);
    }

    public static KVJsonFormat title(String title) {
        return new KVJsonFormat(title);
    }

    public KVJsonFormat add(String key, Object v) {
        this.map.put(key, v);
        return this;
    }

    public KVJsonFormat addAll(Map<String, Object> params) {
        if (params != null) {
            this.map.putAll(params);
        }

        return this;
    }

    protected String buildLogMsg() {
        return this.format(this.map);
    }
}
