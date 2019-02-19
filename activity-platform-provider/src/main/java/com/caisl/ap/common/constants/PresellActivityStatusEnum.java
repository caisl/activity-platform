package com.caisl.ap.common.constants;

/**
 * PresellActivityStatusEnum
 *
 * @author caisl
 * @since 2019-02-19
 */
public enum PresellActivityStatusEnum {
    INITIALIZE(1, "待发布"),
    PROCESSING(2, "进行中"),
    PAUSED(3, "已暂停"),
    TERMINATED(4, "已终止"),
    EXPIRED(5, "已过期");

    private Integer code;
    private String desc;

    PresellActivityStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PresellActivityStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PresellActivityStatusEnum statusEnum : values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
