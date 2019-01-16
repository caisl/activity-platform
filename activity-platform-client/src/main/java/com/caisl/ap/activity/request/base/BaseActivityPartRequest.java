package com.caisl.ap.activity.request.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * BaseActivityPartRequest
 *
 * @author shinan
 * @since 2019-01-12
 */
public abstract class BaseActivityPartRequest implements ActivityRequest {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
