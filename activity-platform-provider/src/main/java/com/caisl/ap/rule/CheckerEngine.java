package com.caisl.ap.rule;

import com.caisl.ap.activity.constant.Result;
import com.caisl.ap.rule.base.Checker;
import com.caisl.ap.rule.base.Rule;
import com.caisl.ap.system.helper.BeanHelper;
import com.caisl.ap.system.util.ResultUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * CheckerEngine
 *
 * @author caisl
 * @since 2019-01-22
 */
public abstract class CheckerEngine<T extends Rule, C extends Checker> {
    public static final String CHECKER = "Checker";

    @Resource
    protected BeanHelper beanHelper;


    /**
     * 检查规则配置格式
     *
     * @param _checkList
     * @return
     */
    public Result check(List<T> _checkList) {
        return (new CheckerProcess<T>() {
            public Result _process(T _check) {
                return CheckerEngine.this.getChecker(_check).check(_check);
            }
        }).process(_checkList);
    }

    /**
     * 获取对应的检查类
     *
     * @param rule
     * @return
     */
    protected C getChecker(T rule) {
        return (C) this.beanHelper.getBean(this.getName(rule.getClass()));
    }

    /**
     * 组装名称
     *
     * @param clazz
     * @return
     */
    private String getName(Class clazz) {
        String clazzName = clazz.getSimpleName() + CHECKER;
        return StringUtils.uncapitalize(clazzName);
    }

    /**
     * 内部执行类
     *
     * @param <T>
     */
    public abstract class CheckerProcess<T> {
        public CheckerProcess() {
        }

        /**
         * 执行方法
         *
         * @param checkList
         * @return
         */
        public Result<List<String>> process(List<T> checkList) {
            if (CollectionUtils.isEmpty(checkList)) {
                return ResultUtil.successResult(Collections.EMPTY_LIST);
            } else {
                Result codeDuplicate = checkCodeDuplicate(checkList);
                if (!ResultUtil.isResultSuccess(codeDuplicate)) {
                    return codeDuplicate;
                }
                List<String> msg = Lists.newArrayList();
                Iterator<T> ruleChecker = checkList.iterator();
                while (ruleChecker.hasNext()) {
                    T checker = ruleChecker.next();
                    Result checkResult = this._process(checker);
                    if (!checkResult.isSuccess()) {
                        msg.add(checkResult.getMessage());
                    }
                }
                if (!CollectionUtils.isEmpty(msg)) {
                    return ResultUtil.successResult(msg);
                } else {
                    return ResultUtil.successResult(Collections.EMPTY_LIST);
                }
            }
        }

        /**
         * 执行方法
         *
         * @param checkList
         * @return
         */
        public Result<String> processSingleRule(List<T> checkList) {
            if (CollectionUtils.isEmpty(checkList)) {
                return ResultUtil.successResult(Collections.EMPTY_LIST);
            } else {
                Result codeDuplicate = checkCodeDuplicate(checkList);
                if (!ResultUtil.isResultSuccess(codeDuplicate)) {
                    return codeDuplicate;
                }
                Iterator<T> ruleChecker = checkList.iterator();
                while (ruleChecker.hasNext()) {
                    T checker = ruleChecker.next();
                    Result checkResult = this._process(checker);
                    if (!checkResult.isSuccess()) {
                        return ResultUtil.successResult(checkResult.getMessage());
                    }
                }
                return ResultUtil.successResult(StringUtils.EMPTY);
            }
        }

        /**
         * 检查code是否重复
         *
         * @param checkList
         * @return
         */
        private Result checkCodeDuplicate(List<T> checkList) {
            if (checkList.get(0) instanceof Rule) {
                Map ruleMap = new HashMap();
                Iterator checkerIterator = checkList.iterator();
                while (checkerIterator.hasNext()) {
                    Rule rule = (Rule) checkerIterator.next();
                    String code = rule.getCode();
                    if (ruleMap.get(code) != null) {
                        return ResultUtil.failResult("", "rule code重复:" + code);
                    }
                    ruleMap.put(code, rule);
                }
            }
            return ResultUtil.successResult(null);
        }

        /**
         * @param checker
         * @return
         */
        public abstract Result _process(T checker);
    }
}
