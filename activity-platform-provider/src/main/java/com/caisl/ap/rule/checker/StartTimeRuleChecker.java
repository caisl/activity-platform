package com.caisl.ap.rule.checker;

import com.caisl.ap.activity.domain.Result;
import com.caisl.ap.rule.base.RuleChecker;
import com.caisl.ap.rule.domain.pojo.StartTimeRule;
import com.caisl.ap.rule.domain.request.ActivityPartRuleRequest;
import com.caisl.ap.system.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * StartTimeRuleChecker
 *
 * @author shinan
 * @since 2019-01-22
 */
@Component
public class StartTimeRuleChecker implements RuleChecker<StartTimeRule, ActivityPartRuleRequest> {
    @Override
    public Result check(StartTimeRule rule) {
        System.out.println(this.getClass() + " do check");
        return null;
    }

    @Override
    public Result<String> validate(StartTimeRule rule, ActivityPartRuleRequest request) {
        System.out.println(this.getClass() + " do validate");
        if(rule.getStartTime() <= System.currentTimeMillis()){
            return ResultUtil.successResult(StringUtils.EMPTY);
        }
        return ResultUtil.failResult("", "还未到开始时间");
    }
}
