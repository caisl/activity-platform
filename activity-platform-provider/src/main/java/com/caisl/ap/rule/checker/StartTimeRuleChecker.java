package com.caisl.ap.rule.checker;


import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.activity.constant.ResultCodeEnum;
import com.caisl.ap.rule.base.RuleChecker;
import com.caisl.ap.rule.domain.pojo.StartTimeRule;
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
public class StartTimeRuleChecker implements RuleChecker<StartTimeRule, RuleCheckRequest> {
    @Override
    public Result check(StartTimeRule rule) {
        if(rule.getRule() == null){
            return ResultUtil.failResult(ResultCodeEnum.RULE_PARAM_ERROR.getCode(), "活动开始时间为null");
        }
        return ResultUtil.successResult(null);
    }

    @Override
    public Result<String> validate(StartTimeRule rule, RuleCheckRequest request) {
        if(rule.getRule() > System.currentTimeMillis()){
            return ResultUtil.failResult("","");
        }
        return ResultUtil.successResult(null);
    }
}
