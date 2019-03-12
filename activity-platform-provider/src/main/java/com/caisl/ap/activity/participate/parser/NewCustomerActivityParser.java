package com.caisl.ap.activity.participate.parser;

import ch.qos.logback.classic.Level;
import com.caisl.ap.activity.participate.domain.BaseActivityPartDTO;
import com.caisl.ap.activity.participate.domain.NewCustomerPartDTO;
import com.caisl.ap.activity.request.NewCustomerPartRequest;
import com.caisl.ap.activity.response.NewCustomerPartResponse;
import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.common.constants.PresellActivityStatusEnum;
import com.caisl.ap.common.dataobject.ActivityConfigDO;
import com.caisl.ap.core.annotation.ActivityTypeMapper;
import com.caisl.ap.core.annotation.FunctionMapper;
import com.caisl.ap.core.base.IActivityResponseParser;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import com.caisl.ap.rule.base.Rule;
import com.caisl.ap.rule.domain.pojo.ActivityStatusRule;
import com.caisl.ap.rule.domain.pojo.EndTimeRule;
import com.caisl.ap.rule.domain.pojo.StartTimeRule;
import com.caisl.ap.system.exception.BusinessRuntimeException;
import com.caisl.ap.system.logger.ActivityLoggerFactory;
import com.caisl.ap.system.logger.ActivityLoggerMarker;
import com.caisl.ap.system.util.LogUtil;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * NewCustomerPartDTOParser
 *
 * @author caisl
 * @since 2019-01-12
 */
@Component
@ActivityTypeMapper({ActivityTypeEnum.NEW_CUSTOMER_GIFT})
@FunctionMapper({FunctionCodeEnum.ACTIVITY_PARTICIPATE})
public class NewCustomerActivityParser extends AbstractActivityPartDTOParser<NewCustomerPartRequest> implements IActivityResponseParser<NewCustomerPartRequest, NewCustomerPartDTO> {

    @Override
    protected ActivityConfigDO queryDB(NewCustomerPartRequest request) {
        LogUtil.log(ActivityLoggerFactory.BUSINESS, ActivityLoggerMarker.BUSINESS, Level.INFO, "do queryDB");
        //MOCK 模拟数据，跑通流程
        ActivityConfigDO activityConfigDO = new ActivityConfigDO();
        activityConfigDO.setStatus(PresellActivityStatusEnum.PROCESSING.getCode());
        activityConfigDO.setEndTime(System.currentTimeMillis() + 24*60*60*1000);
        activityConfigDO.setStartTime(System.currentTimeMillis() - 24*60*60*1000);
        return activityConfigDO;
    }

    @Override
    protected List<Rule> buildRules(ActivityConfigDO activityConfigDO) {
        LogUtil.log(ActivityLoggerFactory.BUSINESS, ActivityLoggerMarker.BUSINESS, Level.INFO, "do buildRules");
        List<Rule> rules = Lists.newArrayList();
        rules.add(new StartTimeRule(activityConfigDO.getStartTime()));
        rules.add(new EndTimeRule(activityConfigDO.getEndTime()));
        rules.add(new ActivityStatusRule(activityConfigDO.getStatus()));
        return rules;
    }

    @Override
    protected BaseActivityPartDTO assembleDTO(NewCustomerPartRequest request, ActivityConfigDO activityConfigDO) {
        LogUtil.log(ActivityLoggerFactory.BUSINESS, ActivityLoggerMarker.BUSINESS, Level.INFO, "do assembleDTO");
        return new NewCustomerPartDTO();
    }


    @Override
    public ActivityResponse buildResponse(ContextParam<NewCustomerPartRequest, NewCustomerPartDTO> contextParam) {
        LogUtil.log(ActivityLoggerFactory.BUSINESS, ActivityLoggerMarker.BUSINESS, Level.INFO, "do buildResponse");
        return new NewCustomerPartResponse();
    }
}
