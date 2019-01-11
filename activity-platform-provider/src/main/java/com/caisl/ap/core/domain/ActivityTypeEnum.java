/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.caisl.ap.core.domain;

/**
 * ActivityTypeEnum
 *
 * @author shinan
 * @since 2019-01-10
 */
public enum ActivityTypeEnum {
    DEFAULT(-1,"","");
    ActivityTypeEnum(int type, String memo, String name) {
        this.type = type;
        this.memo = memo;
        this.name = name;
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

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
