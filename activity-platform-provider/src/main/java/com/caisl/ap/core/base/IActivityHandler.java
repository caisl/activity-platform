package com.caisl.ap.core.base;


import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.domain.ContextParam;
import com.caisl.ap.core.domain.FunctionCodeEnum;

/**
 * IActivityHandler
 * 活动处理器
 * 对应不同的functionCode{@link FunctionCodeEnum}可以派生多个子类，子类内部可以抽象定义一套模板方法，具体交由各个活动自行实现
 *
 * @author caisl
 * @since 2019-01-03
 */
public interface IActivityHandler {
    /**
     * 业务处理
     *
     * @param contextParam
     * @return
     */
    <RSP extends ActivityResponse> RSP handle(ContextParam contextParam);
}
