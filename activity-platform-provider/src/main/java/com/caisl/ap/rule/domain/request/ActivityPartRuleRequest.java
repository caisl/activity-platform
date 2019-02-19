package com.caisl.ap.rule.domain.request;

/**
 * ActivityPartRuleRequest
 *
 * @author caisl
 * @since 2019-01-21
 */
public class ActivityPartRuleRequest implements RuleCheckRequest {
    /**
     * 用户ID
     */
    private String customerRegisterId;

    public String getCustomerRegisterId() {
        return customerRegisterId;
    }

    public void setCustomerRegisterId(String customerRegisterId) {
        this.customerRegisterId = customerRegisterId;
    }
}
