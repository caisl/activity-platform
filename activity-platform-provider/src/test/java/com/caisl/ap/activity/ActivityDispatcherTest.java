
package com.caisl.ap.activity;

import com.caisl.ap.activity.request.NewCustomerPartRequest;
import com.caisl.ap.BaseTest;
import com.caisl.ap.core.ActivityDispatcher;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * ActivityDispatcherTest
 *
 * @author caisl
 * @since 2019-01-12
 */
public class ActivityDispatcherTest extends BaseTest {

    public static final String customerRegisterId = "caisl";
    public static final String channelId= "WECHAT";
    @Resource
    ActivityDispatcher activityDispatcher;

    @Test
    public void dispatcherTest() {
        NewCustomerPartRequest newCustomerPartRequest = new NewCustomerPartRequest(customerRegisterId, channelId);
        ContextParam contextParam = new ContextParam(FunctionCodeEnum.ACTIVITY_PARTICIPATE, newCustomerPartRequest,
                ActivityTypeEnum.NEW_CUSTOMER_GIFT.getType());
        activityDispatcher.dispatcher(contextParam);
    }

}
