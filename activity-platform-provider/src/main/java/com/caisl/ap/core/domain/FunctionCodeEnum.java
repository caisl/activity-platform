
package com.caisl.ap.core.domain;

/**
 * FunctionCodeEnum
 *
 * @author caisl
 * @since 2019-01-09
 */
public enum FunctionCodeEnum {
    ACTIVITY_PARTICIPATE("participate"),
    ACTIVITY_RELEASE("release");

    private String code;

    FunctionCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
