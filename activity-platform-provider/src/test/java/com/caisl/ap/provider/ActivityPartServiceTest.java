package com.caisl.ap.provider;

import com.caisl.ap.BaseTest;
import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.activity.request.NewCustomerPartRequest;
import com.caisl.ap.activity.response.NewCustomerPartResponse;
import com.caisl.ap.activity.service.IActivityPartService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * ActivityPartServiceTest
 *
 * @author caisl
 * @since 2019-02-16
 */
public class ActivityPartServiceTest extends BaseTest {

    public static final String customerRegisterId = "caisl";
    public static final String channelId = "WECHAT";
    @Resource
    private IActivityPartService activityPartService;

    @Test
    public void partSub1NewCustomerActivity() {
        NewCustomerPartRequest request = new NewCustomerPartRequest(customerRegisterId, channelId);
        Result<NewCustomerPartResponse> result = activityPartService.partSub1NewCustomerActivity(request);
        Assert.assertTrue(result.isSuccess());
        Assert.assertNotNull(result.getModel());
    }

    @Test
    public void partSub2NewCustomerActivity() {
        NewCustomerPartRequest request = new NewCustomerPartRequest(customerRegisterId, channelId);
        Result<NewCustomerPartResponse> result = activityPartService.partSub2NewCustomerActivity(request);
        Assert.assertTrue(result.isSuccess());
        Assert.assertNotNull(result.getModel());
    }
}
