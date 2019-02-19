package com.caisl.ap.activity.constant;

/**
 * ResultCodeEnum
 *
 * @author caisl
 * @since 2019-02-14
 */
public enum ResultCodeEnum {
    REQUEST_SUCCESS("1", "request success"),
    REQUEST_FAIL("0", "request fail"),
    SYSTEM_ERROR("10000", "系统内部异常"),
    PARAM_ERROR("10001", "参数不合法"),
    RULE_PARAM_ERROR("10002", "规则参数不合法"),




    RESPONSE_NULL("20001", "响应对象为空"),
    ACTIVITY_IS_WRONG("20002", "活动异常"),
    RULE_CHECK_FAIL("20003", "规则检验不通过");


    private String code;
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
