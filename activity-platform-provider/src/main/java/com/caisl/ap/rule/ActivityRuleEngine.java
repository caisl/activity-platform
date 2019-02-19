package com.caisl.ap.rule;

import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.rule.base.Rule;
import com.caisl.ap.rule.base.RuleChecker;
import com.caisl.ap.rule.domain.request.ActivityPartRuleRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ActivityRuleEngine
 *
 * @author caisl
 * @since 2019-01-22
 */
@Component
public class ActivityRuleEngine extends CheckerEngine<Rule, RuleChecker> {
    /**
     * 规则引擎执行方法
     *
     * @param request
     * @param rules
     * @return
     */
    public Result<String> validate(final ActivityPartRuleRequest request, List<Rule> rules) {
        return (new CheckerProcess<Rule>() {
            public Result _process(Rule rule) {
                return ActivityRuleEngine.this.getChecker(rule).validate(rule, request);
            }
        }).processSingleRule(rules);
    }

}
