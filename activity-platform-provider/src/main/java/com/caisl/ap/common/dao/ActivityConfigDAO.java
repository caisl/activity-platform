package com.caisl.ap.common.dao;

import com.caisl.ap.common.dao.mapper.ActivityConfigMapper;
import com.caisl.ap.common.dataobject.ActivityConfigDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * ActivityConfigDAO
 *
 * @author caisl
 * @since 2019-02-14
 */
@Repository
public class ActivityConfigDAO {
    @Resource
    ActivityConfigMapper activityConfigMapper;

    public ActivityConfigDO selectByPrimaryKey(Long activityId){
        return activityConfigMapper.selectByPrimaryKey(activityId);
    }

}
