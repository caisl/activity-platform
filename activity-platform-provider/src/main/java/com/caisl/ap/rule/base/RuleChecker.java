package com.caisl.ap.rule.base;

import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.rule.domain.request.RuleCheckRequest;

/**
 * RuleChecker
 *
 * @author caisl
 * @since 2019-01-21
 */
public interface RuleChecker<T extends Rule, REQ extends RuleCheckRequest> extends Checker<T> {
    /**
     * 规则检验
     *
     * @param rule
     * @param request
     * @return
     */
    Result validate(T rule, REQ request);
}
