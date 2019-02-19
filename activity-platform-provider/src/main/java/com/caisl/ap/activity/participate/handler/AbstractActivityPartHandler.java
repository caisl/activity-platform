package com.caisl.ap.activity.participate.handler;


import com.alibaba.fastjson.JSON;
import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.activity.constant.ResultCodeEnum;
import com.caisl.ap.activity.participate.domain.BaseActivityPartDTO;
import com.caisl.ap.activity.request.base.BaseActivityPartRequest;
import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.CommonFactory;
import com.caisl.ap.core.base.IActivityDTOParser;
import com.caisl.ap.core.base.IActivityHandler;
import com.caisl.ap.core.base.IActivityResponseParser;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.rule.ActivityRuleEngine;
import com.caisl.ap.rule.base.Rule;
import com.caisl.ap.rule.domain.request.ActivityPartRuleRequest;
import com.caisl.ap.system.exception.BusinessRuntimeException;
import com.caisl.ap.system.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * AbstractActivityPartHandler
 *
 * @author caisl
 * @since 2019-01-03
 */
public abstract class AbstractActivityPartHandler implements IActivityHandler {
    @Resource
    private CommonFactory commonFactory;
    @Resource
    private ActivityRuleEngine activityRuleEngine;

    @Override
    public ActivityResponse handle(ContextParam contextParam) {
        IActivityDTOParser activityDTOParser = commonFactory.getActivityDTOParser(contextParam.getFunctionCode(), contextParam.getActivityType());
        BaseActivityPartDTO activityDTO = (BaseActivityPartDTO) activityDTOParser.buildDTO(contextParam.getRequest());
        contextParam.setActivityDTO(activityDTO);
        doRuleCheck((BaseActivityPartRequest) contextParam.getRequest(), activityDTO.getRules());
        doAction(contextParam);
        return buildResponse(contextParam);
    }

    /**
     * 构造响应对象
     *
     * @param contextParam
     * @return
     */
    private ActivityResponse buildResponse(ContextParam contextParam) {
        IActivityResponseParser parser = commonFactory.getActivityResponseParser(contextParam.getFunctionCode(),
                contextParam.getActivityType());

        ActivityResponse activityResponse = parser.buildResponse(contextParam);
        if (activityResponse == null) {
            throw new BusinessRuntimeException("", "");
        }
        return activityResponse;
    }

    /**
     * 活动规则检验
     *
     * @param rules
     */
    private void doRuleCheck(BaseActivityPartRequest activityRequest, List<Rule> rules) {
        //没有规则直接返回
        if (CollectionUtils.isEmpty(rules)) {
            return;
        }
        //检查参数
        Result<List<String>> paramCheck = activityRuleEngine.check(rules);
        if (!ResultUtil.isResultSuccess(paramCheck)) {
            throw new BusinessRuntimeException(paramCheck.getResultCode(), paramCheck.getMessage());
        }
        if (!CollectionUtils.isEmpty(paramCheck.getModel())) {
            throw new BusinessRuntimeException(ResultCodeEnum.RULE_PARAM_ERROR.getCode(), JSON.toJSONString(paramCheck.getModel()));
        }
        rules.sort(Comparator.comparing(Rule::getSort));
        ActivityPartRuleRequest activityPartRuleRequest = new ActivityPartRuleRequest();
        activityPartRuleRequest.setCustomerRegisterId(activityRequest.getCustomerRegisterId());
        Result<String> result = activityRuleEngine.validate(activityPartRuleRequest, rules);
        if (!ResultUtil.isResultSuccess(result)) {
            throw new BusinessRuntimeException(result.getResultCode(), result.getMessage());
        }
        if (StringUtils.isNotBlank(result.getModel())) {
            throw new BusinessRuntimeException(ResultCodeEnum.RULE_CHECK_FAIL.getCode(), result.getModel());
        }
        return;
    }

    /**
     * 执行操作
     * 各子类自行实现，完成各种动作
     *
     * @param contextParam
     */
    protected abstract void doAction(ContextParam contextParam);


}
