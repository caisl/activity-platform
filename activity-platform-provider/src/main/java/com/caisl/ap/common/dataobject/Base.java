package com.caisl.ap.common.dataobject;

import lombok.Data;

import java.io.Serializable;

/**
 * Base
 *
 * @author caisl
 * @since 2019-03-12
 */
@Data
public abstract class Base implements Serializable {
    /**
     * 是否有效
     */
    private Integer isValid = 1;

    /**
     * 版本号
     */
    private Integer lastVer = 1;

    /**
     * 创建时间
     */
    private Long createTime = System.currentTimeMillis();

    /**
     * 更新时间
     */
    private Long opTime = System.currentTimeMillis();

}
