package com.caisl.ap.rule.base;

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
    void check(T rule);
}
