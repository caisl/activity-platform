package com.caisl.ap.provider;

import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.activity.request.NewCustomerPartRequest;
import com.caisl.ap.activity.response.NewCustomerPartResponse;
import com.caisl.ap.activity.service.IActivityPartService;
import com.caisl.ap.core.ActivityDispatcher;
import com.caisl.ap.core.domain.ActivitySubTypeEnum;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ActivityPartService
 *
 * @author caisl
 * @since 2019-01-22
 */
@Service
public class ActivityPartService implements IActivityPartService {
    @Resource
    private ActivityDispatcher activityDispatcher;


    @Override
    public Result<NewCustomerPartResponse> partSub1NewCustomerActivity(NewCustomerPartRequest request) {
        ContextParam contextParam = new ContextParam(FunctionCodeEnum.ACTIVITY_PARTICIPATE, request,
                ActivityTypeEnum.NEW_CUSTOMER_GIFT.getType(), ActivitySubTypeEnum.NEW_CUSTOMER_GIFT_SUB1.getType());
        return activityDispatcher.dispatcher(contextParam);
    }

    @Override
    public Result<NewCustomerPartResponse> partSub2NewCustomerActivity(NewCustomerPartRequest request) {
        ContextParam contextParam = new ContextParam(FunctionCodeEnum.ACTIVITY_PARTICIPATE, request,
                ActivityTypeEnum.NEW_CUSTOMER_GIFT.getType(), ActivitySubTypeEnum.NEW_CUSTOMER_GIFT_SUB2.getType());
        return activityDispatcher.dispatcher(contextParam);
    }
}
