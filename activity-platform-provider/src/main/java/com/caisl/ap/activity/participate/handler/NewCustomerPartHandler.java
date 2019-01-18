/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.caisl.ap.activity.participate.handler;


import com.caisl.ap.core.annotation.ActivityTypeMapper;
import com.caisl.ap.core.annotation.FunctionMapper;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import org.springframework.stereotype.Component;

/**
 * NewCustomerPartHandler
 *
 * @author shinan
 * @since 2019-01-10
 */
@Component
@ActivityTypeMapper({ActivityTypeEnum.NEW_CUSTOMER_GIFT})
@FunctionMapper({FunctionCodeEnum.ACTIVITY_PARTICIPATE})
public class NewCustomerPartHandler extends AbstractActivityPartHandler {

    @Override
    protected void doAction(ContextParam contextParam) {
        System.out.println("do doAction");
    }
}
