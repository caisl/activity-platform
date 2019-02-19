package com.caisl.ap.system.util.log;

import org.apache.commons.lang3.StringUtils;

/**
 * AbstractLogFormat
 *
 * @author caisl
 * @since 2019-02-18
 */
public abstract class AbstractLogFormat implements LogFormat{
    protected String title;

    public AbstractLogFormat(String title) {
        this.title = title;
    }

    private String getTitle() {
        StringBuilder sb = new StringBuilder(32);
        if (StringUtils.isNotBlank(this.title)) {
            sb.append("[").append(this.title).append("]。");
        }

        return sb.toString();
    }

    public String log() {
        return this.getTitle() + this.buildLogMsg();
    }

    protected abstract String buildLogMsg();
}
