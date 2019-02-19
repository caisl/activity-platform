package com.caisl.ap.system.util;

import ch.qos.logback.classic.Level;
import com.caisl.ap.system.util.log.LogFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;


/**
 * LogUtil
 *
 * @author caisl
 * @since 2019-02-18
 */
public class LogUtil {

    /**
     * 日志记录
     *
     * @param logger
     * @param marker
     * @param level
     * @param message
     */
    public static void log(Logger logger, Marker marker, Level level, String message) {
        log(logger, marker, level, message, null);
    }

    /**
     * 日志记录
     *
     * @param logger
     * @param marker
     * @param level
     * @param message
     * @param t
     */
    public static void log(Logger logger, Marker marker, Level level, String message, Throwable t) {
        if (level == null || StringUtils.isBlank(message)) {
            return;
        }
        int logLevel = level.toInt();
        switch (logLevel) {
            case Level.TRACE_INT:
                logger.trace(marker, message, t);
                break;
            case Level.DEBUG_INT:
                logger.debug(marker, message, t);
                break;
            case Level.INFO_INT:
                logger.info(marker, message, t);
                break;
            case Level.WARN_INT:
                logger.warn(marker, message, t);
                break;
            case Level.ERROR_INT:
                logger.error(marker, message, t);
                break;
        }
    }

    public static String formatLog(LogFormat logFormat) {
        return logFormat != null ? logFormat.log() : "";
    }
}
