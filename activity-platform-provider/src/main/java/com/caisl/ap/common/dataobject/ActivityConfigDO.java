package com.caisl.ap.common.dataobject;

import lombok.Data;

/**
 * ActivityConfigDO
 * <p>
 * 数据库活动配置持久化对象
 *
 * @author caisl
 * @since 2019-01-12
 */
@Data
public class ActivityConfigDO extends Base {
    private static final long serialVersionUID = 17062730966705954L;
    /**
     * 主键
     */
    private Long activityConfigId;

    private String activityTitle;

    private Byte activityType;

    private String creator;

    private String operator;

    private String env;

    private String publisher;

    private Integer status;

    private Long startTime;

    private Long endTime;

    private Long publishTime;

    private String description;

    private String remark;

    private String extendField;
}
