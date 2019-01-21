package com.caisl.ap.activity.participate.parser;


import com.caisl.ap.activity.participate.domain.BaseActivityPartDTO;
import com.caisl.ap.activity.request.base.BaseActivityPartRequest;
import com.caisl.ap.core.ActivityConfigDO;
import com.caisl.ap.core.base.IActivityDTOParser;
import com.caisl.ap.rule.base.Rule;
import com.caisl.ap.system.exception.BusinessRuntimeException;

import java.util.List;

/**
 * AbstractActivityPartDTOParser
 *
 * @author shinan
 * @since 2019-01-10
 */
public abstract class AbstractActivityPartDTOParser<REQ extends BaseActivityPartRequest> implements
        IActivityDTOParser<REQ> {

    /**
     * 读取数据库活动配置
     *
     * @param request
     * @return
     */
    protected abstract ActivityConfigDO queryDB(REQ request);

    /**
     * 构造活动规则集合
     *
     * @param activityConfigDO
     * @return
     */
    protected abstract List<Rule> buildRules(ActivityConfigDO activityConfigDO);

    /**
     * 组装DTO
     *
     * @param activityConfigDO
     * @param rules
     * @return
     */
    protected abstract BaseActivityPartDTO assembleDTO(ActivityConfigDO activityConfigDO, List<Rule> rules);

    @Override
    public BaseActivityPartDTO buildDTO(REQ request) {
        ActivityConfigDO activityConfigDO = queryDB(request);
        if (activityConfigDO == null) {
            throw new BusinessRuntimeException("", "");
        }
        List<Rule> rules = buildRules(activityConfigDO);
        return assembleDTO(activityConfigDO, rules);
    }
}
