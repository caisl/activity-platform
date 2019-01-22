package com.caisl.ap.rule.base;

import com.caisl.ap.activity.domain.Result;

/**
 * Checker
 *
 * @author shinan
 * @since 2019-01-21
 */
public interface Checker<T> {
    /**
     * 规则参数格式检验
     *
     * @param rule
     * @return
     */
    Result check(T rule);
}
