package com.caisl.ap.rule.domain.pojo;

import com.caisl.ap.rule.base.AbstractRule;

/**
 * EndTimeRule
 *
 * @author caisl
 * @since 2019-01-18
 */
public class EndTimeRule extends AbstractRule<Long> {
    private static final long serialVersionUID = -4499210248679692849L;

    public EndTimeRule(Long endTime) {
        super(endTime);
    }

    @Override
    public String getCode() {
        return EndTimeRule.class.getSimpleName();
    }

    @Override
    public int getSort() {
        return 22;
    }

    @Override
    public String getDisplay() {
        return "活动结束时间";
    }
}
