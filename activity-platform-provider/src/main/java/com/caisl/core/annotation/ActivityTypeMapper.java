/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.caisl.core.annotation;


import com.caisl.core.domain.ActivityTypeEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * FunctionMapper
 *
 * @author shinan
 * @since 2019-01-09
 */
@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityTypeMapper {

    /**
     * ActivityTypeEnum
     *
     * @return
     */
    ActivityTypeEnum[] value();
}
