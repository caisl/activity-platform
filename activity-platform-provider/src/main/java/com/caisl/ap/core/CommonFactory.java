
package com.caisl.ap.core;


import com.caisl.ap.core.annotation.ActivityTypeMapper;
import com.caisl.ap.core.annotation.FunctionMapper;
import com.caisl.ap.core.base.IActivityDTOParser;
import com.caisl.ap.core.base.IActivityHandler;
import com.caisl.ap.core.base.IActivityResponseParser;
import com.caisl.ap.core.domain.ActivityTypeEnum;
import com.caisl.ap.core.domain.FunctionCodeEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * CommonFactory
 *
 * @author caisl
 * @since 2019-01-03
 */
@Component
public class CommonFactory implements ApplicationContextAware {

    private Container container = new Container();

    /**
     * 获取活动处理器
     *
     * @param function
     * @param activityType
     * @return
     */
    public IActivityHandler getActivityHandler(FunctionCodeEnum function, Integer activityType) {
        IActivityHandler functionHandler = (IActivityHandler) container.get(IActivityHandler.class, function, activityType);
        if (functionHandler == null) {
            String err = "functionCode :" + function + " - FunctionHandler mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return functionHandler;
        }
    }

    /**
     * 获取活动数据传输对象解析器
     *
     * @param function
     * @param activityType
     * @return
     */
    public IActivityDTOParser getActivityDTOParser(FunctionCodeEnum function, Integer activityType) {
        IActivityDTOParser activityDTOParser = (IActivityDTOParser) container.get(IActivityDTOParser.class, function, activityType);
        if (activityDTOParser == null) {
            String err = "functionCode :" + function + " - activityDTOParser mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return activityDTOParser;
        }
    }

    /**
     * 获取活动响应对象解析器
     *
     * @param function
     * @param activityType
     * @return
     */
    public IActivityResponseParser getActivityResponseParser(FunctionCodeEnum function, Integer activityType) {
        IActivityResponseParser activityResponseParser = (IActivityResponseParser) container.get(IActivityResponseParser.class, function, activityType);
        if (activityResponseParser == null) {
            String err = "functionCode :" + function + " - activityResponseParser mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return activityResponseParser;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Class[] types = new Class[]{IActivityHandler.class, IActivityDTOParser.class, IActivityResponseParser.class};
        for (Class type : types) {
            Map map = applicationContext.getBeansOfType(type);
            for (Object bean : map.values()) {
                FunctionMapper fMap = bean.getClass().getAnnotation(FunctionMapper.class);
                if (fMap == null) {
                    throw new RuntimeException(bean.getClass() + " has not Annotation @FunctionMapper");
                }
                if (fMap.value().length == 0) {
                    throw new RuntimeException(bean.getClass() + " @FunctionMapper is empty");
                }
                ActivityTypeEnum[] activityTypes = getActivityTypes(bean);

                for (FunctionCodeEnum function : fMap.value()) {
                    container.put(type, function, activityTypes, bean);
                }
            }
        }
    }

    /**
     * 获取ActivityTypeMapper注解内容
     *
     * @param obj
     * @return
     */
    private ActivityTypeEnum[] getActivityTypes(Object obj) {
        ActivityTypeMapper activityMap = obj.getClass().getAnnotation(ActivityTypeMapper.class);
        if (activityMap == null) {
            return null;
        }
        return activityMap.value();
    }

    /**
     * 容器类
     */
    private static class Container {
        private final Map<Class, Map<FunctionCodeEnum, Map<ActivityTypeEnum, Object>>> beanMap;

        public Container() {
            this.beanMap = new HashMap<>();
        }

        /**
         * 往容器中添加对象
         *
         * @param clazz
         * @param function
         * @param ActivityTypeEnums
         * @param bean
         */
        public void put(Class clazz, FunctionCodeEnum function, ActivityTypeEnum[] ActivityTypeEnums, Object bean) {
            Map<FunctionCodeEnum, Map<ActivityTypeEnum, Object>> functionMap = beanMap.get(clazz);
            if (functionMap == null) {
                functionMap = new HashMap<>();
                beanMap.put(clazz, functionMap);
            }
            Map<ActivityTypeEnum, Object> activityMap = functionMap.get(function);
            if (activityMap == null) {
                activityMap = new HashMap<>();
                functionMap.put(function, activityMap);
            }
            if (ActivityTypeEnums == null || ActivityTypeEnums.length == 0) {
                Object o = activityMap.put(ActivityTypeEnum.DEFAULT, bean);
                if (o != null) {
                    throw new RuntimeException("duplicate bean,Class=" + clazz.getName() + "FunctionCodeEnum="
                            + function.getCode() + ",ActivityTypeEnum=" + ActivityTypeEnum.DEFAULT.getType() + ",beans=[" +
                            bean + "|" + o + "]");
                }
            } else {
                for (ActivityTypeEnum ActivityTypeEnum : ActivityTypeEnums) {
                    Object o = activityMap.put(ActivityTypeEnum, bean);
                    if (o != null) {
                        throw new RuntimeException("duplicate bean,Class=" + clazz.getName() + "FunctionCodeEnum="
                                + function.getCode() + ",ActivityTypeEnum=" + ActivityTypeEnum.getType() + ",beans=[" + bean
                                + "|" + o + "]");
                    }
                }
            }
        }

        public Object get(Class clazz, FunctionCodeEnum function, Integer activityType) {
            Map<ActivityTypeEnum, Object> activityMap = getActivityMap(clazz, function);
            Object bean = activityMap.get(ActivityTypeEnum.getByType(activityType));
            if (bean == null) {
                bean = activityMap.get(ActivityTypeEnum.DEFAULT);
            }
            if (bean == null) {
                String err = function + ",ActivityType:" + activityType + " " + clazz.getSimpleName() + " mismatch";
                throw new IllegalArgumentException(err);
            }
            return bean;
        }

        private Map<ActivityTypeEnum, Object> getActivityMap(Class clazz, FunctionCodeEnum function) {
            Map<FunctionCodeEnum, Map<ActivityTypeEnum, Object>> functionMap = beanMap.get(clazz);
            if (functionMap == null) {
                String err = clazz.getSimpleName() + " mismatch";
                throw new IllegalArgumentException(err);
            }
            Map<ActivityTypeEnum, Object> sellerMap = functionMap.get(function);
            if (sellerMap == null) {
                String err = function + " " + clazz.getSimpleName() + " mismatch";
                throw new IllegalArgumentException(err);
            }
            return sellerMap;
        }
    }
}
