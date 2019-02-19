package com.caisl.ap.activity.participate.domain;

import com.caisl.ap.core.domain.ActivityDTO;
import com.caisl.ap.rule.base.Rule;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * BaseActivityPartDTO
 *
 * @author caisl
 * @since 2019-01-12
 */
public abstract class BaseActivityPartDTO implements ActivityDTO {
    /**
     * 活动规则集合
     */
    private List<Rule> rules;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
