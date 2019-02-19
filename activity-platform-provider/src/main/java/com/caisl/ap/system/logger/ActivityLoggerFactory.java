package com.caisl.ap.system.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActivityLoggerFactory
 *
 * @author caisl
 * @since 2019-02-14
 */
public class ActivityLoggerFactory {

    /**
     * 异常日志
     */
    public final static Logger EXCEPTION_HANDLER = LoggerFactory.getLogger("EXCEPTION_HANDLER");

    /**
     * 超时日志
     */
    public final static Logger TIME_OUT = LoggerFactory.getLogger("TIME_OUT");

    /**
     * MQ消息日志
     */
    public final static Logger MESSAGE = LoggerFactory.getLogger("MESSAGE");

    /**
     * 业务日志
     */
    public final static Logger BUSINESS = LoggerFactory.getLogger("BUSINESS");

    /**
     * 数据埋点分析日志
     */
    public final static Logger DATA_ANALYSIS = LoggerFactory.getLogger("DATA_ANALYSIS");

}
