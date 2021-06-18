package com.caisl.ap.core.domain;


import com.caisl.ap.activity.request.base.ActivityRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ContextParam
 *
 * @author caisl
 * @since 2019-01-09
 */
public class ContextParam<REQ extends ActivityRequest, DTO extends ActivityDTO> {
    private final FunctionCodeEnum functionCode;

    private Integer activityType;
    private Integer activitySubType;

    private REQ request;

    private DTO activityDTO;

    public ContextParam(FunctionCodeEnum functionCode, REQ request, Integer activityType, Integer activitySubType) {
        this.functionCode = functionCode;
        this.request = request;
        this.activityType = activityType;
        this.activitySubType = activitySubType;
    }

    public ContextParam(FunctionCodeEnum functionCode, REQ request, Integer activityType) {
        this(functionCode, request, activityType, ActivitySubTypeEnum.DEFAULT.getType());
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

    public Integer getActivitySubType() {
        return activitySubType;
    }

    public void setActivitySubType(Integer activitySubType) {
        this.activitySubType = activitySubType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public REQ getRequest() {
        return request;
    }

    public void setRequest(REQ request) {
        this.request = request;
    }

    public DTO getActivityDTO() {
        return activityDTO;
    }

    public void setActivityDTO(DTO activityDTO) {
        this.activityDTO = activityDTO;
    }
}
