/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.caisl.ap.activity.participate.handler;



import com.caisl.ap.activity.participate.domain.BaseActivityPartDTO;
import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.CommonFactory;
import com.caisl.ap.core.base.IActivityDTOParser;
import com.caisl.ap.core.base.IActivityHandler;
import com.caisl.ap.core.base.IActivityResponseParser;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.rule.Rule;
import com.caisl.ap.system.exception.BusinessRuntimeException;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * AbstractActivityPartHandler
 *
 * @author shinan
 * @since 2019-01-03
 */
public abstract class AbstractActivityPartHandler implements IActivityHandler {
    @Resource
    private CommonFactory commonFactory;

    @Override
    public ActivityResponse handle(ContextParam contextParam) {
        IActivityDTOParser activityDTOParser = commonFactory.getActivityDTOParser(contextParam.getFunctionCode(), contextParam.getActivityType());
        BaseActivityPartDTO activityDTO = (BaseActivityPartDTO) activityDTOParser.buildDTO(contextParam.getRequest());
        contextParam.setActivityDTO(activityDTO);
        doRuleCheck(activityDTO.getRules());
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
     * TODO 活动规则检验不通过的情况怎么处理才好？
     *
     * @param rules
     */
    private void doRuleCheck(List<Rule> rules) {
        if(CollectionUtils.isEmpty(rules)){
            return;
        }
    }

    /**
     * 执行操作
     * 各子类自行实现，完成各种动作
     */
    protected abstract void doAction(ContextParam contextParam);


}
