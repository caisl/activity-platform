package com.caisl.ap.rule.base;

import java.io.Serializable;

/**
 * Rule
 *
 * @author caisl
 * @since 2019-01-12
 */
public interface Rule<T> extends Serializable {
    /**
     * 规则Code
     *
     * @return
     */
    String getCode();

    /**
     * 规则
     *
     * @return
     */
    T getRule();

    /**
     * 设置规则
     * <p>
     * 最好使用构造方法来设置
     * <p>
     * 这里是给模型转换器fastjson转换使用
     */
    void setRule(T rule);

    /**
     * 排序Key,数字越大,越后校验该规则
     *
     * @return
     */
    int getSort();
    /**
     * 规则详细信息
     *
     * @return
     */
    String getDisplay();
}
