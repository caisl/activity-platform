package com.caisl.ap.core;

import com.caisl.ap.activity.domain.Result;
import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.base.IActivityHandler;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.system.exception.BusinessRuntimeException;
import com.caisl.ap.system.util.ResultUtil;
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

    public <T extends ActivityResponse> Result<T> dispatcher(ContextParam contextParam) {
        long t1 = System.currentTimeMillis();
        T response;
        String beanName = contextParam.getRequest().getClass().getName();
        System.out.println("执行" + beanName + "开始 " + "context:" + contextParam.toString());
        try {
            IActivityHandler activityHandler = factory.getActivityHandler(contextParam.getFunctionCode(), contextParam.getActivityType());
            response = activityHandler.handle(contextParam);
        } catch (BusinessRuntimeException e) {
            e.printStackTrace();
            return ResultUtil.failResult(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResult("","系统Exception异常");
        } catch (Throwable e) {
            e.printStackTrace();
            return ResultUtil.failResult("","系统Throwable异常");

        }
        System.out.println("执行" + beanName + "结束 time:" + (System.currentTimeMillis()
                - t1) + "context:" + contextParam.toString() + "response:" + response);

        return ResultUtil.successResult(response);
    }
}
