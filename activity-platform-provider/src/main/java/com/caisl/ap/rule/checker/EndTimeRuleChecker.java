package com.caisl.ap.rule.checker;


import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.activity.constant.ResultCodeEnum;
import com.caisl.ap.rule.base.RuleChecker;
import com.caisl.ap.rule.domain.pojo.EndTimeRule;
import com.caisl.ap.rule.domain.request.RuleCheckRequest;
import com.caisl.ap.system.util.ResultUtil;
import org.springframework.stereotype.Component;

/**
 * StartTimeRuleChecker
 *
 * @author caisl
 * @since 2019-01-18
 */
@Component
public class EndTimeRuleChecker implements RuleChecker<EndTimeRule, RuleCheckRequest> {
    @Override
    public Result check(EndTimeRule rule) {
        if(rule.getRule() == null){
            return ResultUtil.failResult(ResultCodeEnum.RULE_PARAM_ERROR.getCode(), "活动过期时间为null");
        }
        return ResultUtil.successResult(null);
    }

    @Override
    public Result<String> validate(EndTimeRule rule, RuleCheckRequest request) {
        if(rule.getRule() <= System.currentTimeMillis()){
            return ResultUtil.failResult("","");
        }
        return ResultUtil.successResult(null);
    }
}
