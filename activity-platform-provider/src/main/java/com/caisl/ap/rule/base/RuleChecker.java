package com.caisl.ap.rule.base;

import com.caisl.ap.rule.domain.request.RuleCheckRequest;

/**
 * RuleChecker
 *
 * @author shinan
 * @since 2019-01-21
 */
public interface RuleChecker<T extends Rule, Request extends RuleCheckRequest> extends Checker<T> {
    /**
     * 规则检验
     *
     * @param Rule
     * @param request
     * @return
     */
    void validate(T Rule, Request request);
}
