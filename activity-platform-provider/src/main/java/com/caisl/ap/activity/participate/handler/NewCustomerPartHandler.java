package com.caisl.ap.activity.participate.handler;


import ch.qos.logback.classic.Level;
import com.caisl.ap.core.annotation.ActivityTypeMapper;
import com.caisl.ap.core.annotation.FunctionMapper;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import com.caisl.ap.process.ActivityRecordProcess;
import com.caisl.ap.process.RewardProcess;
import com.caisl.ap.system.logger.ActivityLoggerFactory;
import com.caisl.ap.system.logger.ActivityLoggerMarker;
import com.caisl.ap.system.util.LogUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * NewCustomerPartHandler
 *
 * @author caisl
 * @since 2019-01-10
 */
@Component
@ActivityTypeMapper({ActivityTypeEnum.NEW_CUSTOMER_GIFT})
@FunctionMapper({FunctionCodeEnum.ACTIVITY_PARTICIPATE})
public class NewCustomerPartHandler extends AbstractActivityPartHandler {

    @Resource
    private ActivityRecordProcess activityRecordProcess;
    @Resource
    private RewardProcess rewardProcess;

    @Override
    protected void doAction(ContextParam contextParam) {
        LogUtil.log(ActivityLoggerFactory.BUSINESS, ActivityLoggerMarker.BUSINESS, Level.INFO, "do doAction");
        rewardProcess.reward();
        activityRecordProcess.insertRecord();
    }


}
