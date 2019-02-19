package com.caisl.ap.rule.domain.pojo;

import com.caisl.ap.rule.base.AbstractRule;

/**
 * ActivityStatusRule
 *
 * @author caisl
 * @since 2019-02-12
 */
public class ActivityStatusRule extends AbstractRule<Integer> {
    @Override
    public String getCode() {
        return ActivityStatusRule.class.getSimpleName();
    }

    public ActivityStatusRule(Integer rule) {
        super(rule);
    }

    @Override
    public int getSort() {
        return 23;
    }

    @Override
    public String getDisplay() {
        return "活动状态";
    }
}
