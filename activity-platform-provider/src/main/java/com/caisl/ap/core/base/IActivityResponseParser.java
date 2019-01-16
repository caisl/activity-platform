package com.caisl.ap.core.base;

import com.caisl.ap.activity.response.base.ActivityResponse;
import com.caisl.ap.core.domain.ContextParam;

/**
 * IActivityResponseParser
 * 活动响应对象解析器
 *
 * @author shinan
 * @since 2019-01-10
 */
public interface IActivityResponseParser {
    /**
     * 构造响应对象
     *
     * @param contextParam
     * @return
     */
    ActivityResponse buildResponse(ContextParam contextParam);
}
