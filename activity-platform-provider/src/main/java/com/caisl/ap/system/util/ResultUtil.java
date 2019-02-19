package com.caisl.ap.system.util;

import com.caisl.ap.activity.constant.Result;

/**
 * ResultUtil
 *
 * @author caisl
 * @since 2019-01-22
 */
public class ResultUtil {


    public static Result defaultResult() {
        return new Result();
    }

    public static Result successResult(Object model) {
        Result result = defaultResult();
        result.setModel(model);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    public static Result failResult(String errorCode, String errorMessage) {
        Result result = defaultResult();
        result.setSuccess(Boolean.FALSE);
        result.setResultCode(errorCode);
        result.setMessage(errorMessage);
        return result;
    }

    public static boolean isResultSuccess(Result result) {
        return null != result && result.isSuccess();
    }

}
