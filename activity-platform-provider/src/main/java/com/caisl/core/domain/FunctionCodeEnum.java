/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.caisl.core.domain;

/**
 * FunctionCodeEnum
 *
 * @author shinan
 * @since 2019-01-09
 */
public enum FunctionCodeEnum {
    ACTIVITY_PARTICIPATE("");

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
