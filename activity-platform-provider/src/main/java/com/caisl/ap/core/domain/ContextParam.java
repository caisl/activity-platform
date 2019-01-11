/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.caisl.ap.core.domain;


import com.caisl.ap.activity.request.base.ActivityRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ContextParam
 *
 * @author shinan
 * @since 2019-01-09
 */
public class ContextParam {
    private final FunctionCodeEnum functionCode;

    private Integer activityType;

    private ActivityRequest request;

    private ActivityDTO activityDTO;

    public ContextParam(FunctionCodeEnum functionCode, ActivityRequest request, Integer activityType) {
        this.functionCode = functionCode;
        this.request = request;
        this.activityType = activityType;
    }

    public FunctionCodeEnum getFunctionCode() {
        return functionCode;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public ActivityRequest getRequest() {
        return request;
    }

    public void setRequest(ActivityRequest request) {
        this.request = request;
    }


    public ActivityDTO getActivityDTO() {
        return activityDTO;
    }

    public void setActivityDTO(ActivityDTO activityDTO) {
        this.activityDTO = activityDTO;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
