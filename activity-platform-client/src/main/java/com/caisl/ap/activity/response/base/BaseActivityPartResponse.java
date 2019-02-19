package com.caisl.ap.activity.response.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * BaseActivityPartResponse
 *
 * @author caisl
 * @since 2019-01-14
 */
public abstract class BaseActivityPartResponse implements ActivityResponse {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
