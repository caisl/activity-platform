package com.caisl.ap.core;

import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.base.IActivityHandler;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.system.exception.BusinessRuntimeException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ActivityDispatcher
 *
 * @author shinan
 * @since 2019-01-11
 */
@Component
public class ActivityDispatcher {
    @Resource
    private CommonFactory factory;

    public <T extends ActivityResponse> T dispatcher(ContextParam contextParam) {
        long t1 = System.currentTimeMillis();
        T result = null;
        String beanName = contextParam.getRequest().getClass().getName();
        System.out.println("执行" + beanName + "开始 " + "context:" + contextParam.toString());
        try {
            IActivityHandler activityHandler = factory.getActivityHandler(contextParam.getFunctionCode(), contextParam.getActivityType());
            result = activityHandler.handle(contextParam);
        } catch (BusinessRuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("执行" + beanName + "结束 time:" + (System.currentTimeMillis()
                - t1) + "context:" + contextParam.toString() + "response:" + result);

        return result;
    }
}
