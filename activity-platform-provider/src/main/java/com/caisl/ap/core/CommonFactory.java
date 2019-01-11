/*
 * Copyright (C) 2009-2017 Hangzhou 2Dfire Technology Co., Ltd.All rights reserved
 */
package com.caisl.ap.core;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * CommonFactory
 *
 * @author shinan
 * @since 2019-01-03
 */
@Component
public class CommonFactory implements ApplicationContextAware {
    private Container container = new Container();

    public IActivityHandler getActivityHandler(FunctionCodeEnum function, Integer activityType) {
        IActivityHandler functionHandler = (IActivityHandler) container.get(IActivityHandler.class, function, activityType);
        if (functionHandler == null) {
            String err = "functionCode :" + function + " - FunctionHandler mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return functionHandler;
        }
    }

    public IActivityDTOParser getActivityDTOParser(FunctionCodeEnum function, Integer activityType) {
        IActivityDTOParser activityDTOParser = (IActivityDTOParser) container.get(IActivityDTOParser.class, function, activityType);
        if (activityDTOParser == null) {
            String err = "functionCode :" + function + " - activityDTOParser mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return activityDTOParser;
        }
    }


    public IActivityResponseParser getActivityReponseParser(FunctionCodeEnum function, Integer activityType) {
        IActivityResponseParser activityResponseParser = (IActivityResponseParser) container.get(IActivityDTOParser.class, function, activityType);
        if (activityResponseParser == null) {
            String err = "functionCode :" + function + " - activityResponseParser mismatch";
            throw new IllegalArgumentException(err);
        } else {
            return activityResponseParser;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Class[] types = new Class[] { IActivityHandler.class};
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

    private ActivityTypeEnum[] getActivityTypes(Object obj) {
        ActivityTypeMapper sMap = obj.getClass().getAnnotation(ActivityTypeMapper.class);
        if (sMap == null) {
            return null;
        }
        return sMap.value();
    }

    private static class Container {
        private final Map<Class, Map<FunctionCodeEnum, Map<ActivityTypeEnum, Object>>> beanMap;
        public Container() {
            this.beanMap = new HashMap<>();
        }

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

/*        public Object get(Class clazz, FunctionCodeEnum function) {
            Map<ActivityTypeEnum, Object> activityMap = getActivityMap(clazz, function);
            Object bean = activityMap.get(ActivityTypeEnum.DEFAULT);
            if (bean == null) {
                String err = function + " " + clazz.getSimpleName() + " mismatch";
                throw new IllegalArgumentException(err);
            }
            return bean;
        }*/

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
