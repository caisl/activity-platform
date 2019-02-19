package com.caisl.ap.rule.base;

import com.alibaba.fastjson.JSON;

/**
 * AbstractRule
 *
 * @author caisl
 * @since 2019-01-21
 */
public abstract class AbstractRule<T> implements Rule<T> {
    /**
     * 规则内容
     */
    protected T rule;

    /**
     * 构造函数的rule
     *
     * @param rule
     */
    public AbstractRule(T rule) {
        this.rule = rule;
    }
    /**
     * 规则
     *
     * @return
     */
    @Override
    public T getRule() {
        return this.rule;
    }

    /**
     * 设置规则
     * <p>
     * 最好使用构造方法来设置
     * ,这里是fastjson转换使用
     *
     * @param rule
     */
    @Override
    public void setRule(T rule) {
        this.rule = rule;
    }

    /**
     * 得到规则的详细说明
     *
     * @return
     */
    public String getRuleDetail() {
        return getDisplay() + ":" + getCode() + ":" + JSON.toJSONString(rule);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(rule);
    }
}
