
package com.caisl.ap.core.base;


import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.domain.ContextParam;

/**
 * IActivityHandler
 *
 * @author shinan
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
