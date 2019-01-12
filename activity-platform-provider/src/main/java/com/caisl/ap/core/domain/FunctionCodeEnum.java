
package com.caisl.ap.core.domain;

/**
 * FunctionCodeEnum
 *
 * @author shinan
 * @since 2019-01-09
 */
public enum FunctionCodeEnum {
    ACTIVITY_PARTICIPATE("participate");

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
