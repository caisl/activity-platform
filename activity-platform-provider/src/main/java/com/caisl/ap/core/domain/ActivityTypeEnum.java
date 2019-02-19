
package com.caisl.ap.core.domain;

/**
 * ActivityTypeEnum
 *
 * @author caisl
 * @since 2019-01-10
 */
public enum ActivityTypeEnum {
    DEFAULT(-1, ""),
    NEW_CUSTOMER_GIFT(1, "新人有礼"),
    DIRECT_SEND_COUPON(2, "定向红包活动");

    ActivityTypeEnum(int type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    public static ActivityTypeEnum getByType(Integer type) {
        if (type == null) {
            return null;
        }
        for (ActivityTypeEnum activityTypeEnum : values()) {
            if (type == activityTypeEnum.getType()) {
                return activityTypeEnum;
            }
        }
        return null;
    }

    private int type;

    private String memo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
