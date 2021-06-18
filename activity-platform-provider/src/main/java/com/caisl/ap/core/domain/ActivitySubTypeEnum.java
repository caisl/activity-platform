package com.caisl.ap.core.domain;

/**
 * @author zhangchen
 * @since 2021/06/18
 */
public enum ActivitySubTypeEnum {
    DEFAULT(-1, "", ActivityTypeEnum.DEFAULT),
    NEW_CUSTOMER_GIFT_SUB1(1, "新人有礼1", ActivityTypeEnum.NEW_CUSTOMER_GIFT),
    NEW_CUSTOMER_GIFT_SUB2(2, "新人有礼2", ActivityTypeEnum.NEW_CUSTOMER_GIFT),
    ;

    ActivitySubTypeEnum(int subType, String memo, ActivityTypeEnum parentActivityTypeEnum) {
        this.subType = subType;
        this.memo = memo;
        this.parentActivityTypeEnum = parentActivityTypeEnum;
    }

    private final int subType;

    private final String memo;

    private final ActivityTypeEnum parentActivityTypeEnum;

    public int getType() {
        return subType;
    }

    public String getMemo() {
        return memo;
    }

    public ActivityTypeEnum getParentActivityTypeEnum() {
        return parentActivityTypeEnum;
    }
}
