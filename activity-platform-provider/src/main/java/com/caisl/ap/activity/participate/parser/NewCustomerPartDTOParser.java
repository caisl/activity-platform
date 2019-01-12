package com.caisl.ap.activity.participate.parser;

import com.caisl.ap.activity.participate.domain.BaseActivityPartDTO;
import com.caisl.ap.activity.participate.domain.NewCustomerPartDTO;
import com.caisl.ap.activity.request.NewCustomerPartRequest;
import com.caisl.ap.core.ActivityConfigDO;
import com.caisl.ap.rule.Rule;

import java.util.Collections;
import java.util.List;

/**
 * NewCustomerPartDTOParser
 *
 * @author shinan
 * @since 2019-01-12
 */
public class NewCustomerPartDTOParser extends AbstractActivityPartDTOParser<NewCustomerPartRequest> {

    @Override
    protected ActivityConfigDO queryDB(NewCustomerPartRequest request) {
        System.out.println("do queryDB");
        return new ActivityConfigDO();
    }

    @Override
    protected List<Rule> buildRules(ActivityConfigDO activityConfigDO) {
        System.out.println("do buildRules");
        return Collections.emptyList();
    }

    @Override
    protected BaseActivityPartDTO assembleDTO(ActivityConfigDO activityConfigDO, List<Rule> rules) {
        System.out.println("do assembleDTO");
        return new NewCustomerPartDTO();
    }
}
