package com.caisl.ap.activity.participate.parser;

import com.caisl.ap.activity.participate.domain.BaseActivityPartDTO;
import com.caisl.ap.activity.participate.domain.NewCustomerPartDTO;
import com.caisl.ap.activity.request.NewCustomerPartRequest;
import com.caisl.ap.activity.response.NewCustomerPartResponse;
import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.ActivityConfigDO;
import com.caisl.ap.core.annotation.ActivityTypeMapper;
import com.caisl.ap.core.annotation.FunctionMapper;
import com.caisl.ap.core.base.IActivityResponseParser;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import com.caisl.ap.rule.Rule;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * NewCustomerPartDTOParser
 *
 * @author shinan
 * @since 2019-01-12
 */
@Component
@ActivityTypeMapper({ActivityTypeEnum.NEW_CUSTOMER_GIFT})
@FunctionMapper({FunctionCodeEnum.ACTIVITY_PARTICIPATE})
public class NewCustomerPartDTOParser extends AbstractActivityPartDTOParser<NewCustomerPartRequest> implements IActivityResponseParser {

    @Override
    protected ActivityConfigDO queryDB(NewCustomerPartRequest request) {
        System.out.println("do queryDB");
        return new ActivityConfigDO();
    }

    @Override
    protected List<Rule> buildRules(ActivityConfigDO activityConfigDO) {
        System.out.println("do buildRules");
        return Collections.emptyList();
    }

    @Override
    protected BaseActivityPartDTO assembleDTO(ActivityConfigDO activityConfigDO, List<Rule> rules) {
        System.out.println("do assembleDTO");
        return new NewCustomerPartDTO();
    }

    @Override
    public ActivityResponse buildResponse(ContextParam contextParam) {
        System.out.println("do buildResponse");
        return new NewCustomerPartResponse();
    }
}
