package com.caisl.ap.dao;

import com.caisl.ap.BaseTest;
import com.caisl.ap.common.dao.ActivityConfigDAO;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * ActivityConfigDAOTest
 *
 * @author caisl
 * @since 2019-03-12
 */
public class ActivityConfigDAOTest extends BaseTest {
    @Resource
    ActivityConfigDAO activityConfigDAO;

    @Test
    public void selectByIdTest(){
        activityConfigDAO.selectByPrimaryKey(0L);
    }
}
