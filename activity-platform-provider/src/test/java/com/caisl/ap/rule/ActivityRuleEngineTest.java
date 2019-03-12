package com.caisl.ap.rule;

import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.BaseTest;
import com.caisl.ap.rule.base.Rule;
import com.caisl.ap.rule.domain.pojo.StartTimeRule;
import com.caisl.ap.rule.domain.request.ActivityPartRuleRequest;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * ActivityRuleEngineTest
 *
 * @author caisl
 * @since 2019-01-22
 */
public class ActivityRuleEngineTest extends BaseTest{
    @Resource
    private ActivityRuleEngine activityRuleEngine;


    @Test
    public void singleRuleTest(){
        ActivityPartRuleRequest request = new ActivityPartRuleRequest();
        List<Rule> ruleList = Lists.newArrayList();
        StartTimeRule startTimeRule = new StartTimeRule(System.currentTimeMillis()+100000000);
        ruleList.add(startTimeRule);
        Result<String> result = activityRuleEngine.validate(request, ruleList);
        Assert.assertTrue(result.isSuccess());
        System.out.println(result.getModel());
    }
}
