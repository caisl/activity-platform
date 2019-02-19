package com.caisl.ap.rule.checker;

import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.activity.constant.ResultCodeEnum;
import com.caisl.ap.rule.base.RuleChecker;
import com.caisl.ap.rule.domain.pojo.ActivityStatusRule;
import com.caisl.ap.rule.domain.request.RuleCheckRequest;
import com.caisl.ap.system.util.ResultUtil;
import org.springframework.stereotype.Component;

/**
 * StartTimeRuleChecker
 *
 * @author caisl
 * @since 2019-02-12
 */
@Component
public class ActivityStatusRuleChecker implements RuleChecker<ActivityStatusRule, RuleCheckRequest> {
    public static final int PROCESSING = 3;

    @Override
    public Result check(ActivityStatusRule rule) {
        if(rule.getRule() == null){
            return ResultUtil.failResult(ResultCodeEnum.RULE_PARAM_ERROR.getCode(), "活动状态为null");
        }
        return ResultUtil.successResult(null);
    }

    @Override
    public Result<String> validate(ActivityStatusRule rule, RuleCheckRequest request) {
        if(rule.getRule() != PROCESSING){
            return ResultUtil.failResult("","");
        }
        return ResultUtil.successResult(null);
    }
}
