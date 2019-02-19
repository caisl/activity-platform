
package com.caisl.ap.core.base;


import com.caisl.ap.activity.request.base.ActivityRequest;
import com.caisl.ap.core.domain.ActivityDTO;

/**
 * IActivityDTOParser
 * 活动数据传输对象解析器
 *
 * @author caisl
 * @since 2019-01-10
 */
public interface IActivityDTOParser<REQ extends ActivityRequest> {
    /**
     * 构建活动数据传输对象
     *
     * @param request
     * @return
     */
    ActivityDTO buildDTO(REQ request);
}
