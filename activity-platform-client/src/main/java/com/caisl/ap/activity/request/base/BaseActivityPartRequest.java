package com.caisl.ap.activity.request.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * BaseActivityPartRequest
 *
 * @author caisl
 * @since 2019-01-12
 */
public abstract class BaseActivityPartRequest implements ActivityRequest {

    /**
     * 参与活动的用户ID
     */
    private String customerRegisterId;
    /**
     * 参与渠道ID
     */
    private String channelId;

    public BaseActivityPartRequest(String customerRegisterId, String channelId) {
        this.customerRegisterId = customerRegisterId;
        this.channelId = channelId;
    }


    public String getCustomerRegisterId() {
        return customerRegisterId;
    }

    public void setCustomerRegisterId(String customerRegisterId) {
        this.customerRegisterId = customerRegisterId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
