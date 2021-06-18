package com.caisl.ap.core.annotation;

import com.caisl.ap.core.domain.ActivitySubTypeEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author zhangchen
 * @since 2021/06/18
 */
@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivitySubTypeMapper {

    ActivitySubTypeEnum[] value() default {ActivitySubTypeEnum.DEFAULT};
}
