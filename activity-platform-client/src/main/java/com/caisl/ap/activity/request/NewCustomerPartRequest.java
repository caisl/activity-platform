package com.caisl.ap.activity.request;

import com.caisl.ap.activity.request.base.BaseActivityPartRequest;

/**
 * NewCustomerPartRequest
 *
 * @author caisl
 * @since 2019-01-12
 */
public class NewCustomerPartRequest extends BaseActivityPartRequest {

    /**
     * 手机号码
     */
    private String mobile;

    public NewCustomerPartRequest(String customerRegisterId, String channelId) {
        super(customerRegisterId, channelId);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
