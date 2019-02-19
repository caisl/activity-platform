package com.caisl.ap.common.dataobject;

import java.io.Serializable;

/**
 * ActivityConfigDO
 * <p>
 * 数据库活动配置持久化对象
 *
 * @author caisl
 * @since 2019-01-12
 */
public class ActivityConfigDO implements Serializable {
    /**
     * 活动开始时间
     */
    private Long startTime;
    /**
     * 活动结束时间
     */
    private Long endTime;
    /**
     * 活动状态
     */
    private Integer activityStatus;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }
}
