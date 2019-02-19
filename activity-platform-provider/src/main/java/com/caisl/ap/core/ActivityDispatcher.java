package com.caisl.ap.core;

import ch.qos.logback.classic.Level;
import com.alibaba.fastjson.JSON;
import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.activity.constant.ResultCodeEnum;
import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.base.IActivityHandler;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.system.exception.BusinessRuntimeException;
import com.caisl.ap.system.logger.ActivityLoggerFactory;
import com.caisl.ap.system.logger.ActivityLoggerMarker;
import com.caisl.ap.system.util.LogUtil;
import com.caisl.ap.system.util.ResultUtil;
import com.caisl.ap.system.util.log.KVJsonFormat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ActivityDispatcher
 *
 * @author caisl
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
        LogUtil.log(ActivityLoggerFactory.BUSINESS, ActivityLoggerMarker.BUSINESS, Level.INFO, "执行" + beanName + "开始" +
                " " + "context:" + JSON.toJSONString(contextParam));
        try {
            IActivityHandler activityHandler = factory.getActivityHandler(contextParam.getFunctionCode(), contextParam.getActivityType());
            response = activityHandler.handle(contextParam);
        } catch (BusinessRuntimeException e) {
            LogUtil.log(ActivityLoggerFactory.EXCEPTION_HANDLER, ActivityLoggerMarker.BUSINESS, Level.ERROR,
                    LogUtil.formatLog(KVJsonFormat.title("业务处理BusinessRuntimeException异常")
                            .add("beanName", beanName)
                            .add("context", contextParam)
                            .add("errMsg", e.getMsg())));
            return ResultUtil.failResult(e.getCode(), e.getMessage());
        } catch (Exception e) {
            LogUtil.log(ActivityLoggerFactory.EXCEPTION_HANDLER, ActivityLoggerMarker.BUSINESS, Level.ERROR,
                    LogUtil.formatLog(KVJsonFormat.title("系统Exception异常")
                            .add("beanName", beanName)
                            .add("context", contextParam)), e);
            return ResultUtil.failResult(ResultCodeEnum.SYSTEM_ERROR.getCode(), "系统Exception异常");
        } catch (Throwable e) {
            LogUtil.log(ActivityLoggerFactory.EXCEPTION_HANDLER, ActivityLoggerMarker.BUSINESS, Level.ERROR,
                    LogUtil.formatLog(KVJsonFormat.title("系统Throwable异常")
                            .add("beanName", beanName)
                            .add("context", contextParam)), e);
            return ResultUtil.failResult(ResultCodeEnum.SYSTEM_ERROR.getCode(), "系统Throwable异常");

        }
        LogUtil.log(ActivityLoggerFactory.BUSINESS, ActivityLoggerMarker.BUSINESS, Level.INFO, "执行" + beanName + "结束 time:" + (System.currentTimeMillis()
                - t1) + " context:" + JSON.toJSONString(contextParam) + "response:" + JSON.toJSONString(response));
        return ResultUtil.successResult(response);
    }
}
